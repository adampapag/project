package cow.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cow.controller.CrucialityRadioListener;
import cow.controller.PatternListener;
import cow.controller.prototype.MenuBar;

public class CrucialityGUI implements IGUI {

	private JFrame frame;
	private JRadioButton rdbtnOrdered;
	private JRadioButton rdbtnUnordered;
	private JRadioButton rdbtnOnWords;
	private JRadioButton rdbtnText;
	private JTextField patternField;
	private JTextField textField;
	private JTextField alphabetField;
	private JTextField fromField;
	private JTextField toField;
	private JTextArea resultsArea;
	private JTable patternTable;
	private JScrollPane patternPane;
	private PatternListener patternListener;
	private ActionListener radioListener;
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
	private boolean ordered;
	private boolean onWords;
	private boolean text;
	String columnNames[] = { "restricted patterns" };
	Object[][] data = {};

	/**
	 * Create the application.
	 */
	public CrucialityGUI() {
		radioListener = new CrucialityRadioListener(this);
		patternListener = new PatternListener();
		initializeGUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@Override
	public void initializeGUI() {
		frame = new JFrame("CoW\t-\tCruciality");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		addMenu();

		addButtons();

		addLabels();

		addFields();

		addPatternTable();

		addPatternListener();

		addResultsPane();

		// separator
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

	private void addPatternTable() {
		patternTable = new JTable(data, columnNames);
		patternPane = new JScrollPane(patternTable);
		patternTable.setGridColor(Color.BLACK);
		patternTable.setFillsViewportHeight(true);
		patternPane.setBounds(279, 27, 122, 97);
		frame.getContentPane().add(patternPane);
	}

	private void addPatternListener() {
		patternListener.setGUI(this);
		patternListener.setTextField(patternField);
	}

	public void setTable(String size) {
		try {
			int alphabetSize = Integer.parseInt(size);
			data = new Object[alphabetSize][columnNames.length];
			for (int row = 0; row < data.length; row++) {
				for (int column = 0; column < columnNames.length; column++) {
					data[row][column] = "";
				}
			}
			frame.getContentPane().remove(patternPane);
			addPatternTable();
		} catch (NumberFormatException nfe) {
			// TODO
			System.out.println("number format exception; crucialityGUI");
		}
	}

	public void setOnWords() {
		rdbtnText.setSelected(false);
		rdbtnOnWords.setSelected(true);
		text = false;
		onWords = true;
	}

	public void setText() {
		rdbtnOnWords.setSelected(false);
		rdbtnText.setSelected(true);
		onWords = false;
		text = true;
	}

	public String OnWordsOrText() {
		if (onWords && (!text)) {
			return "on words";
		} else {
			assert text && (!onWords);
			return "text";
		}
	}

	public boolean isOrdered() {
		return ordered;
	}

	public void setOrdered() {
		rdbtnUnordered.setSelected(false);
		rdbtnOrdered.setSelected(true);
		ordered = true;
	}

	public void setUnordered() {
		rdbtnOrdered.setSelected(false);
		rdbtnUnordered.setSelected(true);
		ordered = false;
	}

	public JTable getPatternTable() {
		return patternTable;
	}

	public int getAlphabetSize() {
		return Integer.parseInt(alphabetField.getText());
	}

	public int getFromLength() {
		return Integer.parseInt(fromField.getText());
	}

	public int getToLength() {
		return Integer.parseInt(toField.getText());
	}

	@Override
	public void addActionListener(ActionListener l) {
		for (JButton b : buttonList) {
			b.addActionListener(l);
		}
	}

	private void addMenu() {
		frame.setJMenuBar(new MenuBar());
	}

	private void addButtons() {
		rdbtnOrdered = new JRadioButton("Ordered");
		rdbtnOrdered.setBounds(413, 6, 85, 23);
		rdbtnOrdered.setActionCommand("ordered");
		rdbtnOrdered.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnOrdered);

		rdbtnUnordered = new JRadioButton("Unordered");
		rdbtnUnordered.setBounds(165, 6, 98, 23);
		rdbtnUnordered.setSelected(true);
		rdbtnUnordered.setActionCommand("unordered");
		rdbtnUnordered.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnUnordered);

		rdbtnOnWords = new JRadioButton("On Words");
		rdbtnOnWords.setBounds(165, 79, 94, 23);
		rdbtnOnWords.setActionCommand("on words");
		rdbtnOnWords.setSelected(true);
		rdbtnOnWords.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnOnWords);

