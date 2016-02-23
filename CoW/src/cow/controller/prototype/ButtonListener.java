package cow.controller.prototype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

	// public ButtonListener(Model m, IGUI gui) {
	//
	// }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		switch (button) {
		case "Show":
//			m.identifyPattern(gui.getPattern(), gui.getText());
			break;
		}

	}

}
