package cow.controller.prototype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.view.IView;

public class MenuListener implements ActionListener {

	private IView v;
	private ActionListener buttonListener;

	public MenuListener(IView v, ActionListener buttonListener) {
		this.v = v;
		this.buttonListener = buttonListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		v.setGUI(e.getActionCommand());
		v.getGUI().addActionListener(buttonListener);
	}

}
