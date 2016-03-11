package cow.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class FactorComplexityGUI implements IGUI {

	private JFrame frame;
	private ActionListener buttonListener;
	private JTextArea textArea;
	private JTextArea resultsArea;
	private ArrayList<JButton> buttonList;

	/**
	 * Create the application.
	 */
	public FactorComplexityGUI() {
		buttonList = new ArrayList<JButton>();
		initializeGUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeGUI() {
		frame = new JFrame("CoW\t-\tFactor Complexity");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

//		addMenu();

		addButtons();

		addLabels();

		addFields();

		addResultsPane();

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 239, 688, 16);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 189, 688, 12);
		frame.getContentPane().add(separator_1);

		frame.setVisible(true);
	}

	public String getText() {
		return textArea.getText();
	}

	@Override
	public void addActionListener(ActionListener l) {
		buttonListener = l;
		for (JButton b : buttonList) {
			b.addActionListener(buttonListener);
		}
	}

	private void addMenu() {
		frame.setJMenuBar(new MenuBar());
	}

	private void addButtons() {

		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBounds(210, 213, 117, 29);
		buttonList.add(btnChooseFile);
		frame.getContentPane().add(btnChooseFile);

		JButton btnPrint = new JButton("Show2");
		btnPrint.setBounds(284, 250, 117, 29);
		buttonList.add(btnPrint);
		frame.getContentPane().add(btnPrint);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(284, 477, 117, 29);
		buttonList.add(btnSave);
		frame.getContentPane().add(btnSave);
	}

	private void addLabels() {
		JLabel lblText = new JLabel("Text");
		lblText.setBounds(21, 17, 88, 16);
		frame.getContentPane().add(lblText);

		JLabel lblFromFile = new JLabel("From file: ");
		lblFromFile.setBounds(21, 218, 88, 16);
		frame.getContentPane().add(lblFromFile);

		JLabel lblFilepath = new JLabel("/filepath");
		lblFilepath.setBounds(121, 218, 70, 16);
		frame.getContentPane().add(lblFilepath);
	}

	private void addFields() {

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(false);
		JScrollPane textPane = new JScrollPane(textArea);
		textPane.setBounds(60, 45, 586, 119);
		frame.getContentPane().add(textPane);

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
