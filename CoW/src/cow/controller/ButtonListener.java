package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.view.IView;

public class ButtonListener implements ActionListener {

	private IView v;

	public ButtonListener(IView v) {
		this.v = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		switch (button) {
		case "Show":
			System.out.println("Show pressed in PatternGUI");
//			String pattern = v.getGUI().getPattern();
			// gui.displayResults(m.resolvePatternEnquiry(gui.getPattern(),
			// gui.getText()));
			break;
		}

	}

}
