package cow.view.prototype;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cow.controller.prototype.MenuBar;
import cow.view.IGUI;

public class FactorComplexityGUI implements IGUI {

	private JFrame frame;
	private ActionListener buttonListener;
	private JTextField textField;
	private JTextArea resultsArea;
	private ArrayList<JButton> buttonList;

	/**
	 * Create the application.
	 */
	public FactorComplexityGUI() {
		buttonList = new ArrayList<JButton>();
		initializeGUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeGUI() {
		frame = new JFrame("CoW\t-\tFactor Complexity");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		addMenu();

		addButtons();

		addLabels();

		addFields();

		addResultsPane();

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 239, 688, 16);
		frame.getContentPane().add(separator);

		JSeparator verticalSeparator = new JSeparator(SwingConstants.VERTICAL);
		verticalSeparator.setBounds(339, 126, 16, 120);
		frame.getContentPane().add(verticalSeparator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 122, 688, 12);
		frame.getContentPane().add(separator_1);

		frame.setVisible(true);
	}

	public String getText() {
		return textField.getText();
	}

	@Override
	public void addActionListener(ActionListener l) {
		buttonListener = l;
		for (JButton b : buttonList) {
			b.addActionListener(buttonListener);
		}
	}

	private void addMenu() {
		frame.setJMenuBar(new MenuBar());
	}

	private void addButtons() {

		JRadioButton rdbtnAvoidance = new JRadioButton("Avoidance");
		rdbtnAvoidance.setBounds(137, 9, 141, 23);
		rdbtnAvoidance.setActionCommand("avoidance");
		// rdbtnAvoidance.addActionListener("////");
		frame.getContentPane().add(rdbtnAvoidance);

		JRadioButton rdbtnDistribution = new JRadioButton("Distribution");
		rdbtnDistribution.setBounds(287, 9, 141, 23);
		rdbtnDistribution.setActionCommand("distribution");
		// rdbtnDistribution.addActionListener(////);
		frame.getContentPane().add(rdbtnDistribution);

		JRadioButton rdbtnOnWords = new JRadioButton("On Words");
		rdbtnOnWords.setBounds(437, 9, 141, 23);
		rdbtnOnWords.setActionCommand("on words");
		// rdbtnOnWords.addActionListener(////);
		frame.getContentPane().add(rdbtnOnWords);

		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBounds(210, 213, 117, 29);
		buttonList.add(btnChooseFile);
		frame.getContentPane().add(btnChooseFile);

		JButton btnPrint = new JButton("Show");
		btnPrint.setBounds(203, 254, 117, 29);
		buttonList.add(btnPrint);
		frame.getContentPane().add(btnPrint);

		JButton btnExport = new JButton("Export");
		btnExport.setBounds(368, 254, 117, 29);
		buttonList.add(btnExport);
		frame.getContentPane().add(btnExport);
	}

	private void addLabels() {
		JLabel lblPattern = new JLabel("Pattern: ");
		lblPattern.setBounds(162, 88, 96, 16);
		frame.getContentPane().add(lblPattern);

		JLabel lblWords = new JLabel("Words");
		lblWords.setBounds(423, 141, 61, 16);
		frame.getContentPane().add(lblWords);

		JLabel lblLengthOfWords = new JLabel("Length of words:");
		lblLengthOfWords.setBounds(423, 205, 117, 16);
		frame.getContentPane().add(lblLengthOfWords);

		JLabel lblAlphabetSize = new JLabel("Size of alphabet:");
		lblAlphabetSize.setBounds(423, 162, 122, 16);
		frame.getContentPane().add(lblAlphabetSize);

		JLabel lblText = new JLabel("Text");
		lblText.setBounds(21, 141, 88, 16);
		frame.getContentPane().add(lblText);

		JLabel lblFromFile = new JLabel("From file: ");
		lblFromFile.setBounds(21, 218, 88, 16);
		frame.getContentPane().add(lblFromFile);

		JLabel lblFilepath = new JLabel("/filepath");
		lblFilepath.setBounds(121, 218, 70, 16);
		frame.getContentPane().add(lblFilepath);
	}

	private void addFields() {

		textField = new JTextField();
		textField.setBounds(21, 169, 278, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

	}

	private void addResultsPane() {
		resultsArea = new JTextArea();
		JScrollPane resultsPane = new JScrollPane(resultsArea);
		resultsPane.setBounds(6, 289, 688, 212);
		frame.getContentPane().add(resultsPane);
	}

	public JTextArea getResultsArea() {
		return resultsArea;
	}
}
