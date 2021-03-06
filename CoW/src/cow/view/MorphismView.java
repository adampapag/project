package cow.view;

import java.awt.Color;
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

import cow.controller.listener.table.TableListener;

public class MorphismView extends AbstractCoWViewWithImportAndTable {

	private JFrame frame;
	private JTextField alphabetField;
	private JTextField fromField;
	private JTextField toField;
	private JTextArea resultsArea;
	private JTable morphismTable;
	private JScrollPane morphismPane;
	private TableListener tableListener;
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
	String columnNames[] = { "letter", "word" };
	Object[][] data = {};

	/**
	 * Create the application.
	 */
	public MorphismView() {
		tableListener = new TableListener();
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

		super.addFrame(frame);

		addButtons();

		addLabels();

		addFields();

		addMorphismTable();

		addMorphismTableListener();

		addResultsPane();

		// separators
		JSeparator verticalSeparator = new JSeparator(SwingConstants.VERTICAL);
		verticalSeparator.setBounds(339, 0, 16, 245);
		frame.getContentPane().add(verticalSeparator);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 239, 688, 16);
		frame.getContentPane().add(separator);

		super.setFrame(frame);
		
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

	private void addMorphismTableListener() {
		tableListener.setGUI(this);
		tableListener.setTextField(alphabetField);
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
			// do nothing.
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

	protected void addButtons() {
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

		JButton btnLoadMorphism = new JButton("Load Morphism");
		btnLoadMorphism.setBounds(521, 193, 135, 29);
		buttonList.add(btnLoadMorphism);
		frame.getContentPane().add(btnLoadMorphism);

		JButton btnSaveMorphism = new JButton("Save Morphism");
		btnSaveMorphism.setBounds(381, 193, 135, 29);
		buttonList.add(btnSaveMorphism);
		frame.getContentPane().add(btnSaveMorphism);

		super.addButtonList(buttonList);

	}

	protected void addLabels() {
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

	protected void addFields() {
		alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(240, 87, 41, 28);
		alphabetField.getDocument().addDocumentListener(tableListener);
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

	protected void addResultsPane() {
		super.addResultsArea(resultsArea);
		super.addResultsPane();
	}

	@Override
	public void setText(String text) {
		// no implementation
	}

}
