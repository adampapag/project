package cow.controller.listener.radio;

import java.awt.event.ActionEvent;

import cow.view.CrucialityView;
import cow.view.View;

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