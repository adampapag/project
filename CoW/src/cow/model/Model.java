package cow.model;

import java.util.ArrayList;

public class Model implements IModel {

	private RequestHandler handler;
	String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();

	public void unorderedPatternRequest(String pattern, String text) {
		resultsList.clear();
		args = new String[2];
		args[0] = pattern;
		args[1] = text;
		handler = new UnorderedPatternRequestHandler();
		resultsList = handler.handle(args);
	}

	public void factorComplexityRequest(String text) {
		resultsList.clear();
		args = new String[1];
		args[0] = text;
		handler = new FactorComplexityRequestHandler();
		resultsList = handler.handle(args);
	}
	
	public void morphismRequest(String text) {
		resultsList.clear();
//		args = new String[];
//		args[args.length-1] = text;
//		handler = new MorphismRequestHander();
//		resultsList = handler.handle(args);
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
