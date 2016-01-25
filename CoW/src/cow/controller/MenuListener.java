package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.model.IModel;
import cow.view.IView;

public class MenuListener implements ActionListener {

	private IModel m;
	private IView v;
	private ActionListener buttonListener;

	public MenuListener(IModel m, IView v, ActionListener buttonListener) {
		this.m = m;
		this.v = v;
		this.buttonListener = buttonListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String guiType = e.getActionCommand();
		v.setGUI(guiType);
		v.getGUI().addActionListener(buttonListener);
	}

}
