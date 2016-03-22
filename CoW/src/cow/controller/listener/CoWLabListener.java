package cow.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.controller.CoWLabController;
import cow.controller.Controller;

public class CoWLabListener implements ActionListener {

	private CoWLabController c;

	public CoWLabListener(Controller c) {
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
