package cow.model.requesthandler;

import java.util.ArrayList;

import cow.data.Result;

public class FactorComplexityRequestHandler implements RequestHandler {

	public ArrayList<Result> handle(String[] args) {
		String text = args[0];
		ArrayList<Result> resultsList = new ArrayList<Result>();
		String candidate;

		resultsList.add(new Result(text, ""));
		for (int symbolLength = 1; symbolLength <= text.length(); symbolLength++) {
			for (int i = 0; i < text.length(); i++) {
				try {
					candidate = text.substring((i * symbolLength),
							((i * symbolLength) + symbolLength));
					if (!(resultsList.contains(candidate))) {
						resultsList.add(new Result(candidate, text
								.substring(candidate.length())));
					}
					// else not added as substring accounted for
				} catch (StringIndexOutOfBoundsException oobe) {
					// not enough characters in string to create a substring of
					// length symbolLength
					break;
				}
			}
		}
		return resultsList;
	}
}
