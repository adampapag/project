package cow.model.prototype;

import java.util.ArrayList;
import java.util.Iterator;

import cow.model.Result;
import cow.model.UnorderedPatternRequestHandler;
import cow.model.OrderedPatternRequestHandler;

public class CrucialityRequestHandler {

	public ArrayList<Result> handle(String[] args) {
		ArrayList<String> alphabet = new ArrayList<String>();
		ArrayList<String> patternList = new ArrayList<String>();
		boolean ordered = true;
		int index = 1;
		String text = args[0];

		String letter = args[index];
		while (!(letter == "true" || letter == "false")) {
			alphabet.add(letter);
			index++;
			letter = args[index];
		}

		if (letter.equals("false")) {
			ordered = false;
		}

		for (int i = index; i < args.length; i++) {
			patternList.add(args[i]);
		}

		ArrayList<Result> resultList = new ArrayList<Result>();
		ArrayList<Result> containsList = new ArrayList<Result>();
		String[] params = new String[2];

		// String pattern = args[0];
		// String text = args[1];
		// boolean ordered = true;
		//
		// params[0] = pattern;
		// params[1] = text;
		//
		// if (args[2].equals("false")) {
		// ordered = false;
		// }
		//
		// for (int i = 3; i < args.length; i++) {
		// alphabet.add(args[i]);
		// }

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
					// containsList.add(r);
					resultList.add(r);
				}

				deletedText = deletedText + textCopy.substring(0, 1);
				textCopy = textCopy.substring(1);
				params[1] = textCopy;
			}
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

		// System.out.println("word contains: ");
		// for (int i = 0; i < containsList.size(); i++) {
		// System.out.println(containsList.get(i).getPrefix() + "["
		// + containsList.get(i).getString() + "]"
		// + containsList.get(i).getRemainingString());
		// }
		// boolean rightExtension = false;
		// boolean leftExtension = false;
		// for (String letter : alphabet) {
		// params[1] = text + letter;
		// for (Result r : cruciality(params, containsList, ordered, "suffix"))
		// {
		// rightExtension = true;
		// resultList.add(r);
		// }
		// params[1] = letter + text;
		// for (Result r : (cruciality(params, containsList, ordered,
		// "prefix"))) {
		// leftExtension = true;
		// resultList.add(r);
		// }
		// }
		// if (rightExtension && leftExtension) {
		// resultList.add(new Result(text + " is bi-crucial", ""));
		// } else if (rightExtension || leftExtension) {
		// resultList.add(new Result(text + " is crucial", ""));
		// }
		return resultList;
	}

	private String cruciality(String text, ArrayList<String> alphabet,
			ArrayList<String> patternList, boolean ordered) {
		ArrayList<Result> resultList = new ArrayList<Result>();
		ArrayList<String> rExtendList = new ArrayList<String>();
		String[] params = new String[2];

		for (String letter : alphabet) {
			rExtendList.add(text + letter);
		}
		for (String extendedWord : rExtendList) {
			params[1] = extendedWord;
			for (String pattern : patternList) {
				params[0] = pattern;
				if (ordered) {
					resultList.addAll(new OrderedPatternRequestHandler()
							.handle(params));
				} else {
					assert !ordered;
					resultList.addAll(new UnorderedPatternRequestHandler()
							.handle(params));
				}
			}
			if (resultList.isEmpty()) {
				return "not crucial";
			}
		}
		return "crucial";
	}

	private String bicruciality(String text, ArrayList<String> alphabet,
			ArrayList<String> patternList, boolean ordered) {
		ArrayList<Result> resultList = new ArrayList<Result>();
		ArrayList<String> lExtendList = new ArrayList<String>();
		String[] params = new String[2];

		for (String letter : alphabet) {
			lExtendList.add(letter + text);
		}
		for (String extendedWord : lExtendList) {
			params[1] = extendedWord;
			for (String pattern : patternList) {
				params[0] = pattern;
				if (ordered) {
					resultList.addAll(new OrderedPatternRequestHandler()
							.handle(params));
				} else {
					assert !ordered;
					resultList.addAll(new UnorderedPatternRequestHandler()
							.handle(params));
				}
			}
			if (resultList.isEmpty()) {
				return "not bi-crucial";
			}
		}
		return "bi-crucial";
	}

	private ArrayList<Result> cruciality(String[] params,
			ArrayList<Result> containsList, boolean ordered,
			String extensionSide) {
		ArrayList<Result> resultList = new ArrayList<Result>();

		String text = params[1];
		String deletedText = "";

		while (text.length() > 1) {
			for (Result r : cruciality2(params, containsList, ordered,
					extensionSide)) {
				r.addPrefix(deletedText + r.getPrefix());
				resultList.add(r);
			}
			deletedText = deletedText + text.substring(0, 1);
			text = text.substring(1);
			params[1] = text;
		}
		Iterator<Result> iterator = resultList.iterator();
		Result r = null;
		while (iterator.hasNext()) {
			r = iterator.next();
			for (Result c : containsList) {
				if (extensionSide.equals("prefix")) {
					if (r.getString().equals(c.getString())
							&& r.getRemainingString().equals(
									c.getRemainingString())) {
						iterator.remove();
					}
				} else {
					assert extensionSide.equals("suffix");
					if (r.getPrefix().equals(c.getPrefix())
							&& r.getString().equals(c.getString())) {
						iterator.remove();
					}
				}
			}
		}
		return resultList;
	}

	private ArrayList<Result> cruciality2(String[] params,
			ArrayList<Result> containsList, boolean ordered,
			String extensionSide) {
		ArrayList<Result> resultList;

		if (ordered) {
			resultList = new OrderedPatternRequestHandler().handle(params);
		} else {
			assert !ordered;
			resultList = new UnorderedPatternRequestHandler().handle(params);
		}

		return resultList;
	}
}
