package cow.model;

import java.util.ArrayList;

import cow.data.Result;
import cow.data.requesthandler.LoadMorphismRequestHandler;
import cow.data.requesthandler.MorphismRequestHandler;
import cow.data.requesthandler.RequestHandler;
import cow.data.requesthandler.SaveMorphismRequestHandler;
import cow.data.requesthandler.SaveRequestHandler;

public class MorphismModel implements Model {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();
	private String result = "";

	public void morphismRequest(String text, String[] morphismData,
			int iteration) {
		resultsList.clear();
		args = new String[morphismData.length + 2];
		for (int i = 0; i < morphismData.length; i++) {
			args[i] = morphismData[i];
		}
		args[args.length - 2] = text;
		args[args.length - 1] = String.valueOf(iteration);
		handler = new MorphismRequestHandler();
		resultsList = handler.handle(args);
	}

	public void saveMorphismRequest(String filepath, String[] morphismData) {
		resultsList.clear();
		args = new String[morphismData.length + 1];
		args[0] = filepath;
		for (int i = 1; i < args.length; i++) {
			args[i] = morphismData[i - 1];
		}
		handler = new SaveMorphismRequestHandler();
		resultsList = handler.handle(args);
	}

	public void loadMorphismRequest(String filepath) {
		resultsList.clear();
		args = new String[1];
		args[0] = filepath;
		handler = new LoadMorphismRequestHandler();
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

	public ArrayList<String> validate(String[] morphismData) {
		ArrayList<String> letters = new ArrayList<String>();
		ArrayList<String> morphisms = new ArrayList<String>();
		ArrayList<String> results = new ArrayList<String>();

		for (int i = 0; i < morphismData.length; i++) {
			if ((i % 2) == 0) {
				letters.add(morphismData[i]);
			} else {
				morphisms.add(morphismData[i]);
			}
		}

		int occurrences = 0;
		for (String l : letters) {
			if (l.equals("")) {
				System.out.println("Letter cannot be space.");
				results.add("Letter cannot be space." + "\n");
			} else if (l.length() > 1) {
				System.out.println("Letter must be one character.");
				results.add("Letter must be one character." + "\n");
			}
			occurrences = 0;
			for (String l2 : letters) {
				if (l2.equals(l)) {
					occurrences++;
				}
				if (occurrences > 1) {
					System.out
							.println("Letter "
									+ l
									+ "appears more than once; letters should be unique.");
					results.add("Letter "
							+ l
							+ "appears more than once; letters should be unique."
							+ "\n");
				}
			}
		}

		String symbol = "";
		for (String m : morphisms) {
			for (char c : m.toCharArray()) {
				symbol = String.valueOf(c);
				if (!(letters.contains(symbol))) {
					System.out.println("Symbol '" + c
							+ "' has no corresponding morphism.");
					results.add("Symbol '" + c
							+ "' has no corresponding morphism." + "\n");
				}
			}
		}
		return results;
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
