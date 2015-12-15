package cow.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MorphismGUI implements IGUI {

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
	@Override
	public void initializeGUI() {
		frame = new JFrame("CoW\t-\tWorking with Morphisms");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		addMenu();

		addButtons();

		addLabels();

		addFields();

		addMorphismTable();

		addResultsPane();

		// separator
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 177, 688, 16);
		frame.getContentPane().add(separator);

		frame.setVisible(true);
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
		JButton btnPrint = new JButton("Show");
		btnPrint.setBounds(339, 196, 117, 29);
		frame.getContentPane().add(btnPrint);
		buttonList.add(btnPrint);

		JButton btnExport = new JButton("Export");
		btnExport.setBounds(504, 196, 117, 29);
		frame.getContentPane().add(btnExport);
		buttonList.add(btnExport);
	}

	private void addLabels() {
		JLabel lblAlphabet = new JLabel("Alphabet");
		lblAlphabet.setBounds(35, 21, 61, 16);
		frame.getContentPane().add(lblAlphabet);

		JLabel lblNumberOfLetters = new JLabel("Number of letters in alphabet");
		lblNumberOfLetters.setBounds(45, 49, 199, 16);
		frame.getContentPane().add(lblNumberOfLetters);

		JLabel lblMorphism = new JLabel("Morphism");
		lblMorphism.setBounds(359, 21, 88, 16);
		frame.getContentPane().add(lblMorphism);

		JLabel lblFromIteration = new JLabel("from iteration");
		lblFromIteration.setBounds(60, 201, 96, 16);
		frame.getContentPane().add(lblFromIteration);

		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(220, 201, 24, 16);
		frame.getContentPane().add(lblTo);
	}

	private void addFields() {
		JTextField alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(240, 43, 41, 28);
		frame.getContentPane().add(alphabetField);

		JTextField fromField = new JTextField();
		fromField.setBounds(168, 195, 41, 28);
		frame.getContentPane().add(fromField);
		fromField.setColumns(3);

		JTextField toField = new JTextField();
		toField.setColumns(3);
		toField.setBounds(240, 195, 41, 28);
		frame.getContentPane().add(toField);
	}

	private void addMorphismTable() {
		JTable table = new JTable(data, columnNames);
		JScrollPane morphismPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		morphismPane.setBounds(369, 49, 306, 116);
		frame.getContentPane().add(morphismPane);
	}

	private void addResultsPane() {
		JScrollPane resultsPane = new JScrollPane();
		resultsPane.setBounds(6, 229, 688, 272);
		frame.getContentPane().add(resultsPane);
	}
}
