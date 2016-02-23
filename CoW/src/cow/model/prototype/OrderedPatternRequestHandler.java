package cow.model.prototype;

import java.util.ArrayList;

import cow.model.Result;

public class OrderedPatternRequestHandler {

	public OrderedPatternRequestHandler() {

	}

	public ArrayList<Result> handle(String[] args) {
		String pattern = args[0];
		String text = args[1];
		ArrayList<String> highLowList = orderedPatternSplit(pattern);
		ArrayList<Result> resultsList = new ArrayList<Result>();

		resultsList = orderedPatternMatch(highLowList, text);

		return resultsList;
	}

	private ArrayList<Result> orderedPatternMatch(
			ArrayList<String> highLowList, String text) {
		ArrayList<Result> resultsList = new ArrayList<Result>();
		String result = "";
		String currentIncrement = "";
		String currentChar = "";
		String nextChar = "";
		boolean okay = true;
		for (int i = 0; i < highLowList.size(); i++) {
			currentIncrement = highLowList.get(i);
			currentChar = text.substring(i, i + 1);
			nextChar = text.substring(i + 1, i + 2);

			if (currentIncrement.equals("-")) {
				if (!(currentChar.equals(nextChar))) {
					okay = false;
				}

			} else if (currentIncrement.equals("up")) {
				if (!(Integer.parseInt(currentChar) < Integer
						.parseInt(nextChar))) {
					okay = false;
				}

			} else if (currentIncrement.equals("down")) {
				if (!(Integer.parseInt(currentChar) > Integer
						.parseInt(nextChar))) {
					okay = false;
				}

			}

			if (!okay) {
				System.out.println("pattern broken");
				return resultsList;
			}

			result = result + currentChar + nextChar;
			System.out.println("result = " + result);

		}

		resultsList.add(new Result(result, text.substring((result.length()))));

		return resultsList;
	}

	public ArrayList<String> orderedPatternSplit(String pattern) {
		ArrayList<String> patternList = new ArrayList<String>();

		for (int i = 0; i < (pattern.length() - 1); i++) {

			String currentChar = pattern.substring(i, i + 1);
			String nextChar = pattern.substring(i + 1, i + 2);

			if (currentChar.equals(nextChar)) {
				patternList.add("-");
			} else if (Integer.parseInt(currentChar) > Integer
					.parseInt(nextChar)) {
				patternList.add("down");
			} else if (Integer.parseInt(currentChar) < Integer
					.parseInt(nextChar)) {
				patternList.add("up");
			}
		}

		return patternList;
	}
}
