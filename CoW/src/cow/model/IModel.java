package cow.model;

import java.util.ArrayList;

public interface IModel {

	void patternRequest(String pattern, String text, boolean ordered);

	void orderedPatternRequest(String pattern, String text);

	void unorderedPatternRequest(String pattern, String text);

	void factorComplexityRequest(String text);

	void morphismRequest(String text, String[] morphismData, int iteration);

	void crucialityRequest(ArrayList<String> patternList, String text,
			boolean ordered, ArrayList<String> alphabet);

	void exportRequest(String filepath);

	ArrayList<Result> getResultsList();

	String trimText(String text);

	boolean isValidPattern(String pattern);

	boolean isValidText(String text);

	boolean isValid(String[] morphismData);

	void appendResultLine(String text);

	void clearResult();

	ArrayList<String> deduceAlphabet(String text);

	void saveRequest(String filepath);

}
