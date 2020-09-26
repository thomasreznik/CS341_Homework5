package reznik;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.stage.FileChooser;

import javax.swing.JButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class fileMain {

	public JFrame frame;
	public JButton btnBuild;
	public JTextArea FileInput;
	public JTextArea OutputCalc;
	public static String fileContent = "";
	public LinkedList list;
	public Scanner scan;
	public JButton fileBTN;
	public String pathway;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileMain window = new fileMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public fileMain() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 52, 423, 86);
		frame.getContentPane().add(scrollPane);

		FileInput = new JTextArea();
		scrollPane.setViewportView(FileInput);
		FileInput.setEditable(false);

		btnBuild = new JButton("Calculate");
		btnBuild.setBounds(167, 150, 117, 29);
		frame.getContentPane().add(btnBuild);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 191, 423, 81);
		frame.getContentPane().add(scrollPane_1);

		OutputCalc = new JTextArea();
		scrollPane_1.setViewportView(OutputCalc);
		OutputCalc.setEditable(false);

		fileBTN = new JButton("Select File");
		fileBTN.setBounds(167, 11, 117, 29);
		frame.getContentPane().add(fileBTN);

		list = new LinkedList();

	}

	public void createEvents() {
		fileBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("C:/Users/thomasreznik/desktop"));
				fc.setDialogTitle("File Directory");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fc.setFileFilter(filter);
				if (fc.showOpenDialog(fileBTN) == JFileChooser.APPROVE_OPTION) {
					pathway = fc.getSelectedFile().getAbsolutePath();
				}
				System.out.println("You chose" + fc.getSelectedFile().getAbsolutePath());
				try {

					File file = new File(pathway);

					scan = new Scanner(file);

					String fromFile = scanning();

					if (!fromFile.equals("invalid")) {
						FileInput.setText(fromFile);
					} else {
						FileInput.setText(fromFile);
						OutputCalc
								.setText("You either put a space in the list or put a invalid number, such as letters");
						return;
					}

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildOutput();

			}
		});
	}

	// reads the file
	public String scanning() {

		while (scan.hasNext()) {

			String single = scan.nextLine();

			if (isNumeric(single)) {
				int value = Integer.parseInt(single);
				list.append(value);
				fileContent += value + "\n";
			} else {
				OutputCalc.setText("no");
				return "invalid";

			}

		}
		return fileContent; // content from file
	}

//Building the output screen
	public void buildOutput() {
		double mean = list.mean();
		double deviate = list.s_deviation(mean);
		OutputCalc.setText("This is the mean: " + mean + "\n" + "This is the standard deviation: " + deviate);
	}

	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
