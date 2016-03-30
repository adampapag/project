package cow.model;

import java.util.ArrayList;

import cow.data.Result;
import cow.model.requesthandler.ImportRequestHandler;
import cow.model.requesthandler.RequestHandler;

/**
 * Abstract class which all CoW classes with the import functionality extend.
 * 
 * @author Adam Papageorgiou
 *
 * @see AbstractCoWModel
 * @see AbstractModel
 * @see Model
 */
public abstract class AbstractCoWModelWithImport extends AbstractCoWModel {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();

	/**
	 * Called from Controller, creates appropriate RequestHandler for import
	 * handling.
	 * 
	 * @param filepath
	 *            String reference to path the requested file is located
	 * 
	 * @see RequestHandler
	 */
	public void openFileRequest(String filepath) {
		resultsList.clear();
		args = new String[1];
		args[0] = filepath;
		handler = new ImportRequestHandler();
		resultsList = handler.handle(args);
		super.updateResults(resultsList);
	}
}
