package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

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

	public void genKeyPairRSA() {
		logger.debug("Begin generate Key Pair");
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(KEY_SIZE);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privayeKey = keyPair.getPrivate();
			logger.debug(privayeKey.toString());
			logger.debug(publicKey.toString());
			FileWorker.writeToFile(Const.CERT_DIR + "publicKey", publicKey.getEncoded());
			FileWorker.writeToFile(Const.CERT_DIR + "privateKey", privayeKey.getEncoded());
			logger.debug("Genereted key Pair Done");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
