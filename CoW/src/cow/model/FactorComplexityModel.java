package cow.model;

import java.util.ArrayList;

import cow.data.Result;
import cow.data.requesthandler.FactorComplexityRequestHandler;
import cow.data.requesthandler.OpenFileRequestHandler;
import cow.data.requesthandler.RequestHandler;
import cow.data.requesthandler.SaveRequestHandler;

public class FactorComplexityModel implements Model {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();
	private String result = "";

	public void factorComplexityRequest(String text) {
		resultsList.clear();
		args = new String[1];
		args[0] = text;
		handler = new FactorComplexityRequestHandler();
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

	public boolean isValidText(String text) {
		if (text.length() == 0) {
			return false;
		}
		return true;
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
}
