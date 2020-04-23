package swing.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import util.Const;
import util.FileWorker;

public class FileInterface extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(FileInterface.class);

//	private static final JFrame ROOT_FRAME = new JFrame("File signer");
//	private static final JRootPane ROOT_PANE = new JRootPane();
//	private static final JLayeredPane LAY_PANE = new JLayeredPane();
	private static final String[] TABLE_COL = { "NAME", "SIGN", "VERIFIED"};
//	private JPanel topPanel;
//	private JPanel btnPanel;
	public static JScrollPane scrollPane = new JScrollPane();
//	private JTable table;
	private JButton loadFileBtn = addFileBtn(this);
	private JButton generateReportBtn = new JButton("Generate Report");
	private JButton exitBtn = new JButton("Exit");
//	private JLabel fileNameLbl = new JLabel("File Name Here");
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMI = new JMenu("File");
	private JMenuItem openFileMenu = new JMenuItem("Open File");
	private JSeparator separator = new JSeparator();
	private JMenuItem exitMenu = new JMenuItem("Exit");
	private JMenu reportMI = new JMenu("Report");
	private JMenuItem generateReportMenu = new JMenuItem("Generate Report");
	private JMenu helpMI = new JMenu("Help");
	private JMenuItem aboutMenu = new JMenuItem("About");
	public static JTable table = getTablePanel();
	private JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
	public static JPanel panel1 = new JPanel(new BorderLayout());
	private JPanel panel2 = new JPanel(new GridLayout(1, 2));
	private JPanel panel3 = new JPanel(new GridLayout());

	public FileInterface() {
		super("File signer");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width / 2;
		int height = screenSize.height / 2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(width, height));
		setResizable(false);
		setLayout(new BorderLayout(10, 10));

//		fileMI.add(openFileMenu);
//		fileMI.add(separator);
//		fileMI.add(exitMenu);

//		reportMI.add(generateReportMenu);

//		helpMI.add(aboutMenu);

//		menuBar.add(fileMI);
//		menuBar.add(reportMI);
//		menuBar.add(helpMI);

//		setJMenuBar(menuBar);
		scrollPane = new JScrollPane(table);
		panel1.add(scrollPane);

//		panel2.add(fileNameLbl);
		panel3.add(loadFileBtn);

//		panel3.add(generateReportBtn);
//		panel3.add(exitBtn);

		mainPanel.add(panel1, BorderLayout.CENTER);
//		mainPanel.add(panel2, BorderLayout.NORTH);
		mainPanel.add(panel3, BorderLayout.SOUTH);

		add(mainPanel);
	}

//	public static void init() {
//		logger.debug("begin init");
//
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int winWith = screenSize.width / 2;
//		int winHeight = screenSize.height / 2;
//		FileInterface window = new FileInterface();
//		window.setTitle("FILE SIGNER");
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setSize(winWith, winWith);
//		window.setVisible(true);
//		window.add(addBtnPanel());
//		window.add(getTablePanel());
//		logger.debug("end init");
//	}

	private static JTable getTablePanel() {
		logger.debug("get files table from dir");
		String[] files = FileWorker.getFileListFromDir(Const.ROOT_FILE_DIR);
		String[][] data = convert(files);
		JTable table = new JTable(data, TABLE_COL);
		logger.debug("get files table from dir");
		return table;
	}

	private static String[][] convert(String[] files) {
		String[][] result = new String[files.length][TABLE_COL.length];
		for (int i = 0; i < result.length; i++) {
			result[i][0] = files[i];
		}
		return result;
	}

//	public static JPanel addBtnPanel() {
//		JPanel panel = new JPanel();
//		showFilesBtn(panel);
//		addFileBtn(panel);
//		return panel;
//	}

	public static JButton addFileBtn(final FileInterface fileInterface) {
		JButton addFile = new JButton("ADD FILE");
		fileInterface.add(addFile);
		addFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fileDownloadDialog(fileInterface, Const.ROOT_FILE_DIR);
				scrollPane = new JScrollPane(getTablePanel());
				panel1.removeAll();
				panel1.add(scrollPane, BorderLayout.CENTER);
				panel1.revalidate();
				panel1.repaint();
			}
		});

		return addFile;
	}
//
//	public static void showFilesBtn(JPanel panel) {
//		JButton showFiles = new JButton("VIEW FILES");
//		panel.add(showFiles);
//		showFiles.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				showAllFilesInDir(Const.ROOT_FILE_DIR);
//			}
//		});
//	}
//
//	public static void showAllFilesInDir(String dirPath) {
//		logger.debug("get files from dir");
//		String[] files = FileWorker.getFileListFromDir(dirPath);
//		String[][] data = convert(files);
//		JTable table = new JTable(data, TABLE_COL);
//		JScrollPane scrolePane = new JScrollPane(table);
//		JFrame frame = new JFrame("File LIST");
//		frame.setSize(800, 200);
//		frame.setVisible(true);
//		frame.setLocationRelativeTo(null);
//		frame.add(scrolePane);
//
//	}

	public static void fileDownloadDialog(JFrame frame, String saveDir) {
		logger.debug("begin file download dialog");
		JFileChooser chooser = new JFileChooser();
		frame.add(chooser);
		int value = chooser.showOpenDialog(null);
		if (value == JFileChooser.APPROVE_OPTION) {
			fileSaveProc(saveDir, chooser);
		} else {
			logger.info("Downloading canceled");
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
