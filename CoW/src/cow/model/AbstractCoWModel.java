package cow.model;

import java.util.ArrayList;

import cow.data.Result;
import cow.data.requesthandler.RequestHandler;
import cow.data.requesthandler.SaveRequestHandler;

public abstract class AbstractCoWModel extends AbstractModel {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();
	private String result = "";

	public void updateResults(ArrayList<Result> resultsList) {
		this.resultsList = resultsList;
	}

	public void saveRequest(String filepath) {
		resultsList.clear();
		args = new String[2];
		args[0] = filepath;
		args[1] = result;
		handler = new SaveRequestHandler();
		resultsList = handler.handle(args);
	}

	public void clearResult() {
		result = "";
	}

	public void appendResultLine(String text) {
		result = result + text;
	}

	public ArrayList<Result> getResultsList() {
		return resultsList;
	}
}
