package cow.prototype;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import cow.model.Result;

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
				ArrayList<String> seenList = new ArrayList<String>();
				ArrayList<Result> resultsList = new ArrayList<Result>();
				while (text.length() > 0) {
					System.out.println("\n text: " + text);
					ArrayList<Result> results = handler.handle(text);
					for (Result r : results) {
						String result = r.getString();
						if (!(seenList.contains(result))) {
							seenList.add(result);
							resultsList.add(r);
						}
						// for (Result seen : resultsList) {
						// System.out.println("seen: " + seen.getString());
						// if (!(result.equals(seen.getString()))) {
						// resultsList.add(r);
						// }
						// }
					}
					text = text.substring(1);
				}
				System.out.println("\n results: ");
				System.out.print("(");
				for (int i = 0; i < text2.length(); i++) {
					for (Result r : resultsList) {
						if (r.getString().length() == i)
							System.out.print(r.getString() + ", ");
					}
				}
				System.out.print("- )\n\n");
			}
		});
	}
}
