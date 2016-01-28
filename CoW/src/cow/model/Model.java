package cow.model;

import java.util.ArrayList;

public class Model implements IModel {

	private UnorderedPatternRequestHandler handler = new UnorderedPatternRequestHandler();

	@Override
	public ArrayList<String> unorderedPatternRequest(String pattern, String text) {
		// handler.countOccurrences(pattern, text);
		// return handler.pattern(pattern, text);
		return handler.unaryPatternMatch(pattern, text);
	}

}
