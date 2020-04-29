package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyGenerator;

import org.apache.log4j.Logger;

public class SecurityGenerator {

	private static final Logger logger = Logger.getLogger(SecurityGenerator.class);
	private static final int KEY_SIZE = 2048;
	private static String ALIAS;
	private static String DEF_ALIAS_VAL = "genDefault";

	public static void init(String alias) {
		ALIAS = alias;
		if (ALIAS == null) {
			ALIAS = DEF_ALIAS_VAL;
		}
	}

	public static String signFile(String fileName) {
		File file = FileWorker.getFile(fileName);
		logger.debug("Signing file: " + file.getName());
		logger.debug("private key name:" + Const.DEF_PRIV_KEY);
		String result = "";
		String signPath = Const.CERT_DIR + "sign_" + fileName;
		byte[] hash = hashBytes(FileWorker.getBytes(file));
		try {
			Signature sign = Signature.getInstance(Const.SHA256_with_RSA);
			PrivateKey privateKey = getPrivateKey(Const.CERT_DIR + Const.DEF_PRIV_KEY);
			sign.initSign(privateKey);
			sign.update(hash);
			sign.sign();
			FileWorker.writeToFile(signPath, sign.sign());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Sign complete");
		return result;
	}

	public static String verifySign(String fileName) {
		String result = null;
		File fileSign = FileWorker.getFileSign(fileName);
		File file = FileWorker.getFile(fileName);
		byte[] hash = hashBytes(FileWorker.getBytes(file));
		logger.debug("Verify file: " + file.getName());
		logger.debug("Public Key name: " + Const.DEF_PUB_KEY);
		try {
			Signature sign = Signature.getInstance(Const.SHA256_with_RSA);
			PublicKey publicKey = getPublicKey(Const.CERT_DIR + Const.DEF_PUB_KEY);
			PrivateKey privKey = getPrivateKey(Const.CERT_DIR+ Const.DEF_PRIV_KEY);
			sign.initSign(privKey);
			sign.initVerify(publicKey);
			sign.sign();
			sign.update(hash);
			result = sign.verify(FileWorker.getBytes(fileSign)) ? "OK" : "ERR";
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Verify complete :" + result);
		return result;
	}

	private static byte[] hashBytes(byte[] bytes) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md.digest();
	}

	public static PublicKey getPublicKey(String fileName) {
		logger.debug("begin get public key: "+  fileName);
		File f = new File(fileName);
		X509EncodedKeySpec keySpecX509;
		PublicKey pubKey = null;
		logger.debug("Public key bytes" + FileWorker.getBytes(f));
		try {
			keySpecX509 = new X509EncodedKeySpec(Files.readAllBytes(f.toPath()));
			KeyFactory kf = KeyFactory.getInstance("RSA");
			pubKey = kf.generatePublic(keySpecX509);
		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		logger.debug("Public key value" + pubKey.getEncoded());
		logger.debug("get successful public key format: "+ pubKey.getFormat());
		return pubKey;
	}

	public static PrivateKey getPrivateKey(String fileName) {
		logger.debug("begin get private key :" + fileName);
		File f = new File(fileName);
		PKCS8EncodedKeySpec spec;
		PrivateKey privKey = null;
		try {
			spec = new PKCS8EncodedKeySpec(Files.readAllBytes(f.toPath()));
			KeyFactory kf = KeyFactory.getInstance("RSA");
			privKey = kf.generatePrivate(spec);
		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		logger.debug("get successful prvate key format: " + privKey.getFormat());
		return privKey;
	}

	public static void loadCertificate(String instName, String certName, String pass) {
		try {
			KeyStore keyStore = KeyStore.getInstance(Const.KeyStoreType.PKCS12.type());
			keyStore.load(new FileInputStream(Const.CERT_DIR + certName), pass.toCharArray());
			Certificate cert = keyStore.getCertificate("private");
			logger.debug(cert);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void createKeyStore() {
		try {
			KeyStore keyStore = KeyStore.getInstance(Const.KeyStoreType.PKCS12.type());
			keyStore.load(null, null);
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(KEY_SIZE);
			Key key = keyGen.generateKey();
			keyStore.setKeyEntry("secret", key, "somepass".toCharArray(), null);
			keyStore.store(new FileOutputStream(Const.CERT_DIR + "output.p12"), "somepass".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void genKeyPairRSA() {
		logger.debug("Begin generate Key Pair");
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(KEY_SIZE);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			Key publicKey = keyPair.getPublic();
			Key privayeKey = keyPair.getPrivate();
			logger.debug("Private Key Format: " + privayeKey.getFormat());
			logger.debug("Public Key Format: " + publicKey.getFormat());
			FileWorker.writeToFile(Const.CERT_DIR + Const.DEF_PUB_KEY, publicKey.getEncoded());
			FileWorker.writeToFile(Const.CERT_DIR + Const.DEF_PRIV_KEY, privayeKey.getEncoded());
			logger.debug("Genereted key Pair Done");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
