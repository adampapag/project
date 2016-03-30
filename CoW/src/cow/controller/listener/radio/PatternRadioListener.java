package cow.controller.listener.radio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.view.PatternView;
import cow.view.View;

/**
 * Concrete implementation of the AbstractRadioListener. Provides responses for
 * radio button clicks in the View.
 * 
 * @author Adam Papageorgiou
 *
 * @see AbstractRadioListener
 * @see ActionListener
 * @see PatternView
 */
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
