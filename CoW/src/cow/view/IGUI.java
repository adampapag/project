package cow.view;

import java.awt.event.ActionListener;

public interface IGUI {
	void initializeGUI();

	void addActionListener(ActionListener l);
}
