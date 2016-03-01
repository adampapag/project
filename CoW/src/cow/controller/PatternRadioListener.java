package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.view.PatternGUI;

public class PatternRadioListener implements ActionListener {

	private PatternGUI gui;

	public PatternRadioListener(PatternGUI gui) {
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
		case "on words":
			gui.setOnWords();
			break;
		case "text":
			gui.setText();
			break;
		}
	}

}
