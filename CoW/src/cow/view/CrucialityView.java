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

import cow.controller.listener.radio.CrucialityRadioListener;
import cow.controller.listener.table.TableListener;

public class CrucialityView extends AbstractCoWViewWithImportAndTable {

	private JFrame frame;
	private JRadioButton rdbtnOrdered;
	private JRadioButton rdbtnUnordered;
	private JRadioButton rdbtnOnWords;
	private JRadioButton rdbtnText;
	private JRadioButton rdbtnNumberWords;
	private JRadioButton rdbtnPrintWords;
	private JTextField patternField;
	private JTextField textField;
	private JTextField alphabetField;
	private JTextField fromField;
	private JTextField toField;
	private JLabel lblFilepath;
	private JTextArea resultsArea;
	private JTable patternTable;
	private JScrollPane patternPane;
	private TableListener tableListener;
	private ActionListener radioListener;
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
	private boolean ordered;
	private boolean onWords = true;
	private boolean text;
	private boolean printWords = true;
	private boolean numberWords;
	String columnNames[] = { "restricted patterns" };
	Object[][] data = {};

	/**
	 * Create the application.
	 */
	public CrucialityView() {
		radioListener = new CrucialityRadioListener(this);
		tableListener = new TableListener();
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

		super.addFrame(frame);

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

	public void setText(String text) {
		textField.setText(text);
	}

	public String getText() {
		return textField.getText();
	}

	private void addPatternTable() {
		patternTable = new JTable(data, columnNames);
		patternPane = new JScrollPane(patternTable);
		patternTable.setGridColor(Color.BLACK);
		patternTable.setFillsViewportHeight(true);
		patternPane.setBounds(279, 31, 122, 90);
		frame.getContentPane().add(patternPane);
	}

	private void addPatternListener() {
		tableListener.setGUI(this);
		tableListener.setTextField(patternField);
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
			System.out.println("number format exception; cruciality view");
		}
	}

	public void setPrintWords() {
		rdbtnNumberWords.setSelected(false);
		rdbtnPrintWords.setSelected(true);
		numberWords = false;
		printWords = true;
	}

	public void setNumberWords() {
		rdbtnPrintWords.setSelected(false);
		rdbtnNumberWords.setSelected(true);
		printWords = false;
		numberWords = true;
	}

	public String printWordsOrNumberWords() {
		if (printWords && (!numberWords)) {
			return "print words";
		} else {
			assert numberWords && (!printWords);
			return "number words";
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

	protected void addButtons() {
		rdbtnOrdered = new JRadioButton("Ordered");
		rdbtnOrdered.setBounds(413, 28, 85, 23);
		rdbtnOrdered.setActionCommand("ordered");
		rdbtnOrdered.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnOrdered);

		rdbtnUnordered = new JRadioButton("Unordered");
		rdbtnUnordered.setBounds(165, 28, 98, 23);
		rdbtnUnordered.setSelected(true);
		rdbtnUnordered.setActionCommand("unordered");
		rdbtnUnordered.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnUnordered);

		rdbtnOnWords = new JRadioButton("On Words");
		rdbtnOnWords.setBounds(165, 98, 94, 23);
		rdbtnOnWords.setActionCommand("on words");
		rdbtnOnWords.setSelected(true);
		rdbtnOnWords.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnOnWords);

		rdbtnText = new JRadioButton("Text");
		rdbtnText.setBounds(413, 98, 94, 23);
		rdbtnText.setActionCommand("text");
		rdbtnText.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnText);

		rdbtnPrintWords = new JRadioButton("Print Words");
		rdbtnPrintWords.setBounds(190, 214, 105, 23);
		rdbtnPrintWords.setActionCommand("print words");
		rdbtnPrintWords.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnPrintWords);

		rdbtnNumberWords = new JRadioButton("Find Number Words");
		rdbtnNumberWords.setBounds(31, 214, 160, 23);
		rdbtnNumberWords.setActionCommand("number words");
		rdbtnNumberWords.setSelected(true);
		rdbtnNumberWords.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnNumberWords);

		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBounds(577, 213, 117, 29);
		buttonList.add(btnChooseFile);
		frame.getContentPane().add(btnChooseFile);

		JButton btnPrint = new JButton("Show");
		btnPrint.setActionCommand("Show");
		btnPrint.setBounds(219, 250, 117, 29);
		buttonList.add(btnPrint);
		frame.getContentPane().add(btnPrint);

		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(357, 250, 117, 29);
		buttonList.add(btnStop);
		frame.getContentPane().add(btnStop);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(284, 493, 117, 29);
		buttonList.add(btnSave);
		frame.getContentPane().add(btnSave);

		super.addButtonList(buttonList);
	}

	protected void addLabels() {
		JLabel lblNumberOfLetters = new JLabel("Number of patterns:");
		lblNumberOfLetters.setBounds(260, 8, 134, 16);
		frame.getContentPane().add(lblNumberOfLetters);

		JLabel lblWords = new JLabel("On Words over {0, 1, ..., k}");
		lblWords.setBounds(46, 141, 170, 16);
		frame.getContentPane().add(lblWords);

		JLabel lblAlphabetSize = new JLabel("k = ");
		lblAlphabetSize.setBounds(45, 164, 57, 16);
		frame.getContentPane().add(lblAlphabetSize);

		JLabel lblLengthOfWords = new JLabel("Length of words:");
		lblLengthOfWords.setBounds(45, 191, 117, 16);
		frame.getContentPane().add(lblLengthOfWords);

		JLabel lblFrom = new JLabel("from");
		lblFrom.setBounds(161, 191, 30, 16);
		frame.getContentPane().add(lblFrom);

		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(246, 191, 16, 16);
		frame.getContentPane().add(lblTo);

		JLabel lblText = new JLabel("Text/Word");
		lblText.setBounds(388, 141, 88, 16);
		frame.getContentPane().add(lblText);

		JLabel lblFromFile = new JLabel("From file: ");
		lblFromFile.setBounds(388, 218, 88, 16);
		frame.getContentPane().add(lblFromFile);

		lblFilepath = new JLabel("");
		lblFilepath.setBounds(455, 218, 120, 16);
		frame.getContentPane().add(lblFilepath);

		super.addLabel(lblFilepath);
	}

	protected void addFields() {
		patternField = new JTextField();
		patternField.setColumns(3);
		patternField.setBounds(391, 0, 41, 28);
		patternField.getDocument().addDocumentListener(tableListener);
		frame.getContentPane().add(patternField);

		alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(120, 158, 41, 28);
		frame.getContentPane().add(alphabetField);

		fromField = new JTextField();
		fromField.setColumns(3);
		fromField.setBounds(202, 185, 41, 28);
		frame.getContentPane().add(fromField);

		toField = new JTextField();
		toField.setColumns(3);
		toField.setBounds(266, 185, 41, 28);
		frame.getContentPane().add(toField);

		textField = new JTextField();
		textField.setBounds(388, 169, 278, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

	protected void addResultsPane() {
		super.addResultsArea(resultsArea);
		super.addResultsPane();
	}

}
