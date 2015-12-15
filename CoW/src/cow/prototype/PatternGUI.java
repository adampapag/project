package cow.prototype;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PatternGUI {

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// Add menu
		frame.setJMenuBar(new MenuBar());

		// Alphabet label
		JLabel lblAlphabet = new JLabel("Words");
		lblAlphabet.setBounds(59, 146, 61, 16);
		frame.getContentPane().add(lblAlphabet);
		// Number of letters in alphabet
		JLabel lblNumberOfLetters = new JLabel("Size of alphabet:");
		lblNumberOfLetters.setBounds(59, 167, 122, 16);
		frame.getContentPane().add(lblNumberOfLetters);

		JTextField alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(182, 161, 41, 28);
		frame.getContentPane().add(alphabetField);

		// Morphism label
		JLabel lblMorphism = new JLabel("Text");
		lblMorphism.setBounds(359, 144, 88, 16);
		frame.getContentPane().add(lblMorphism);

		// frame.getContentPane().add(morphismPane);

		// from iteration
		JLabel lblFromIteration = new JLabel("Pattern: ");
		lblFromIteration.setBounds(45, 27, 96, 16);
		frame.getContentPane().add(lblFromIteration);

		JTextField fromField = new JTextField();
		fromField.setBounds(112, 18, 252, 35);
		frame.getContentPane().add(fromField);
		fromField.setColumns(3);

		// buttons
		JButton btnPrint = new JButton("Show");
		btnPrint.setBounds(339, 257, 117, 29);
		frame.getContentPane().add(btnPrint);

		JButton btnExport = new JButton("Export");
		btnExport.setBounds(504, 257, 117, 29);
		frame.getContentPane().add(btnExport);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 239, 688, 16);
		frame.getContentPane().add(separator);

		JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
		separator2.setBounds(339, 126, 16, 120);
		frame.getContentPane().add(separator2);

		// morphism results
		JScrollPane resultsPane = new JScrollPane();
		resultsPane.setBounds(6, 289, 688, 212);
		frame.getContentPane().add(resultsPane);

		JRadioButton rdbtnOrdered = new JRadioButton("Ordered");
		rdbtnOrdered.setBounds(55, 65, 141, 23);
		frame.getContentPane().add(rdbtnOrdered);

		JRadioButton rdbtnUnordered = new JRadioButton("Unordered");
		rdbtnUnordered.setBounds(55, 100, 141, 23);
		frame.getContentPane().add(rdbtnUnordered);

		JTextField textField = new JTextField();
		textField.setBounds(359, 172, 278, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblFromFile = new JLabel("From file: ");
		lblFromFile.setBounds(359, 221, 88, 16);
		frame.getContentPane().add(lblFromFile);

		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBounds(548, 216, 117, 29);
		frame.getContentPane().add(btnChooseFile);

		JLabel lblfilepath = new JLabel("/filepath");
		lblfilepath.setBounds(459, 221, 70, 16);
		frame.getContentPane().add(lblfilepath);

		JLabel lblLengthOfWords = new JLabel("Length of words:");
		lblLengthOfWords.setBounds(59, 210, 117, 16);
		frame.getContentPane().add(lblLengthOfWords);

		JTextField textField_1 = new JTextField();
		textField_1.setColumns(3);
		textField_1.setBounds(182, 204, 41, 28);
		frame.getContentPane().add(textField_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 122, 688, 12);
		frame.getContentPane().add(separator_1);

		frame.setVisible(true);
	}

}
