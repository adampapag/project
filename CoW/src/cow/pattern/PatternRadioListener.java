package cow.pattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatternRadioListener implements ActionListener {

	private PatternView gui;

	public PatternRadioListener(PatternView gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		switch (button) {
		case "ordered":
			gui.setOrdered();
			break;
		case "unordered":
			gui.setUnordered();
			break;
		case "avoidance":
			gui.setAvoidance();
			break;
		case "distribution":
			gui.setDistribution();
			break;
		case "number words":
			gui.setNumberWords();
			break;
		case "print words":
			gui.setPrintWords();
		case "on words":
			gui.setOnWords();
			break;
		case "text":
			gui.setText();
			break;
		}
	}

}