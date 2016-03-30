package cow.model;

import java.util.ArrayList;

import cow.data.Result;
import cow.model.requesthandler.FactorComplexityRequestHandler;
import cow.model.requesthandler.RequestHandler;

public class FactorComplexityModel extends
		AbstractCoWModelWithImportAndTextValidation {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();

	public void factorComplexityRequest(String text) {
		resultsList.clear();
		args = new String[1];
		text = removeCarriages(text);
		ArrayList<String> seenList = new ArrayList<String>();
		ArrayList<Result> resultList = new ArrayList<Result>();

		while (text.length() > 0) {
			args[0] = text;
			handler = new FactorComplexityRequestHandler();
			ArrayList<Result> results = handler.handle(args);
			for (Result r : results) {
				String result = r.getString();
				if (!(seenList.contains(result))) {
					seenList.add(result);
					resultList.add(r);
				}
			}
			text = text.substring(1);
		}

		resultsList = resultList;
		super.updateResults(resultsList);
	}

	public String removeCarriages(String text) {
		text = text.replaceAll("[\n\r]", "");
		return text;
	}
}
