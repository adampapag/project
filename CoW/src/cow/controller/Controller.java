package cow.controller;

import java.awt.event.ActionListener;

import cow.view.IView;
import cow.view.View;

public class Controller implements IController {

	private IView v;
	private ActionListener buttonListener;
	private ActionListener menuListener;

	// IModel m;

	public Controller() {
		createModel();
		createView();
		addActionListener();
	}

	// listener for buttons in GUI (e.g. Pattern)
	public void createButtonListener() {
		buttonListener = new ButtonListener(v);
	}
	
	// listener for CoWGUI (main menu) buttons
	public void createMenuListener() {
		menuListener = new MenuListener(v, buttonListener);
	}

	@Override
	public void createModel() {
		// TODO
		// m = new Model();

	}

	@Override
	public void createView() {
		v = new View();

	}
	
	@Override
	public void addActionListener() {
		createButtonListener();
		createMenuListener();
		v.getGUI().addActionListener(menuListener);
	}

}
