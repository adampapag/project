package cow.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.controller.AbstractController;
import cow.controller.CoWLabController;

/**
 * Concrete implementation of the ActionListener interface. Provides responses
 * for input in the View.
 * 
 * @author Adam Papageorgiou
 *
 * @see ActionListener
 * @see CoWLabView
 */
public class CoWLabListener implements ActionListener {

	private CoWLabController c;

	/**
	 * Casts the Controller to the relevant CoWLab type.
	 * 
	 * 
	 * @param c
	 *            reference to the Controller
	 * 
	 * @see AbstractController
	 */
	public CoWLabListener(AbstractController c) {
		this.c = (CoWLabController) c;
	}

	/**
	 * Provides reaction to each button click in the View, calling the
	 * appropriate method in the Controller.
	 * 
	 * @see CoWLabView
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String type = e.getActionCommand();
		switch (type) {
		case "Morphisms":
			c.createMorphismWindow();
			break;
		case "Patterns":
			c.createPatternWindow();
			break;
		case "Factor Complexity":
			c.createFactorComplexityWindow();
			break;
		case "Cruciality":
			c.createCrucialityWindow();
			break;
		}
	}
}
