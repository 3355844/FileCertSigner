package swing.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.log4j.Logger;

import util.Const;
import util.FileWorker;

public class FileInterface {

	private static final JFrame frame = new JFrame("File signer");
	private static final Logger logger = Logger.getLogger(FileInterface.class);
	private static final String[] TABLE_COL = { "File Name", "SIGN" };

	public static void showMainScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width - 100, screenSize.height - 100);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		addPanel();
	}

	public static void addPanel() {
		JPanel panel = new JPanel();
		frame.add(panel);
		addButtons(panel);
		
	}

	public static void addButtons(JPanel panel) {
		JButton showFiles = new JButton("Files");
		panel.add(showFiles);
		showFiles.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 showAllFilesInDir(Const.ROOT_FILE_DIR);
			}
		});
//		
//		JButton addFile = new JButton("AddFile");
//		panel.add(addFile);

	}

	public static void writeToFile(String path, byte[] key) {
		File f = new File(path);
		f.getParentFile().mkdirs();
		try (FileOutputStream fos = new FileOutputStream(f)) {
			fos.write(key);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void showAllFilesInDir(String dirPath) {
		logger.debug("get files from dir");
		String[] files = FileWorker.getFileListFromDir(dirPath);
		String[][] data = convert(files);
		JTable table = new JTable(data, TABLE_COL);
		JScrollPane scrolePane = new JScrollPane(table);
		JFrame frame = new JFrame("File LIST");
		frame.setSize(800, 200);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.add(scrolePane);

	}

	private static String[][] convert(String[] files) {
		String[][] result = new String[files.length][TABLE_COL.length];
		for (int i = 0; i < result.length; i++) {
			result[i][0] = files[i];
		}
		return result;
	}

	public static void fileDownloadDialog(String saveDir) {
		logger.debug("begin file download dialog");
		JFileChooser chooser = new JFileChooser();
		frame.add(chooser);
		int value = chooser.showOpenDialog(null);
		if (value == JFileChooser.APPROVE_OPTION) {
			fileSaveProc(saveDir, chooser);
		} else {
			logger.debug("File download Error!");
		}
		logger.debug("End file download dialog");
	}

	private static void fileSaveProc(String saveDir, JFileChooser chooser) {
		File file = chooser.getSelectedFile();
		String filePath = saveDir + file.getName();
		try {
			FileWorker.writeToFile(filePath, Files.readAllBytes(file.toPath()));
			logger.debug("File downloaded successful, file name:" + file.getName());
		} catch (IOException e) {
			logger.error("Error Write file {}", e);
		}
	}
}