		rdbtnText = new JRadioButton("Text");
		rdbtnText.setBounds(413, 79, 94, 23);
		rdbtnText.setActionCommand("text");
		rdbtnText.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnText);

		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBounds(577, 213, 117, 29);
		buttonList.add(btnChooseFile);
		frame.getContentPane().add(btnChooseFile);

		JButton btnPrint = new JButton("Show4");
		btnPrint.setBounds(284, 250, 117, 29);
		buttonList.add(btnPrint);
		frame.getContentPane().add(btnPrint);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(284, 477, 117, 29);
		buttonList.add(btnSave);
		frame.getContentPane().add(btnSave);
	}

	private void addLabels() {
		JLabel lblNumberOfLetters = new JLabel("Number of patterns");
		lblNumberOfLetters.setBounds(45, 49, 199, 16);
		frame.getContentPane().add(lblNumberOfLetters);

		JLabel lblWords = new JLabel("On Words over {0, 1, ..., k}");
		lblWords.setBounds(46, 141, 170, 16);
		frame.getContentPane().add(lblWords);

		JLabel lblAlphabetSize = new JLabel("k = ");
		lblAlphabetSize.setBounds(46, 162, 122, 16);
		frame.getContentPane().add(lblAlphabetSize);

		JLabel lblLengthOfWords = new JLabel("Length of words:");
		lblLengthOfWords.setBounds(46, 205, 117, 16);
		frame.getContentPane().add(lblLengthOfWords);

		JLabel lblFrom = new JLabel("from");
		lblFrom.setBounds(162, 205, 30, 16);
		frame.getContentPane().add(lblFrom);

		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(247, 205, 16, 16);
		frame.getContentPane().add(lblTo);

		JLabel lblText = new JLabel("Text/Word");
		lblText.setBounds(388, 141, 88, 16);
		frame.getContentPane().add(lblText);

		JLabel lblFromFile = new JLabel("From file: ");
		lblFromFile.setBounds(388, 218, 88, 16);
		frame.getContentPane().add(lblFromFile);

		JLabel lblFilepath = new JLabel("/filepath");
		lblFilepath.setBounds(488, 218, 70, 16);
		frame.getContentPane().add(lblFilepath);
	}

	private void addFields() {
		patternField = new JTextField();
		patternField.setColumns(3);
		patternField.setBounds(240, 43, 41, 28);
		patternField.getDocument().addDocumentListener(patternListener);
		frame.getContentPane().add(patternField);

		alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(180, 156, 41, 28);
		frame.getContentPane().add(alphabetField);

		fromField = new JTextField();
		fromField.setColumns(3);
		fromField.setBounds(203, 199, 41, 28);
		frame.getContentPane().add(fromField);

		toField = new JTextField();
		toField.setColumns(3);
		toField.setBounds(267, 199, 41, 28);
		frame.getContentPane().add(toField);

		textField = new JTextField();
		textField.setBounds(388, 169, 278, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

	private void addResultsPane() {
		resultsArea = new JTextArea();
		JScrollPane resultsPane = new JScrollPane(resultsArea);
		resultsPane.setBounds(6, 280, 688, 195);
		frame.getContentPane().add(resultsPane);
	}

	public JTextArea getResultsArea() {
		return resultsArea;
	}
}
