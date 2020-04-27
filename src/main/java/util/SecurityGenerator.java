package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
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
		try {
			Signature sign = Signature.getInstance(Const.SHA256_with_RSA);
			PrivateKey privateKey = getPrivateKey(Const.CERT_DIR + Const.DEF_PRIV_KEY);
			sign.initSign(privateKey);
			sign.update(Files.readAllBytes(file.toPath()));
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
		logger.debug("Verify file: " + file.getName());
		logger.debug("Public Key name: " + Const.DEF_PUB_KEY);
		try {
			Signature sign = Signature.getInstance(Const.SHA256_with_RSA);
			PublicKey publicKey = getPublicKey(Const.CERT_DIR + Const.DEF_PUB_KEY);
			sign.initVerify(publicKey);
			sign.update(Files.readAllBytes(file.toPath()));
			result = sign.verify(Files.readAllBytes(fileSign.toPath())) ? "OK" : "ERR";
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Verify complete :" + result);
		return result;
	}

	public static PublicKey getPublicKey(String fileName) throws Exception {
		File f = new File(fileName);
		X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Files.readAllBytes(f.toPath()));
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey pubKey = kf.generatePublic(keySpecX509);
		return pubKey;
	}

	public static RSAPrivateKey getPrivateKey(String fileName) throws Exception {
		File f = new File(fileName);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Files.readAllBytes(f.toPath()));
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return (RSAPrivateKey) kf.generatePrivate(spec);
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
