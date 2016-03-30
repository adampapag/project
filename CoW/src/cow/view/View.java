package cow.view;

import java.awt.event.ActionListener;

/**
 * Interface through which all further View implementation is implemented.
 * 
 * @author Adam Papageorgiou
 * 
 *
 */
public interface View {

	/**
	 * The method signature for building and showing the GUI. Strategy
	 * algorithm, different implementations of which are executed at run-time.
	 */
	void initializeGUI();

	void addActionListener(ActionListener l);
}
