package cow.application;

import javax.swing.SwingUtilities;

import cow.controller.Controller;

public class CoWLabApp {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO
				new Controller();
			}
		});
	}
}