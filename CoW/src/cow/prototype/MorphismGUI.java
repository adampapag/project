package cow.prototype;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MorphismGUI {

	private JFrame frame;
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
	String columnNames[] = { "letter", "morphism" };
	Object[][] data = { { 1, 123 }, { 2, 131 } };

	/**
	 * Create the application.
	 */
	public MorphismGUI() {
		initializeGUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeGUI() {
		frame = new JFrame("CoW\t-\tWorking with Morphisms");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Add menu
		frame.setJMenuBar(new MenuBar());

		// Alphabet label
		JLabel lblAlphabet = new JLabel("Alphabet");
		lblAlphabet.setBounds(35, 21, 61, 16);
		frame.getContentPane().add(lblAlphabet);

		// Number of letters in alphabet
		JLabel lblNumberOfLetters = new JLabel("Number of letters in alphabet");
		lblNumberOfLetters.setBounds(45, 49, 199, 16);
		frame.getContentPane().add(lblNumberOfLetters);

		JTextField alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(240, 43, 41, 28);
		frame.getContentPane().add(alphabetField);

		// Morphism label
		JLabel lblMorphism = new JLabel("Morphism");
		lblMorphism.setBounds(359, 21, 88, 16);
		frame.getContentPane().add(lblMorphism);

		// Morphism table
		JTable table = new JTable(data, columnNames);
		JScrollPane morphismPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		morphismPane.setBounds(369, 49, 306, 116);
		frame.getContentPane().add(morphismPane);

		// from iteration
		JLabel lblFromIteration = new JLabel("from iteration");
		lblFromIteration.setBounds(60, 201, 96, 16);
		frame.getContentPane().add(lblFromIteration);

		JTextField fromField = new JTextField();
		fromField.setBounds(168, 195, 41, 28);
		frame.getContentPane().add(fromField);
		fromField.setColumns(3);

		// to iteration
		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(220, 201, 24, 16);
		frame.getContentPane().add(lblTo);

		JTextField toField = new JTextField();
		toField.setColumns(3);
		toField.setBounds(240, 195, 41, 28);
		frame.getContentPane().add(toField);

		// buttons
		JButton btnPrint = new JButton("Show");
		btnPrint.setBounds(339, 196, 117, 29);
		frame.getContentPane().add(btnPrint);
		buttonList.add(btnPrint);

		JButton btnExport = new JButton("Export");
		btnExport.setBounds(504, 196, 117, 29);
		frame.getContentPane().add(btnExport);
		buttonList.add(btnExport);

		// separator
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 177, 688, 16);
		frame.getContentPane().add(separator);

		// morphism results
		JScrollPane resultsPane = new JScrollPane();
		resultsPane.setBounds(6, 229, 688, 272);
		frame.getContentPane().add(resultsPane);

		frame.setVisible(true);
	}
}
