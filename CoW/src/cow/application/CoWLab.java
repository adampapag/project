package cow.application;

import javax.swing.SwingUtilities;

import cow.controller.CoWLabController;
import cow.model.CoWLabModel;
import cow.model.Model;
import cow.view.CoWLabView;
import cow.view.View;

public class CoWLab {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Model m = new CoWLabModel();
				View v = new CoWLabView();
				new CoWLabController(m, v);
			}
		});
	}
}