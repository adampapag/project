package cow.model;

import java.util.ArrayList;

public class Model implements IModel {

	private RequestHandler handler;
	String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();
	private String result;

	public void orderedPatternRequest(String pattern, String text) {
		resultsList.clear();
		args = new String[2];
		args[0] = pattern;
		args[1] = text;
		handler = new OrderedPatternRequestHandler();
		resultsList = handler.handle(args);
	}

	public void unorderedPatternRequest(String pattern, String text) {
		resultsList.clear();
		args = new String[2];
		args[0] = pattern;
		args[1] = text;
		handler = new UnorderedPatternRequestHandler();
		resultsList = handler.handle(args);
	}

	public void patternRequest(String pattern, String text, boolean ordered) {
		resultsList.clear();
		args = new String[2];
		args[0] = pattern;
		args[1] = text;
		if (ordered) {
			handler = new OrderedPatternRequestHandler();
		} else {
			assert !ordered;
			handler = new UnorderedPatternRequestHandler();
		}
		resultsList = handler.handle(args);
	}

	public void factorComplexityRequest(String text) {
		resultsList.clear();
		args = new String[1];
		args[0] = text;
		handler = new FactorComplexityRequestHandler();
		resultsList = handler.handle(args);
	}

	public void morphismRequest(String text, String[] morphismData,
			int iteration) {
		resultsList.clear();
		args = new String[morphismData.length + 2];
		for (int i = 0; i < morphismData.length; i++) {
			args[i] = morphismData[i];
		}
		args[args.length - 2] = text;
		args[args.length - 1] = String.valueOf(iteration);
		handler = new MorphismRequestHandler();
		resultsList = handler.handle(args);
	}

	public void crucialityRequest(String pattern, String text, boolean ordered,
			ArrayList<String> alphabet) {
		resultsList.clear();
		args = new String[alphabet.size() + 3];
		args[0] = pattern;
		args[1] = text;
		args[2] = String.valueOf(ordered);
		for (int i = 3; i < args.length; i++) {
			args[i] = alphabet.get(i - 3);
		}
		handler = new CrucialityRequestHandler();
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i] + ", ");
		}
		resultsList = handler.handle(args);
	}

	public void saveRequest(String filepath) {
		resultsList.clear();
		args = new String[2];
		args[0] = filepath;
		args[1] = result;
		handler = new ExportRequestHandler();
		resultsList = handler.handle(args);
	}

	public void exportRequest(String filepath) {
		// TODO Auto-generated method stub

	}

	public String trimText(String text) {
		return text.substring(1);
	}

	public ArrayList<Result> getResultsList() {
		return resultsList;
	}

	public void appendResultLine(String text) {
		result = result + text;
	}

	public void clearResult() {
		result = "";
	}

	public boolean isValid(String pattern, String text) {
		if (text.length() == 0) {
			return false;
		} else if (pattern.length() < 2) {
			return false;
		}
		return true;
	}

	public boolean isValid(String[] morphismData) {
		ArrayList<String> letters = new ArrayList<String>();
		ArrayList<String> morphisms = new ArrayList<String>();

		for (int i = 0; i < morphismData.length; i++) {
			if ((i % 2) == 0) {
				letters.add(morphismData[i]);
			} else {
				morphisms.add(morphismData[i]);
			}
		}

		int occurrences = 0;
		for (String l : letters) {
			if (l.equals("")) {
				System.out.println("Letter cannot be space.");
				return false;
			} else if (l.length() > 1) {
				System.out.println("Letter must be one character.");
				return false;
			}
			occurrences = 0;
			for (String l2 : letters) {
				if (l2.equals(l)) {
					occurrences++;
				}
				if (occurrences > 1) {
					System.out.println("Letter appears more than once.");
					return false;
				}
			}
		}

		String symbol = "";
		for (String m : morphisms) {
			for (char c : m.toCharArray()) {
				symbol = String.valueOf(c);
				if (!(letters.contains(symbol))) {
					System.out.println("Symbol '" + c + "' in morphism '" + m
							+ "' has no corresponding morphism.");
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public ArrayList<String> deduceAlphabet(String text) {
		char[] textArray = text.toCharArray();
		ArrayList<String> alphabet = new ArrayList<String>();
		for (char c : textArray) {
			String letter = String.valueOf(c);
			if (!alphabet.contains(letter)) {
				alphabet.add(letter);
			}
		}
		return alphabet;
	}

}
