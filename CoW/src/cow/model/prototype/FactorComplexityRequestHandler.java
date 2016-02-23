package cow.model.prototype;

import java.util.ArrayList;

import cow.model.Result;

public class FactorComplexityRequestHandler {

	public FactorComplexityRequestHandler() {

	}

	public ArrayList<Result> handle(String text) {
		ArrayList<Result> resultsList = new ArrayList<Result>();
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
						resultsList.add(new Result(candidate, text
								.substring(candidate.length())));
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
