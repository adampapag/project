package cow.controller.listener;

import java.awt.event.ActionEvent;

import cow.controller.CoWLabController;
import cow.controller.Controller;

public class CoWLabListener extends AbstractListener {

	private CoWLabController c;

	public CoWLabListener(Controller c) {
		super(c);
		this.c = (CoWLabController) c;
	}

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
