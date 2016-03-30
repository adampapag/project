package cow.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.controller.AbstractController;
import cow.controller.CrucialityController;

/**
 * Concrete implementation of the ActionListener interface. Provides responses
 * for input in the View.
 * 
 * @author Adam Papageorgiou
 *
 * @see ActionListener
 * @see CrucialityView
 */
public class CrucialityListener implements ActionListener {
	private CrucialityController c;

	/**
	 * Casts the Controller to the relevant Cruciality type.
	 * 
	 * 
	 * @param c
	 *            reference to the Controller
	 * 
	 * @see AbstractController
	 */
	public CrucialityListener(AbstractController c) {
		this.c = (CrucialityController) c;
	}

	/**
	 * Provides reaction to each button click in the View, calling the
	 * appropriate method in the Controller.
	 * 
	 * @see CrucialityView
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
