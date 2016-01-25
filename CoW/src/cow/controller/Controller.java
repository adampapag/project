package cow.controller;

import java.awt.event.ActionListener;

import cow.model.IModel;
import cow.model.Model;
import cow.view.IView;
import cow.view.View;

public class Controller implements IController {

	private IModel m;
	private IView v;

	public Controller() {
		createModel();
		createView();
		addActionListener();
	}

	@Override
	public void addActionListener() {
		ActionListener menuListener = new MenuListener(m, v,
				new ButtonListener(m, v));
		v.getGUI().addActionListener(menuListener);
	}

	@Override
	public void createModel() {
		m = new Model();

	}

	@Override
	public void createView() {
		v = new View();

	}

}
