package processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import signer.FileSigner;
import signer.FileSignerImpl;
import swing.ui.FileInterface;
import util.KeyGeneratorCusom;
import util.SelfSignedCertificateGeneration;

public class SignProcessor {

	static final Logger logger = Logger.getLogger(SignProcessor.class);

	private static File file;
	private static FileSigner signer;
	
	public static void main(String[] args) {
		logger.debug("======= START Sign processor main method =========");
		FileInterface.showMainScreen();
//		FileInterface.showAllFilesInDir(ROOT_FILE_DIR);
//		FileInterface.fileDownloadDialog(ROOT_FILE_DIR);
//		FileInterface.showAllFilesInDir(ROOT_FILE_DIR);
//		KeyGeneratorCusom.loadCertificate();
//		SelfSignedCertificateGeneration.genCerteficate(); 
//		KeyGenerator generator = new KeyGenerator();
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

	private static void init(String filePath) {
		logger.debug("======= START INIT method with filePath:" + filePath + " =========");
		signer = new FileSignerImpl();
		file = new File(filePath);
		logger.debug("======= END INIT method with file.length: " + file.length() + " =========");
	}

}
