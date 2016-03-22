package cow.pattern;

import java.util.ArrayList;

import cow.data.Result;
import cow.interfaces.Model;
import cow.interfaces.RequestHandler;
import cow.io.OpenFileRequestHandler;
import cow.io.SaveRequestHandler;

public class PatternModel implements Model {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();
	private String result = "";

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

	public void openFileRequest(String filepath) {
		resultsList.clear();
		args = new String[1];
		args[0] = filepath;
		handler = new OpenFileRequestHandler();
		resultsList = handler.handle(args);
	}

	public void saveRequest(String filepath) {
		resultsList.clear();
		args = new String[2];
		args[0] = filepath;
		args[1] = result;
		handler = new SaveRequestHandler();
		resultsList = handler.handle(args);
	}

	public boolean isValidPattern(String pattern) {
		if (pattern.length() < 2) {
			return false;
		}
		return true;
	}

	public boolean isValidText(String text) {
		if (text.length() == 0) {
			return false;
		}
		return true;
	}

	public void appendResultLine(String text) {
		result = result + text;
	}

	public ArrayList<Result> getResultsList() {
		return resultsList;
	}

	public String trimText(String text) {
		return text.substring(1);
	}

	public void clearResult() {
		result = "";
	}
}
