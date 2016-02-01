package cow.view;

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

import cow.controller.RadioListener;

public class PatternGUI implements IGUI {

	private JFrame frame;
	private ActionListener radioListener;
	private ActionListener buttonListener;
	private JRadioButton rdbtnOrdered;
	private JRadioButton rdbtnUnordered;
	private JTextField patternField;
	private JTextField textField;
	private JTextField alphabetField;
	private JTextField wordField;
	private JTextArea resultsArea;
	private ArrayList<JButton> buttonList;
	private boolean ordered;

	/**
	 * Create the application.
	 */
	public PatternGUI() {
		radioListener = new RadioListener(this);
		buttonList = new ArrayList<JButton>();
		initializeGUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeGUI() {
		frame = new JFrame("CoW\t-\tIdentifying Patterns");
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

	public String getPattern() {
		return patternField.getText();
	}

	public String getText() {
		return textField.getText();
	}

	public int getAlphabetSize() {
		return Integer.parseInt(alphabetField.getText());
	}

	public int getWordLength() {
		return Integer.parseInt(wordField.getText());
	}

	public void setOrdered(boolean selected) {

		if (selected == true) {
			rdbtnOrdered.setSelected(true);
			rdbtnUnordered.setSelected(false);
			ordered = true;
		} else {
			assert selected == false : "selected = " + selected
					+ " ; should be false ";
			rdbtnOrdered.setSelected(false);
			rdbtnUnordered.setSelected(true);
			ordered = false;
		}
	}

	public boolean isOrdered() {
		return ordered;
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
		rdbtnOrdered = new JRadioButton("Ordered");
		rdbtnOrdered.setBounds(229, 44, 141, 23);
		rdbtnOrdered.setActionCommand("ordered");
		rdbtnOrdered.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnOrdered);

		rdbtnUnordered = new JRadioButton("Unordered");
		rdbtnUnordered.setBounds(344, 44, 141, 23);
		rdbtnUnordered.setSelected(true);
		rdbtnUnordered.setActionCommand("unordered");
		rdbtnUnordered.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnUnordered);

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

		alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(546, 156, 41, 28);
		frame.getContentPane().add(alphabetField);

		wordField = new JTextField();
		wordField.setColumns(3);
		wordField.setBounds(546, 199, 41, 28);
		frame.getContentPane().add(wordField);

		patternField = new JTextField();
		patternField.setBounds(229, 79, 252, 35);
		frame.getContentPane().add(patternField);
		patternField.setColumns(3);

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
