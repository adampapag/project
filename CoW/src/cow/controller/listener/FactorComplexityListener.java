package cow.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.controller.Controller;
import cow.controller.FactorComplexityController;

public class FactorComplexityListener implements ActionListener {
	private FactorComplexityController c;

	public FactorComplexityListener(Controller c) {
		this.c = (FactorComplexityController) c;
	}

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
