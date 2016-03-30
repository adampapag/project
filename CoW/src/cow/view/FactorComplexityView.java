package cow.view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class FactorComplexityView extends AbstractCoWViewWithImport {

	private JFrame frame;
	private JTextArea textArea;
	private JTextArea resultsArea;
	private JLabel lblFilepath;
	private ArrayList<JButton> buttonList;

	/**
	 * Create the application.
	 */
	public FactorComplexityView() {
		buttonList = new ArrayList<JButton>();
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

		super.addFrame(frame);

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

		super.setFrame(frame);
		
		frame.setVisible(true);
	}

	public JTextArea getTextField() {
		return textArea;
	}

	public void setText(String text) {
		textArea.setText(text);
	}

	public String getText() {
		return textArea.getText();
	}

	protected void addButtons() {

		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBounds(210, 213, 117, 29);
		buttonList.add(btnChooseFile);
		frame.getContentPane().add(btnChooseFile);

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

		super.addButtonList(buttonList);
	}

	protected void addLabels() {
		JLabel lblText = new JLabel("Text");
		lblText.setBounds(21, 17, 88, 16);
		frame.getContentPane().add(lblText);

		JLabel lblFromFile = new JLabel("From file: ");
		lblFromFile.setBounds(21, 218, 88, 16);
		frame.getContentPane().add(lblFromFile);

		lblFilepath = new JLabel("");
		lblFilepath.setBounds(88, 218, 120, 16);
		frame.getContentPane().add(lblFilepath);

		super.addLabel(lblFilepath);
	}

	protected void addFields() {

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(false);
		JScrollPane textPane = new JScrollPane(textArea);
		textPane.setBounds(60, 45, 586, 119);
		frame.getContentPane().add(textPane);

	}

	protected void addResultsPane() {
		super.addResultsArea(resultsArea);
		super.addResultsPane();
	}

}
