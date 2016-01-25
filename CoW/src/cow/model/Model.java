package cow.model;

public class Model implements IModel {

	@Override
	public void unorderedPatternRequest(String pattern, String text) {
		new UnorderedPatternRequestHandler().countOccurrences(pattern, text);
	}

}
