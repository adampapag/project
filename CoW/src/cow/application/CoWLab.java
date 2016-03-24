package cow.application;

import javax.swing.SwingUtilities;

import cow.controller.CoWLabController;
import cow.controller.Controller;
import cow.model.CoWLabModel;
import cow.model.Model;
import cow.view.CoWLabView;
import cow.view.View;

public class CoWLab implements Application {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				CoWLab app = new CoWLab();
				app.open();
			}
		});
	}

	private Model m;
	private View v;
	private Controller c;

	public void open() {
		m = new CoWLabModel();
		v = new CoWLabView();
		c = new CoWLabController(m, v);
		c.show();
	}
}