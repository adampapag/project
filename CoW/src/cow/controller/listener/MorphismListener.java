package cow.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.controller.AbstractController;
import cow.controller.MorphismController;

/**
 * Concrete implementation of the ActionListener interface. Provides responses
 * for input in the View.
 * 
 * @author Adam Papageorgiou
 *
 * @see ActionListener
 * @see MorphismView
 */
public class MorphismListener implements ActionListener {

	private MorphismController c;

	/**
	 * Casts the Controller to the relevant MorphismLab type.
	 * 
	 * 
	 * @param c
	 *            reference to the Controller
	 * 
	 * @see AbstractController
	 */
	public MorphismListener(AbstractController c) {
		this.c = (MorphismController) c;
	}

	/**
	 * Provides reaction to each button click in the View, calling the
	 * appropriate method in the Controller.
	 * 
	 * @see MorphismView
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case "Show":
			c.showResults();
			break;
		case "Load Morphism":
			c.loadMorphism();
			break;
		case "Save Morphism":
			c.saveMorphism();
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
