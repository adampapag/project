package cow.model;

import java.util.ArrayList;

import cow.data.Result;
import cow.model.requesthandler.CrucialityRequestHandler;
import cow.model.requesthandler.RequestHandler;

public class CrucialityModel extends
		AbstractCoWModelWithImportAndTextAndPatternValidation {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();

	public void crucialityRequest(ArrayList<String> patternList, String text,
			boolean ordered, ArrayList<String> alphabet) {
		resultsList.clear();
		args = new String[patternList.size() + alphabet.size() + 2];
		int index = 0;
		args[0] = text;
		for (int i = 1; i <= alphabet.size(); i++) {
			args[i] = alphabet.get(i - 1);
			index = i;
		}
		index++;

		args[index] = String.valueOf(ordered);

		index++;
		for (int i = index; i < args.length; i++) {
			args[i] = patternList.get(i - index);
		}
		handler = new CrucialityRequestHandler();
		resultsList = handler.handle(args);
		super.updateResults(resultsList);
	}

	public ArrayList<String> deduceAlphabet(String text) {
		char[] textArray = text.toCharArray();
		ArrayList<String> alphabet = new ArrayList<String>();
		for (char c : textArray) {
			String letter = String.valueOf(c);
			if (!alphabet.contains(letter)) {
				alphabet.add(letter);
			}
		}
		return alphabet;
	}

}
