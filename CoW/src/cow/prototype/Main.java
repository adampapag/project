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
				String text = "12341234";
				String text2 = text;
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
				System.out.print("(");
				for (int i = 0; i < text2.length(); i++) {
					for (String s : resultsList) {
						if (s.length() == i)
							System.out.print(s + ", ");
					}
				}
				System.out.print("- )\n\n");
			}
		});
	}
}
