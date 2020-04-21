package processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import signer.FileSigner;
import signer.FileSignerImpl;
import util.KeyGeneratorCusom;
import util.SelfSignedCertificateGeneration;

public class SignProcessor {

	static final Logger logger = Logger.getLogger(SignProcessor.class);

	private static File file;
	private static FileSigner signer;

	public static void main(String[] args) {
		logger.debug("======= START Sign processor main method =========");
		KeyGeneratorCusom.loadCertificate();
//		SelfSignedCertificateGeneration.genCerteficate(); 
//		KeyGenerator generator = new KeyGenerator();
//		generator.init();
//		String filePath = "src/main/resources/files/test_excel.ods"; // path to file which will signed

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

	private static void testCreationFile() {
		String tepmFileName = "src/main/resources/files/text.txt";
		String testText = "text for testing";
		try (OutputStream os = new FileOutputStream(new File(tepmFileName))) {
			os.write(testText.getBytes());
			os.flush();
		} catch (IOException e) {
			logger.debug("ERROR file creation");
		}
	}
}
