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
		logger.debug("Begin write file with size"+ key.length);
		File f = new File(path);
		logger.debug("file bytes: " + FileWorker.getBytes(f));
		f.getParentFile().mkdirs();
		try (FileOutputStream fos = new FileOutputStream(f)) {
			fos.write(key);
			fos.flush();
			fos.close();
			logger.debug("successful writed file :" + f.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("End write file success");
	}

	public static String[] getFileListFromDir(String dirPath) {
		logger.debug("begin get file list from dir: "+ dirPath);
		File file = new File(dirPath);
		String[] fileList = file.list();
		for (String name : fileList) {
			logger.debug("File Name: " + name);
		}
		logger.debug("end file list from directory? list size: " + file.length());
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
		logger.debug("begin save process file: "+ file.getName());
		logger.debug("file bytes: " + FileWorker.getBytes(file));
		String filePath = saveDir + file.getName();
		FileWorker.writeToFile(filePath, getBytes(file));
		logger.debug("File downloaded successful, file name:" + file.getName());
	}

	public static byte[] getBytes(File file) {
		logger.debug("begin get bytes from file: " + file.getName());
		byte[] res = null;
		try {
			logger.debug("file path" + file.getPath());
			res = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			logger.debug("Error: " + e);
		}
		logger.debug("end get bytes from file successful" + res);
		return res;
	}
}
