package cow.model;

import java.util.ArrayList;

public interface IModel {

	void patternRequest(String pattern, String text, boolean ordered);

	void orderedPatternRequest(String pattern, String text);

	void unorderedPatternRequest(String pattern, String text);

	void factorComplexityRequest(String text);

	void morphismRequest(String text, String[] morphismData, int iteration);

	void crucialityRequest(String pattern, String text, boolean ordered,
			String[] alphabet);

	void exportRequest(String filepath);

	ArrayList<Result> getResultsList();

	String trimText(String text);

	boolean isValid(String pattern, String text);

	boolean isValid(String[] morphismData);

	void appendResultLine(String text);

	void clearResult();

}
