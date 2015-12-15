package cow.view;

import java.awt.event.ActionListener;

public interface IGUI {
	abstract void initializeGUI();

	void addActionListener(ActionListener l);
}
