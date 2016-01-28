package cow.model;

import java.util.ArrayList;

public interface IModel {

	void unorderedPatternRequest(String pattern, String text);

	ArrayList<String> getResultsList();

	String trimText(String text);

	boolean isValid(String pattern, String text);

}
