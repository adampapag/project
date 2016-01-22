package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.view.IView;
import cow.view.PatternGUI;

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
			PatternGUI gui;
			try {
				assert v.getGUI() instanceof PatternGUI : "GUI should be of type PatternGUI; is of type "
						+ v.getGUI().getClass().getName();
				gui = (PatternGUI) v.getGUI();
				// check if ordered//unordered
				// if (unordered) {
				// gui.displayResults(m.resolvePatternEnquiry(gui.getPattern(),
				// // gui.getText()));
				// } else {
				// assert ordered
				// gui.displayResults(m.resolvePatternEnquiry(gui.getAlphabetSize(),
				// gui.getWordLength()));}
				// gui.displayResults(m.resolvePatternEnquiry(gui.getPattern(),
				// gui.getText()));
			} catch (NumberFormatException nfe) {
				System.out.println("Error: Text in number field!");
				// inform user
			}
			break;
		}

	}

}
