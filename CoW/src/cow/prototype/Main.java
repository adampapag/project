package cow.prototype;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class Main {
	static String pattern = "xx";
	static String text = "34334646453ss ddd ff";

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// new PatternIdentifier(pattern, text);
				FactorComplexityRequestHandler handler = new FactorComplexityRequestHandler();
				String text = "1234";
				ArrayList<String> resultsList = new ArrayList<String>();
				while (text.length() > 0) {
					System.out.println("\n text: " + text);
					ArrayList<String> results = handler.handle(text);
					for (String s : results) {
						if (!(resultsList.contains(s))) {
							resultsList.add(s);
						}
					}
					text = text.substring(1);
				}
				System.out.println("\n results: ");
				for (String s : resultsList) {
					System.out.println(s + ", ");
				}
			}
		});
	}
}
