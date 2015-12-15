package cow.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PatternGUI implements IGUI {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public PatternGUI() {
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

	@Override
	public void addActionListener(ActionListener l) {
		// for (JButton b : buttonList) {
		// b.addActionListener(l);
		// }
	}

	private void addMenu() {
		frame.setJMenuBar(new MenuBar());
	}

	private void addButtons() {
		JRadioButton rdbtnOrdered = new JRadioButton("Ordered");
		rdbtnOrdered.setBounds(55, 65, 141, 23);
		frame.getContentPane().add(rdbtnOrdered);

		JRadioButton rdbtnUnordered = new JRadioButton("Unordered");
		rdbtnUnordered.setBounds(55, 100, 141, 23);
		frame.getContentPane().add(rdbtnUnordered);

		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBounds(548, 216, 117, 29);
		frame.getContentPane().add(btnChooseFile);

		JButton btnPrint = new JButton("Show");
		btnPrint.setBounds(339, 257, 117, 29);
		frame.getContentPane().add(btnPrint);

		JButton btnExport = new JButton("Export");
		btnExport.setBounds(504, 257, 117, 29);
		frame.getContentPane().add(btnExport);
	}

	private void addLabels() {
		JLabel lblPattern = new JLabel("Pattern: ");
		lblPattern.setBounds(45, 27, 96, 16);
		frame.getContentPane().add(lblPattern);

		JLabel lblWords = new JLabel("Words");
		lblWords.setBounds(59, 146, 61, 16);
		frame.getContentPane().add(lblWords);

		JLabel lblLengthOfWords = new JLabel("Length of words:");
		lblLengthOfWords.setBounds(59, 210, 117, 16);
		frame.getContentPane().add(lblLengthOfWords);

		JLabel lblAlphabetSize = new JLabel("Size of alphabet:");
		lblAlphabetSize.setBounds(59, 167, 122, 16);
		frame.getContentPane().add(lblAlphabetSize);

		JLabel lblText = new JLabel("Text");
		lblText.setBounds(359, 144, 88, 16);
		frame.getContentPane().add(lblText);

		JLabel lblFromFile = new JLabel("From file: ");
		lblFromFile.setBounds(359, 221, 88, 16);
		frame.getContentPane().add(lblFromFile);

		JLabel lblFilepath = new JLabel("/filepath");
		lblFilepath.setBounds(459, 221, 70, 16);
		frame.getContentPane().add(lblFilepath);
	}

	private void addFields() {

		JTextField alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(182, 161, 41, 28);
		frame.getContentPane().add(alphabetField);

		JTextField patternField = new JTextField();
		patternField.setBounds(112, 18, 252, 35);
		frame.getContentPane().add(patternField);
		patternField.setColumns(3);

		JTextField textField = new JTextField();
		textField.setBounds(359, 172, 278, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JTextField wordField = new JTextField();
		wordField.setColumns(3);
		wordField.setBounds(182, 204, 41, 28);
		frame.getContentPane().add(wordField);
	}

	private void addResultsPane() {
		JScrollPane resultsPane = new JScrollPane();
		resultsPane.setBounds(6, 289, 688, 212);
		frame.getContentPane().add(resultsPane);
	}
}
