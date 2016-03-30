package cow.controller.listener.radio;

import java.awt.event.ActionListener;

import cow.view.View;

/**
 * Abstract base class for a JRadioButton Listener.
 * 
 * @author Adam Papageorgiou
 *
 * @see ActionListener
 * @see View
 */
public abstract class AbstractRadioListener implements ActionListener {

	private View v;

	public AbstractRadioListener(View v) {
		this.v = v;
	}
}
