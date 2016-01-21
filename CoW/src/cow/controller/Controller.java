package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.view.IView;
import cow.view.View;

public class Controller implements IController, ActionListener {

	private IView v;
	private ActionListener buttonListener;

	// IModel m;

	public Controller() {
		buttonListener = new ButtonListener();
	}

	@Override
	public void createModel() {
		// TODO
		// m = new Model();

	}

	@Override
	public void createView() {
		v = new View();
		addActionListener();
	}

	// listener for CoWGUI (main menu) buttons
	public void addActionListener() {
		v.getGUI().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		v.setGUI(e.getActionCommand());
		v.getGUI().addActionListener(buttonListener);
		// v.getGUI().addActionListener(buttonListener);
		// String GUIType = v.getGUI();
		// if (GUIType.equals("Pattern") {}
	}
}
