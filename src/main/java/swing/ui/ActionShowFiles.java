package swing.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.log4j.Logger;

import util.Const;
import util.FileWorker;

public class ActionShowFiles implements ActionListener {

	private static final Logger logger = Logger.getLogger(ActionShowFiles.class);
	private static final String[] TABLE_COL = { "File Name", "SIGN" };

	@Override
	public void actionPerformed(ActionEvent arg0) {
		showAllFilesInDir(Const.ROOT_FILE_DIR);
	}

	public static void showAllFilesInDir(String dirPath) {
		logger.debug("get files from dir");
		String[] files = FileWorker.getFileListFromDir(dirPath);
		String[][] data = convert(files);
		JFrame frame = new JFrame("Clicked");
		frame.setVisible(true);
		frame.setSize(200, 200);
		JTable table = new JTable(data, TABLE_COL);
		JScrollPane scrolePane = new JScrollPane(table);
		frame.add(scrolePane);

	}

	private static String[][] convert(String[] files) {
		String[][] result = new String[files.length][TABLE_COL.length];
		for (int i = 0; i < result.length; i++) {
			result[i][0] = files[i];
		}
		return result;
	}
}
