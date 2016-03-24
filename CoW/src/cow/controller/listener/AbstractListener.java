package cow.controller.listener;

import java.awt.event.ActionListener;

import cow.controller.Controller;

public abstract class AbstractListener implements ActionListener {

	private Controller c;

	public AbstractListener(Controller c) {
		this.c = c;
	}
}
