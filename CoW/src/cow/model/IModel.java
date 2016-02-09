package cow.model;

import java.util.ArrayList;

public interface IModel {

	void unorderedPatternRequest(String pattern, String text);

	void factorComplexityRequest(String text);

	void morphismRequest(String text, String[] morphismData, int iteration);

	void exportRequest(String filepath);

	ArrayList<Result> getResultsList();

	String trimText(String text);

	boolean isValid(String pattern, String text);

	void appendResultLine(String text);

	void clearResult();

}
