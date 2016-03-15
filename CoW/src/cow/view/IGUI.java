package cow.view;

import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public interface IGUI {
	void initializeGUI();

	void addActionListener(ActionListener l);

	void setText(String text);

	public JTextArea getResultsArea();

	void setFile(String path);
}
