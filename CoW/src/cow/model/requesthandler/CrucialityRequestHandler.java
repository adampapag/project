package cow.model.requesthandler;

import java.util.ArrayList;

import cow.data.Result;

public class CrucialityRequestHandler implements RequestHandler {

	public ArrayList<Result> handle(String[] args) {
		ArrayList<Result> resultList = new ArrayList<Result>();
		String text = args[0];
		ArrayList<String> alphabet = parseAlphabet(args);
		boolean ordered = parseOrdered(args);
		ArrayList<String> patternList = parsePatterns(args);
		
		String[] params = new String[2];

		params[1] = text;

		for (String pattern : patternList) {
			params[0] = pattern;
			String textCopy = text;
			String deletedText = "";
			ArrayList<Result> resultCopy;
			while (textCopy.length() > 0) {
				if (ordered) {
					resultCopy = new OrderedPatternRequestHandler()
							.handle(params);
				} else {
					assert !ordered;
					resultCopy = new UnorderedPatternRequestHandler()
							.handle(params);
				}

				for (Result r : resultCopy) {
					if (r.getPrefix().equals("")) {
						r.addPrefix(deletedText);
					}
					r.addPrefix("word already contains pattern " + pattern
							+ " : " + r.getPrefix());
					resultList.add(r);
				}

				deletedText = deletedText + textCopy.substring(0, 1);
				textCopy = textCopy.substring(1);
				params[1] = textCopy;
			}
			params[1] = text;
		}

		if (!resultList.isEmpty()) {
			return resultList;
		}

		String cruciality = cruciality(text, alphabet, patternList, ordered);

		if (!cruciality.equals("not crucial")) {
			Result r = new Result(text, "");
			r.addPrefix("crucial word: ");
			String bicruciality = bicruciality(text, alphabet, patternList,
					ordered);
			if (bicruciality.equals("bi-crucial")) {
				r.addPrefix("bi-crucial word: ");
			}
			resultList.add(r);
		}

		return resultList;
	}

	private String cruciality(String text, ArrayList<String> alphabet,
			ArrayList<String> patternList, boolean ordered) {
		ArrayList<String> rExtendList = new ArrayList<String>();

		for (String letter : alphabet) {
			rExtendList.add(text + letter);
		}

		if (isCrucial(rExtendList, patternList, ordered)) {
			return "crucial";
		}

		return "not crucial";

	}

	private String bicruciality(String text, ArrayList<String> alphabet,
			ArrayList<String> patternList, boolean ordered) {
		ArrayList<String> lExtendList = new ArrayList<String>();

		for (String letter : alphabet) {
			lExtendList.add(letter + text);
		}

		if (isCrucial(lExtendList, patternList, ordered)) {
			return "bi-crucial";
		}

		return "not bi-crucial";

	}

	private boolean isCrucial(ArrayList<String> extendList,
			ArrayList<String> patternList, boolean ordered) {
		ArrayList<Result> resultList = new ArrayList<Result>();
		String[] params = new String[2];
		String word;

		for (String extendedWord : extendList) {
			for (String pattern : patternList) {
				params[0] = pattern;
				word = extendedWord;
				while (word.length() > 0) {
					params[1] = word;
					if (ordered) {
						resultList.addAll(new OrderedPatternRequestHandler()
								.handle(params));
					} else {
						assert !ordered;
						resultList.addAll(new UnorderedPatternRequestHandler()
								.handle(params));
					}
					word = word.substring(1);
				}
			}
			if (resultList.isEmpty()) {
				return false;
			}
			resultList.clear();
		}
		return true;
	}

	private ArrayList<String> parseAlphabet(String[] args) {
		ArrayList<String> alphabet = new ArrayList<String>();
		int index = 1;
		String letter = args[index];
		while (!(letter == "true" || letter == "false")) {
			alphabet.add(letter);
			index++;
			letter = args[index];
		}
		return alphabet;
	}

	private boolean parseOrdered(String[] args) {
		int index = 1;
		String letter = args[index];
		while (!(letter == "true" || letter == "false")) {
			index++;
			letter = args[index];
		}
		if (letter.equals("true")) {
			return true;
		}
		assert letter.equals("false");
		return false;
	}

	private ArrayList<String> parsePatterns(String[] args) {
		ArrayList<String> patternList = new ArrayList<String>();
		int index = 1;
		String letter = args[index];
		while (!(letter == "true" || letter == "false")) {
			index++;
			letter = args[index];
		}
		for (int i = index + 1; i < args.length; i++) {
			patternList.add(args[i]);
		}
		return patternList;
	}
}
