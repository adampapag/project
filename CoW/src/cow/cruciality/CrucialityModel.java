package cow.cruciality;

import java.util.ArrayList;

import cow.data.Result;
import cow.interfaces.Model;
import cow.interfaces.RequestHandler;
import cow.io.OpenFileRequestHandler;
import cow.io.SaveRequestHandler;

public class CrucialityModel implements Model {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();
	private String result = "";

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

	public void clearResult() {
		result = "";
	}

}
