package cow.model;

import java.util.ArrayList;

public class Model implements IModel {

	private UnorderedPatternRequestHandler handler = new UnorderedPatternRequestHandler();
	private ArrayList<Result> resultsList = new ArrayList<Result>();

	// @Override
	// public ArrayList<String> unorderedPatternRequest(String pattern, String
	// text) {
	// // handler.countOccurrences(pattern, text);
	// // return handler.pattern(pattern, text);
	// return handler.unaryPatternMatch(pattern, text);
	// }

	public void unorderedPatternRequest(String pattern, String text) {
		resultsList.clear();
		// resultsList = handler.unaryPatternMatch(pattern, text);
		handler.binaryPatternMatch(handler.unaryPatternSplit(pattern), text);
	}

	public String trimText(String text) {
		return text.substring(1);
	}

	public ArrayList<Result> getResultsList() {
		return resultsList;
	}

	public boolean isValid(String pattern, String text) {
		if (text.length() == 0) {
			return false;
		} else if (pattern.length() < 2) {
			return false;
		}
		return true;
	}

}
