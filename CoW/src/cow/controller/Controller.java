package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.view.IView;
import cow.view.View;

public class Controller implements IController, ActionListener {

	IView v;

	// IModel m;

	public Controller() {

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

	public void addActionListener() {
		v.getGUI().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		v.setGUI(e.getActionCommand()); // i.e. "Morphisms"
		// String command = e.getActionCommand();
		// if (command.equals("Morphisms")) {
		// System.out.println("Morphisms button pressed.");
		// v.setGUI(e.getActionCommand()); // i.e. "Morphisms"
		// } else if (command.equals("Patterns")) {
		// System.out.println("Patterns button pressed.");
		// } else if (command.equals("Cruciality")) {
		// System.out.println("Cruciality button pressed.");
		// }

	}
}
