package cow.prototype;

import java.util.ArrayList;

public class FactorComplexityRequestHandler {

	public FactorComplexityRequestHandler() {

	}

	public ArrayList<String> handle(String text) {
		ArrayList<String> resultsList = new ArrayList<String>();
		String candidate;
		for (int symbolLength = 1; symbolLength <= text.length(); symbolLength++) {
			System.out.println("length: " + symbolLength);
			for (int i = 0; i < text.length(); i++) {
				try {
					candidate = text.substring((i * symbolLength),
							((i * symbolLength) + symbolLength));
					System.out.println("candidate: " + candidate);
					if (!(resultsList.contains(candidate))) {
						System.out.println("added: " + candidate);
						resultsList.add(candidate);
					} else {
						System.out.println("not added; already contains: "
								+ candidate);
					}
				} catch (StringIndexOutOfBoundsException oobe) {
					System.out.println("not enough characters in string: "
							+ "text = " + text + " , symbol length = "
							+ symbolLength + ", start index = " + i
							* symbolLength);
					break;
				}
			}
		}
		return resultsList;
	}
}
