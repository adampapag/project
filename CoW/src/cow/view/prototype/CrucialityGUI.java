package cow.view.prototype;

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

import cow.controller.MorphismListener;
import cow.controller.prototype.MenuBar;
import cow.view.IGUI;

public class CrucialityGUI implements IGUI {

	private JFrame frame;
	private JRadioButton rdbtnOrdered;
	private JRadioButton rdbtnUnordered;
	private JTextField textField;
	private JTextField alphabetField;
	private JTextField fromField;
	private JTextField toField;
	private JTextArea resultsArea;
	private JTable patternTable;
	private JScrollPane patternPane;
	private MorphismListener morphismListener;
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
	String columnNames[] = { "restricted patterns" };
	Object[][] data = {};

	/**
	 * Create the application.
	 */
	public CrucialityGUI() {
		morphismListener = new MorphismListener();
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

		addMenu();

		addButtons();

		addLabels();

		addFields();

		addPatternTable();

		addMorphismListener();

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
		patternTable.setFillsViewportHeight(true);
		patternPane.setBounds(279, 27, 122, 97);
		frame.getContentPane().add(patternPane);
	}

	private void addMorphismListener() {
		// morphismListener.setGUI(this);
		morphismListener.setTextField(alphabetField);
		alphabetField.getDocument().addDocumentListener(morphismListener);
	}

	public void setTable(String alphaSize) {
		try {
			int alphabetSize = Integer.parseInt(alphaSize);
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

	public JTable getPatternTable() {
		return patternTable;
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
		// rdbtnOrdered.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnOrdered);

		rdbtnUnordered = new JRadioButton("Unordered");
		rdbtnUnordered.setBounds(165, 6, 98, 23);
		rdbtnUnordered.setSelected(true);
		rdbtnUnordered.setActionCommand("unordered");
		// rdbtnUnordered.addActionListener(radioListener);
		frame.getContentPane().add(rdbtnUnordered);

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
		JLabel lblWords = new JLabel("On Words");
		lblWords.setBounds(46, 141, 61, 16);
		frame.getContentPane().add(lblWords);

		JLabel lblAlphabetSize = new JLabel("Size of alphabet:");
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
	}

	private void addFields() {
		alphabetField = new JTextField();
		alphabetField.setColumns(3);
		alphabetField.setBounds(180, 156, 41, 28);
		alphabetField.getDocument().addDocumentListener(morphismListener);
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
