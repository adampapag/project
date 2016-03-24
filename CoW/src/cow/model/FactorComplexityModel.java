package cow.model;

import java.util.ArrayList;

import cow.data.Result;
import cow.data.requesthandler.FactorComplexityRequestHandler;
import cow.data.requesthandler.RequestHandler;

public class FactorComplexityModel extends
		AbstractCoWModelWithImportAndTextValidation {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();

	public void factorComplexityRequest(String text) {
		resultsList.clear();
		args = new String[1];
		args[0] = text;
		handler = new FactorComplexityRequestHandler();
		resultsList = handler.handle(args);
		super.updateResults(resultsList);
	}

}
