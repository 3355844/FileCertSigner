package signer;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.apache.log4j.Logger;

public class FileSignerImpl implements FileSigner {

	public static final Logger logger = Logger.getLogger(FileSignerImpl.class);
	public static final String ATTRIBUTE = "SystemID-SAPBS";

	public void setSignAttributeToFile(File file) {
		logger.debug("======== START setSignAttributeToFile =========");
		try {
			logger.debug(file.getName());
			final String valAttribute = file.getAbsolutePath()
					+ "AA009400007127312|UAH|AKD_ACCOUNT|26002878913122||.ACD";
			setAttributeToFile(ATTRIBUTE, valAttribute, file.toPath());

		} catch (Exception e) {
			logger.debug("ERROR when reading attributes:" + e.getLocalizedMessage());
		}
		logger.debug("======== END setSignAttributeToFile =========");
	}

	private String genereteKey(File file) {
		return "";
	}

	private void setAttributeToFile(final String nameAttribute, final String valAttribute, Path path)
			throws IOException {
		final UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		final byte[] bytes = valAttribute.getBytes(StandardCharsets.UTF_8);
		final ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);

		writeBuffer.put(bytes);
		writeBuffer.flip();
		view.write(ATTRIBUTE, writeBuffer);
	}

	public String fileValidator(File file) {
		logger.debug("======== START  fileValidator =========");
		String valueFromAttributes = null;
		Path filePath = Paths.get(file.getPath());
		try {
			valueFromAttributes = getAttributeValue(filePath, ATTRIBUTE);
		} catch (Exception e) {
			logger.debug("ERROR when reading attributes " + e.getLocalizedMessage());
		}
		logger.debug("======== END  fileValidator =========");
		return valueFromAttributes == null ? "err" : "ok";
	}

	private String getAttributeValue(Path filePath, String nameAttribute)
			throws IOException, UnsupportedEncodingException {
		logger.debug("GET Attribute with name: " + nameAttribute);
		UserDefinedFileAttributeView view = Files.getFileAttributeView(filePath, UserDefinedFileAttributeView.class);
		ByteBuffer readBuffer = ByteBuffer.allocate(view.size(nameAttribute));
		view.read(nameAttribute, readBuffer);
		readBuffer.flip();
		String valueFromAttributes = new String(readBuffer.array(), "UTF-8");
		logger.debug(nameAttribute + ": " + valueFromAttributes);
		return valueFromAttributes;
	}
}
