package cow.prototype;

import javax.swing.SwingUtilities;

public class Main {
	static String pattern = "xx";
	static String text = "34334646453ss ddd ff";

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new PatternIdentifier(pattern, text);
			}
		});
	}
}
