package cow.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public abstract class AbstractCoWView extends AbstractView {

	private JFrame frame;
	private JTextArea resultsArea;

	public void addFrame(JFrame frame) {
		this.frame = frame;
	}

	public void addResultsArea(JTextArea resultsArea) {
		this.resultsArea = resultsArea;
	}

	public JTextArea getResultsArea() {
		return resultsArea;
	}

	protected void addResultsPane() {
		resultsArea = new JTextArea();
		JScrollPane resultsPane = new JScrollPane(resultsArea);
		resultsPane.setBounds(6, 280, 688, 210);
		frame.getContentPane().add(resultsPane);
	}

	protected abstract void addLabels();

	protected abstract void addFields();

}
