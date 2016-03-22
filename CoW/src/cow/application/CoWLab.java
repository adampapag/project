package cow.application;

import javax.swing.SwingUtilities;

import cow.controller.Controller;
import cow.cowlab.CoWLabController;
import cow.cowlab.CoWLabModel;
import cow.cowlab.CoWLabView;
import cow.interfaces.Model;
import cow.interfaces.View;

public class CoWLab {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO
				// new Controller();
				Model m = new CoWLabModel();
				View v = new CoWLabView();
				new CoWLabController(m, v);
			}
		});
	}
}