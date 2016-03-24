package cow.model;

import java.util.ArrayList;

import cow.data.Result;
import cow.data.requesthandler.OrderedPatternRequestHandler;
import cow.data.requesthandler.RequestHandler;
import cow.data.requesthandler.UnorderedPatternRequestHandler;

public class PatternModel extends AbstractCoWModelWithImportAndTextAndPatternValidation {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();

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
		super.updateResults(resultsList);
	}

	public String trimText(String text) {
		return text.substring(1);
	}

}
