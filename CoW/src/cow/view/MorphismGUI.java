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
import javax.swing.SwingConstants;

import cow.controller.MorphismListener;
import cow.view.IGUI;

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

		// addMenu();

		addButtons();

		addLabels();

		addFields();

		addMorphismTable();

		addMorphismListener();

		addResultsPane();

		JSeparator verticalSeparator = new JSeparator(SwingConstants.VERTICAL);
		verticalSeparator.setBounds(339, 0, 16, 245);
		frame.getContentPane().add(verticalSeparator);

		// separator
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 239, 688, 16);
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

	public void setTable(String[] morphismData) {
		data = new Object[morphismData.length / 2][columnNames.length];
		int index = 0;
		for (int row = 0; row < data.length; row++) {
			for (int column = 0; column < columnNames.length; column++) {
				data[row][column] = morphismData[index];
				index++;
			}
		}
		frame.getContentPane().remove(morphismPane);
		addMorphismTable();
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
		JButton btnPrint = new JButton("Show");
		btnPrint.setActionCommand("Show3");
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

		JButton btnLoadMorphism = new JButton("Load Morphism");
		btnLoadMorphism.setBounds(521, 193, 135, 29);
		buttonList.add(btnLoadMorphism);
		frame.getContentPane().add(btnLoadMorphism);

		JButton btnSaveMorphism = new JButton("Save Morphism");
		btnSaveMorphism.setBounds(381, 193, 135, 29);
		buttonList.add(btnSaveMorphism);
		frame.getContentPane().add(btnSaveMorphism);

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
		lblNumberOfLetters.setBounds(35, 93, 199, 16);
		frame.getContentPane().add(lblNumberOfLetters);

		JLabel lblMorphism = new JLabel("Morphism");
		lblMorphism.setBounds(369, 21, 88, 16);
		frame.getContentPane().add(lblMorphism);

		JLabel lblFromIteration = new JLabel("from iteration");
		lblFromIteration.setBounds(60, 143, 96, 16);
		frame.getContentPane().add(lblFromIteration);

		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(220, 143, 24, 16);
		frame.getContentPane().add(lblTo);
	}

	private void addFields() {
		alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(240, 87, 41, 28);
		alphabetField.getDocument().addDocumentListener(morphismListener);
		frame.getContentPane().add(alphabetField);

		fromField = new JTextField();
		fromField.setBounds(168, 137, 41, 28);
		frame.getContentPane().add(fromField);
		fromField.setColumns(3);

		toField = new JTextField();
		toField.setColumns(3);
		toField.setBounds(240, 137, 41, 28);
		frame.getContentPane().add(toField);
	}

	private void addResultsPane() {
		resultsArea = new JTextArea();
		JScrollPane resultsPane = new JScrollPane(resultsArea);
		resultsPane.setBounds(6, 280, 688, 210);
		frame.getContentPane().add(resultsPane);
	}

	public JTextArea getResultsArea() {
		return resultsArea;
	}

	public void setText(String text) {
		// TODO
	}

	@Override
	public void setFile(String path) {
		// TODO Auto-generated method stub
		
	}
}
