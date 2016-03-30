package cow.application;

import javax.swing.SwingUtilities;

import cow.controller.CoWLabController;
import cow.controller.Controller;
import cow.model.CoWLabModel;
import cow.model.Model;
import cow.view.CoWLabView;
import cow.view.View;

/**
 * Concrete implementation of the Application interface. The CoWLab creates an
 * M-V-C structure.
 * 
 * @author Adam Papageorgiou
 * 
 * @version 1.0
 *
 * @see Application
 */
public class CoWLab implements Application {

	/**
	 * Main method that instantiates the CoWLab Application.
	 * 
	 * @param args
	 */
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