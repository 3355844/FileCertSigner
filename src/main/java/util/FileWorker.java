package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class FileWorker {

	private static Logger logger = Logger.getLogger(FileWorker.class);
	
	public static void writeToFile(String path, byte[] key) {
		logger.debug("Begin write file");
		File f = new File(path);
		f.getParentFile().mkdirs();
		try (FileOutputStream fos = new FileOutputStream(f)) {
			fos.write(key);
			fos.flush();
			fos.close();
			logger.debug("successful writed file :" + f.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("End write file");
	}
	
	public static String[] getFileListFromDir(String dirPath) {
		File file = new File(dirPath);
		String[] fileList = file.list();
		for (String name : fileList) {
			logger.debug("File Name: " + name);
		}
		return fileList;		
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
