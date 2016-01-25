package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.model.IModel;
import cow.view.IView;
import cow.view.PatternGUI;

public class ButtonListener implements ActionListener {

	private IModel m;
	private IView v;

	public ButtonListener(IModel m, IView v) {
		this.m = m;
		this.v = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		System.out.println("Button pressed: " + button);
		switch (button) {
		case "Show":
			PatternGUI gui;
			try {
				assert v.getGUI() instanceof PatternGUI : "GUI should be of type PatternGUI; is of type "
						+ v.getGUI().getClass().getName();
				gui = (PatternGUI) v.getGUI();
				// check if ordered//unordered
				if (gui.isOrdered()) {
					System.out.println("ordered!");
				} else {
					assert gui.isOrdered() == false : "";
					System.out.println("unordered!");
					gui.displayResults(m.unorderedPatternRequest(
							gui.getPattern(), gui.getText()));
				}
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
