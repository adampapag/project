package cow.model;

import java.util.ArrayList;

import cow.data.Result;
import cow.data.requesthandler.OpenFileRequestHandler;
import cow.data.requesthandler.RequestHandler;

public abstract class AbstractCoWModelWithImport extends AbstractCoWModel {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();

	public void openFileRequest(String filepath) {
		resultsList.clear();
		args = new String[1];
		args[0] = filepath;
		handler = new OpenFileRequestHandler();
		resultsList = handler.handle(args);
		super.updateResults(resultsList);
	}
}
