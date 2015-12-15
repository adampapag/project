package cow.application;

import javax.swing.SwingUtilities;

import cow.controller.Controller;
import cow.controller.IController;

public class CoWLabApp {
	
	private static IController c;
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//TODO
				c = new Controller();
				c.createModel();
				c.createView();
			}
		});
	}
}