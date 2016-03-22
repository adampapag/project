package cow.morphism;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.interfaces.Controller;

public class MorphismListener implements ActionListener {

	private MorphismController c;

	public MorphismListener(Controller c) {
		this.c = (MorphismController) c;
	}

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
