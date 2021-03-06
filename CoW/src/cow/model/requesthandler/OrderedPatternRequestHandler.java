package cow.model.requesthandler;

import java.util.ArrayList;

import cow.data.Result;
import cow.data.SymbolMapping;

public class OrderedPatternRequestHandler extends PatternRequestHandler {

	public ArrayList<Result> handle(String[] args) {
		String pattern = args[0];
		String text = args[1];
		ArrayList<String> highLowList = orderedPatternSplit(pattern);
		ArrayList<Result> resultsList = new ArrayList<Result>();

		resultsList = orderedPatternMatch(highLowList, text, pattern);

		resultsList = removeSymbolicContradictions(resultsList);

		return resultsList;
	}

	private ArrayList<Result> orderedPatternMatch(
			ArrayList<String> highLowList, String text, String pattern) {
		ArrayList<Result> resultsList = new ArrayList<Result>();
		ArrayList<SymbolMapping> symbolMap = new ArrayList<SymbolMapping>();
		String result = "";
		String currentIncrement = "";
		String currentChar = "";
		String nextChar = "";
		boolean okay = true;
		if (text.length() > highLowList.size()) {
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
					// pattern broken
					return resultsList;
				}

				if (i == 0) {
					result = currentChar + nextChar;
				} else {
					result = result + nextChar;
				}

				symbolMap.add(new SymbolMapping(pattern.substring(0, 1),
						currentChar));

				pattern = pattern.substring(1);

			}
			symbolMap.add(new SymbolMapping(
					pattern.substring(pattern.length() - 1), nextChar));

			Result r = new Result(result, text.substring((result.length())));

			for (int i = symbolMap.size() - 1; i >= 0; i--) {
				r.addSymbolMapping(symbolMap.get(i));
			}

			resultsList.add(r);
		}

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
