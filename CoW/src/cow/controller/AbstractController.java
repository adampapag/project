package cow.controller;

import java.awt.event.ActionListener;

import cow.model.Model;
import cow.view.View;

public abstract class AbstractController implements Controller {

	private Model m;
	private View v;

	public AbstractController(Model m, View v) {
		this.m =  m;
		this.v = v;
	}

	public void show() {
		createView();
		createViewListener();
	}

	private void createView() {
		v.initializeGUI();
	}

	protected abstract void createViewListener();

	protected void attachListener(ActionListener listener) {
		v.addActionListener(listener);
	}

}
