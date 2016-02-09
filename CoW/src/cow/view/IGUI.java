package cow.view;

import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public interface IGUI {
	void initializeGUI();

	void addActionListener(ActionListener l);

	public JTextArea getResultsArea();
}
