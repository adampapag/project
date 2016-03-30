package cow.controller.listener.radio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cow.view.CrucialityView;
import cow.view.View;

/**
 * Concrete implementation of the AbstractRadioListener. Provides responses for
 * radio button clicks in the View.
 * 
 * @author Adam Papageorgiou
 *
 * @see AbstractRadioListener
 * @see ActionListener
 * @see CrucialityView
 */
public class CrucialityRadioListener extends AbstractRadioListener {

	private CrucialityView v;

	public CrucialityRadioListener(View v) {
		super(v);
		this.v = (CrucialityView) v;
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
		case "on words":
			v.setOnWords();
			break;
		case "text":
			v.setText();
			break;
		case "print words":
			v.setPrintWords();
			break;
		case "number words":
			v.setNumberWords();
			break;
		}

	}
}