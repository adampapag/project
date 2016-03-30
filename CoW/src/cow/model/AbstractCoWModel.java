package cow.model;

import java.util.ArrayList;

import cow.data.Result;
import cow.model.requesthandler.RequestHandler;
import cow.model.requesthandler.SaveRequestHandler;

/**
 * Abstract base class through which all CoW-related methods are inherited.
 * 
 * @author Adam Papageorgiou
 *
 * @see AbstractModel
 * @see Model
 */
public abstract class AbstractCoWModel extends AbstractModel {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();
	private String result = "";

	public void updateResults(ArrayList<Result> resultsList) {
		this.resultsList = resultsList;
	}

	/**
	 * Called from Controller, creates appropriate RequestHandler for save
	 * handling.
	 * 
	 * @param filepath
	 *            String reference to path the file should be saved at
	 *            
	 * @see RequestHandler
	 */
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
