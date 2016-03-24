package cow.controller.listener.radio;

import java.awt.event.ActionEvent;

import cow.view.PatternView;
import cow.view.View;

public class PatternRadioListener extends AbstractRadioListener {

	private PatternView v;

	public PatternRadioListener(View v) {
		super(v);
		this.v = (PatternView) v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		switch (button) {
		case "ordered":
			v.setOrdered();
			break;
		case "unordered":
			v.setUnordered();
			break;
		case "avoidance":
			v.setAvoidance();
			break;
		case "distribution":
			v.setDistribution();
			break;
		case "number words":
			v.setNumberWords();
			break;
		case "print words":
			v.setPrintWords();
		case "on words":
			v.setOnWords();
			break;
		case "text":
			v.setText();
			break;
		}
	}

}
