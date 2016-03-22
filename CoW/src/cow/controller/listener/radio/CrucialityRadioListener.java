package cow.controller.listener.radio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.view.CrucialityView;

public class CrucialityRadioListener implements ActionListener {

	private CrucialityView gui;

	public CrucialityRadioListener(CrucialityView gui) {
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
		case "on words":
			gui.setOnWords();
			break;
		case "text":
			gui.setText();
			break;
		case "print words":
			gui.setPrintWords();
			break;
		case "number words":
			gui.setNumberWords();
			break;
		}

	}
}