package cow.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.controller.AbstractController;
import cow.controller.FactorComplexityController;

/**
 * Concrete implementation of the ActionListener interface. Provides responses
 * for input in the View.
 * 
 * @author Adam Papageorgiou
 *
 * @see ActionListener
 * @see FactorComplexityView
 */
public class FactorComplexityListener implements ActionListener {
	private FactorComplexityController c;

	/**
	 * Casts the Controller to the relevant FactorComplexity type.
	 * 
	 * 
	 * @param c
	 *            reference to the Controller
	 * 
	 * @see AbstractController
	 */
	public FactorComplexityListener(AbstractController c) {
		this.c = (FactorComplexityController) c;
	}

	/**
	 * Provides reaction to each button click in the View, calling the
	 * appropriate method in the Controller.
	 * 
	 * @see FactorComplexityView
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
