package cow.cruciality;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.interfaces.Controller;

public class CrucialityListener implements ActionListener {
	private CrucialityController c;

	public CrucialityListener(Controller c) {
		this.c = (CrucialityController) c;
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
