package cow.controller.prototype;

import javax.swing.SwingUtilities;

public class Controller {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			}
		});
	}
}