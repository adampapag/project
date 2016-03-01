package cow.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import cow.controller.MorphismListener;

public class MorphismGUI implements IGUI {

	private JFrame frame;
	private JTextField alphabetField;
	private JTextField fromField;
	private JTextField toField;
	private JTextArea resultsArea;
	private JTable morphismTable;
	private JScrollPane morphismPane;
	private MorphismListener morphismListener;
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
	String columnNames[] = { "letter", "word" };
	Object[][] data = {};

	/**
	 * Create the application.
	 */
	public MorphismGUI() {
		morphismListener = new MorphismListener();
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
		frame.setResizable(false);

		addMenu();

		addButtons();

		addLabels();

		addFields();

		addMorphismTable();

		addMorphismListener();

		addResultsPane();

		// separator
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 177, 688, 16);
		frame.getContentPane().add(separator);

		frame.setVisible(true);
	}

	private void addMorphismTable() {
		morphismTable = new JTable(data, columnNames);
		morphismTable.setGridColor(Color.BLACK);
		morphismTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		morphismTable.setCellSelectionEnabled(true);
		morphismTable.setFillsViewportHeight(true);
		morphismPane = new JScrollPane(morphismTable);
		morphismPane.setBounds(369, 49, 306, 116);
		frame.getContentPane().add(morphismPane);
	}

	private void addMorphismListener() {
		morphismListener.setGUI(this);
		morphismListener.setTextField(alphabetField);
		// alphabetField.getDocument().addDocumentListener(morphismListener);
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
			frame.getContentPane().remove(morphismPane);
			addMorphismTable();
		} catch (NumberFormatException nfe) {
			// TODO
			System.out.println("number format exception; morphismGUI");
		}
	}

	public JTable getMorphismTable() {
		return morphismTable;
	}

	public JTextField getFromIterationField() {
		return fromField;
	}

	public JTextField getToIterationField() {
		return toField;
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
		JButton btnPrint = new JButton("Show3");
		btnPrint.setBounds(284, 250, 117, 29);
		buttonList.add(btnPrint);
		frame.getContentPane().add(btnPrint);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(284, 477, 117, 29);
		buttonList.add(btnSave);
		frame.getContentPane().add(btnSave);

		// JButton btnExport = new JButton("Export");
		// btnExport.setBounds(504, 196, 117, 29);
		// frame.getContentPane().add(btnExport);
		// buttonList.add(btnExport);
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
		alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(240, 43, 41, 28);
		alphabetField.getDocument().addDocumentListener(morphismListener);
		frame.getContentPane().add(alphabetField);

		fromField = new JTextField();
		fromField.setBounds(168, 195, 41, 28);
		frame.getContentPane().add(fromField);
		fromField.setColumns(3);

		toField = new JTextField();
		toField.setColumns(3);
		toField.setBounds(240, 195, 41, 28);
		frame.getContentPane().add(toField);
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
