package cow.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cow.controller.listener.radio.PatternRadioListener;

public class PatternView extends AbstractCoWViewWithImport {

	private JFrame frame;
	private JRadioButton rdbtnOrdered;
	private JRadioButton rdbtnUnordered;
	private JRadioButton rdbtnAvoidance;
	private JRadioButton rdbtnDistribution;
	private JRadioButton rdbtnNumberWords;
	private JRadioButton rdbtnPrintWords;
	private JRadioButton rdbtnOnWords;
	private JRadioButton rdbtnText;
	private JTextField patternField;
	private JTextField textField;
	private JTextField alphabetField;
	private JTextField fromField;
	private JTextField toField;
	private JTextArea resultsArea;
	private JLabel lblFilepath;
	private ActionListener radioListener;
	private ArrayList<JButton> buttonList;
	private boolean ordered;
	private boolean onWords = true;
	private boolean text;
	private boolean avoidance = true;
	private boolean distribution;
	private boolean numberWords = true;
	private boolean printWords;

	/**
	 * Create the application.
	 */
	public PatternView() {
		radioListener = new PatternRadioListener(this);
		buttonList = new ArrayList<JButton>();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeGUI() {
		frame = new JFrame("CoW\t-\tIdentifying Patterns");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		super.addFrame(frame);

		addButtons();

		addLabels();

		addFields();

		addResultsPane();
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(6, 100, 688, 12);
		frame.getContentPane().add(separator1);

		JSeparator verticalSeparator = new JSeparator(SwingConstants.VERTICAL);
		verticalSeparator.setBounds(339, 104, 16, 140);
		frame.getContentPane().add(verticalSeparator);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(6, 239, 688, 16);
		frame.getContentPane().add(separator2);

		frame.setVisible(true);
	}

	public String getPattern() {
		return patternField.getText();
	}

	public void setText(String text) {
		textField.setText(text);
	}

	public String getText() {
		return textField.getText();
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

	public void setAvoidance() {
		rdbtnDistribution.setSelected(false);
		rdbtnAvoidance.setSelected(true);
		distribution = false;
		avoidance = true;
	}

	public void setDistribution() {
		rdbtnAvoidance.setSelected(false);
		rdbtnDistribution.setSelected(true);
		avoidance = false;
		distribution = true;
	}

	public void setNumberWords() {
		rdbtnPrintWords.setSelected(false);
		rdbtnNumberWords.setSelected(true);
		printWords = false;
		numberWords = true;
	}

	public void setPrintWords() {
		rdbtnNumberWords.setSelected(false);
		rdbtnPrintWords.setSelected(true);
		numberWords = false;
		printWords = true;
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

	public boolean isOrdered() {
		return ordered;
	}

	public String onWordsOrText() {
		if (onWords && (!text)) {
			return "on words";
		} else {
			assert text && (!onWords);
			return "text";
		}
	}

	public String avoidanceOrDistribution() {
		if (avoidance && (!distribution)) {
			return "avoidance";
		} else {
			assert (distribution && (!avoidance));
			return "distribution";
		}
	}

	public String numberWordsOrPrintWords() {
		if (numberWords && (!printWords)) {
			return "number words";
		} else {
			assert printWords && (!numberWords);
			return "print words";
		}
	}

	protected void addButtons() {
		rdbtnOrdered = new JRadioButton("Ordered");
		rdbtnOrdered.setBounds(393, 9, 85, 23);
		rdbtnOrdered.setActionCommand("ordered");
		rdbtnOrdered.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnOrdered);

		rdbtnUnordered = new JRadioButton("Unordered");
		rdbtnUnordered.setBounds(210, 9, 98, 23);
		rdbtnUnordered.setSelected(true);
		rdbtnUnordered.setActionCommand("unordered");
		rdbtnUnordered.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnUnordered);

		rdbtnAvoidance = new JRadioButton("Avoidance");
		rdbtnAvoidance.setBounds(56, 128, 98, 23);
		rdbtnAvoidance.setActionCommand("avoidance");
		rdbtnAvoidance.setSelected(true);
		rdbtnAvoidance.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnAvoidance);

		rdbtnDistribution = new JRadioButton("Distribution");
		rdbtnDistribution.setBounds(178, 128, 110, 23);
		rdbtnDistribution.setActionCommand("distribution");
		rdbtnDistribution.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnDistribution);

		rdbtnNumberWords = new JRadioButton("Find Number Words");
		rdbtnNumberWords.setActionCommand("number words");
		rdbtnNumberWords.setBounds(30, 214, 156, 23);
		rdbtnNumberWords.setSelected(true);
		rdbtnNumberWords.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnNumberWords);

		rdbtnPrintWords = new JRadioButton("Print Words");
		rdbtnPrintWords.setActionCommand("print words");
		rdbtnPrintWords.setBounds(198, 214, 110, 23);
		rdbtnPrintWords.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnPrintWords);

		rdbtnOnWords = new JRadioButton("On Words");
		rdbtnOnWords.setBounds(210, 79, 94, 23);
		rdbtnOnWords.setActionCommand("on words");
		rdbtnOnWords.setSelected(true);
		rdbtnOnWords.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnOnWords);

		rdbtnText = new JRadioButton("Text");
		rdbtnText.setBounds(393, 79, 94, 23);
		rdbtnText.setActionCommand("text");
		rdbtnText.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnText);

		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBounds(577, 213, 117, 29);
		buttonList.add(btnChooseFile);
		frame.getContentPane().add(btnChooseFile);

		JButton btnPrint = new JButton("Show");
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
		JLabel lblPattern = new JLabel("Pattern: ");
		lblPattern.setBounds(162, 47, 96, 16);
		frame.getContentPane().add(lblPattern);

		JLabel lblWords = new JLabel("On Words over {0, 1, ..., k}");
		lblWords.setBounds(46, 112, 170, 16);
		frame.getContentPane().add(lblWords);

		JLabel lblAlphabetSize = new JLabel("k = ");
		lblAlphabetSize.setBounds(124, 160, 41, 16);
		frame.getContentPane().add(lblAlphabetSize);

		JLabel lblLengthOfWords = new JLabel("Length of words:");
		lblLengthOfWords.setBounds(46, 190, 117, 16);
		frame.getContentPane().add(lblLengthOfWords);

		JLabel lblFrom = new JLabel("from");
		lblFrom.setBounds(162, 190, 30, 16);
		frame.getContentPane().add(lblFrom);

		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(247, 190, 16, 16);
		frame.getContentPane().add(lblTo);

		JLabel lblText = new JLabel("Text/Word");
		lblText.setBounds(388, 114, 88, 16);
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
		patternField.setBounds(229, 38, 252, 35);
		frame.getContentPane().add(patternField);
		patternField.setColumns(3);

		alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(160, 154, 41, 28);
		frame.getContentPane().add(alphabetField);

		fromField = new JTextField();
		fromField.setColumns(3);
		fromField.setBounds(203, 184, 41, 28);
		frame.getContentPane().add(fromField);

		toField = new JTextField();
		toField.setColumns(3);
		toField.setBounds(267, 184, 41, 28);
		frame.getContentPane().add(toField);

		textField = new JTextField();
		textField.setBounds(388, 159, 278, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

	}

	protected void addResultsPane() {
		super.addResultsArea(resultsArea);
		super.addResultsPane();
	}

}
