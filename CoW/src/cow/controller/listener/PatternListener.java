package cow.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.controller.AbstractController;
import cow.controller.PatternController;

/**
 * Concrete implementation of the ActionListener interface. Provides responses
 * for input in the View.
 * 
 * @author Adam Papageorgiou
 *
 * @see ActionListener
 * @see PatternView
 */
public class PatternListener implements ActionListener {

	private PatternController c;

	/**
	 * Casts the Controller to the relevant Pattern type.
	 * 
	 * 
	 * @param c
	 *            reference to the Controller
	 * 
	 * @see AbstractController
	 */
	public PatternListener(AbstractController c) {
		this.c = (PatternController) c;
	}

	/**
	 * Provides reaction to each button click in the View, calling the
	 * appropriate method in the Controller.
	 * 
	 * @see PatternView
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case "Show":
			c.showResults();
			break;
		case "Choose File":
			c.chooseFile();
			break;
		case "Save":
			c.saveResults();
			break;
		case "Stop":
			c.stop();
			break;
		}
	}
}
