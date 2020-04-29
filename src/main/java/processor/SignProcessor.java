package processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Logger;

import signer.FileSigner;
import signer.FileSignerImpl;
import swing.ui.FileInterface;
import util.Const;
import util.FileWorker;
import util.SecurityGenerator;
import util.SelfSignedCertificateGeneration;

public class SignProcessor {

	static final Logger logger = Logger.getLogger(SignProcessor.class);

	private static File file;
	private static FileSigner signer;

	public static void main(String[] args) {
		String fileName = "info.txt";
		logger.debug("======= START Sign processor main method =========");
//
//		SecurityGenerator.signFile(fileName);
//		SecurityGenerator.verifySign(fileName);
//	} 
//	
//	public void doNothing(String fileName) {
		try {
			PrivateKey privKey= SecurityGenerator.getPrivateKey(Const.CERT_DIR + Const.DEF_PRIV_KEY);
			PublicKey publKey = SecurityGenerator.getPublicKey(Const.CERT_DIR + Const.DEF_PUB_KEY);
//			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
//			generator.initialize(2048);
//			KeyPair keyPair = generator.generateKeyPair();
			// Digital Signature
			Signature dsa;
			dsa = Signature.getInstance("SHA256withRSA");
			dsa.initSign(privKey);
//			dsa.initVerify(certificate);
			// Update and sign the data
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publKey);
//			String secretHash = new String(hashBytes(FileWorker.getBytes(FileWorker.getFileSign(fileName))));
//			logger.debug(secretHash);
			byte[] data = cipher.doFinal(hashBytes(FileWorker.getBytes(FileWorker.getFileSign(fileName))));
			dsa.update(data);
			byte[] signature = dsa.sign();
			// Verify signature
			dsa.initVerify(publKey);
			dsa.update(data);
			boolean verifies = dsa.verify(signature);
			logger.debug("Signature is ok: " + verifies);
			// Decrypt if signature is correct
//			if (verifies) {
//				cipher.init(Cipher.DECRYPT_MODE, privKey);
//				byte[] result = cipher.doFinal(data);
//				logger.debug(new String(result));
//			}

		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | SignatureException  e) {
			e.printStackTrace();
		}
		
//		try {
//			PublicKey publicKey = SecurityGenerator.getPublicKey(Const.CERT_DIR + Const.DEF_PUB_KEY);
//			logger.debug(publicKey);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		FileWorker.showSignAttribute(Const.ROOT_FILE_DIR + fileName);
//		FileWorker.setSignAttrToFile(Const.ROOT_FILE_DIR + fileName, "securiy message");
//		FileWorker.showSignAttribute(Const.ROOT_FILE_DIR + fileName);

//		FileInterface view = new FileInterface();
//		view.setVisible(true);

//		SecurityGenerator.genKeyPairRSA();

		// FileInterface.showAllFilesInDir(ROOT_FILE_DIR);
//		FileInterface.fileDownloadDialog(ROOT_FILE_DIR);
//		FileInterface.showAllFilesInDir(ROOT_FILE_DIR);
//		KeyGeneratorCusom.loadCertificate();
//		SelfSignedCertificateGeneration.genCerteficate(); 
//		SecurityGenerator generator = new SecurityGenerator();
//		generator.init();

//		testCreationFile();
//		logger.debug("test file created");

//		init(filePath);
//		logger.debug("INIT success");

//		signer.setSignAttributeToFile(file);
//		logger.debug("Signed file name  " + file.getName());

//		String isValidSign = signer.fileValidator(file);
//		logger.debug("file is valid: " + isValidSign);

		logger.debug("======== END Sign processor main method =========");
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

	private static void init(String filePath) {
		logger.debug("======= START INIT method with filePath:" + filePath + " =========");
		signer = new FileSignerImpl();
		file = new File(filePath);
		logger.debug("======= END INIT method with file.length: " + file.length() + " =========");
	}

}
