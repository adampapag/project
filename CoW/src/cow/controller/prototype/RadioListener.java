package cow.controller.prototype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.view.PatternGUI;

public class RadioListener implements ActionListener {

	private PatternGUI gui;

	public RadioListener(PatternGUI gui) {
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
		}
	}

}