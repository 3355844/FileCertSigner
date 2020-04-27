package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.apache.log4j.Logger;

public class FileWorker {

	private static Logger logger = Logger.getLogger(FileWorker.class);

	public static void showSignAttribute(String fileName) {
		String attrValue = null;
		String attrName = "sign";
		UserDefinedFileAttributeView attr = Files.getFileAttributeView(new File(fileName).toPath(),
				UserDefinedFileAttributeView.class);
		ByteBuffer readBuffer;
		try {
			readBuffer = ByteBuffer.allocate(attr.size(attrName));
			attr.read(attrName, readBuffer);
			readBuffer.flip();
			attrValue = new String(readBuffer.array(), "UTF-8");
		} catch (IOException e) {
			logger.debug("No attribute " + attrName);
			attrName = "not Signed";
		}
		logger.debug("Fil sign value: " + attrValue);
	}

	public static void setSignAttrToFile(String fileName, String attrVal) {
		logger.debug("Start adding to File attribute: " + attrVal);
		String attrName = "sign";
		UserDefinedFileAttributeView attr = Files.getFileAttributeView(new File(fileName).toPath(),
				UserDefinedFileAttributeView.class);
		byte[] bytes;
		try {
			bytes = attrVal.getBytes("UTF-8");
			final ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			attr.write(attrName, writeBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("Attribute added successful");
	}

	public static File getFile(String fileName) {
		File file = new File(Const.ROOT_FILE_DIR + fileName);
		return file;
	}

	public static File getFileSign(String fileName) {
		String fileSign = Const.CERT_DIR + "sign_" + fileName;
		File file = new File(fileSign);
		return file;
	}

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

	public static void fileSaveProc(String saveDir, File file) {
		String filePath = saveDir + file.getName();
		try {
			FileWorker.writeToFile(filePath, Files.readAllBytes(file.toPath()));
			logger.debug("File downloaded successful, file name:" + file.getName());
		} catch (IOException e) {
			logger.error("Error Write file {}", e);
		}
	}
}
