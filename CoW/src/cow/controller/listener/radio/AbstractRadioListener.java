package cow.controller.listener.radio;

import java.awt.event.ActionListener;

import cow.view.View;

public abstract class AbstractRadioListener implements ActionListener {

	private View v;

	public AbstractRadioListener(View v) {
		this.v = v;
	}
}
