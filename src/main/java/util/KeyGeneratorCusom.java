package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class KeyGeneratorCusom {

	private static final Logger logger = Logger.getLogger(KeyGeneratorCusom.class);
	public static final int KEY_SIZE = 2048;
	private static final String keyPathDir = "src/main/resources/cert/";

	public static void loadCertificate() {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(new FileInputStream(keyPathDir+"output.p12"), "somepass".toCharArray());

			Certificate cert = keyStore.getCertificate("private");

			logger.debug(cert);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} 
	
	public static void createKeyStore() {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(null, null);
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(KEY_SIZE);
			Key key = keyGen.generateKey();
			keyStore.setKeyEntry("secret", key, "somepass".toCharArray(), null);

			keyStore.store(new FileOutputStream(keyPathDir + "output.p12"), "somepass".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void init() {
		logger.debug("Begin generate Key Pair");
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(KEY_SIZE);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privayeKey = keyPair.getPrivate();
			logger.debug(privayeKey.toString());
			logger.debug(publicKey.toString());
			FileWorker.writeToFile(keyPathDir + "publicKey", publicKey.getEncoded());
			FileWorker.writeToFile(keyPathDir + "privateKey", privayeKey.getEncoded());
			logger.debug("Genereted key Pair Done");
			String alias = "localhost";
//			Certificate cert = keyPair.
//			PublicKey publicKey =  cert.getPublicKey();
//			String publicKeyValue = publicKey.toString();
//			logger.debug("public key value " + publicKeyValue);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
