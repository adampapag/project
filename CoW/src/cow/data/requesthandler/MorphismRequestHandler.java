package cow.data.requesthandler;

import java.util.ArrayList;

import cow.data.Letter;
import cow.data.Result;

public class MorphismRequestHandler implements RequestHandler {

	@Override
	public ArrayList<Result> handle(String[] args) {
		ArrayList<Letter> letterList = new ArrayList<Letter>();
		ArrayList<Result> resultList = new ArrayList<Result>();
		for (int i = 0; i < args.length - 3; i += 2) {
			if (!(args[i] == null)) {
				letterList.add(new Letter(args[i], args[i + 1]));
			} else {
				break;
			}
		}
		String text = args[args.length - 2];
		int iteration = Integer.parseInt(args[args.length - 1]);

		resultList.add(new Result(
				morphismIteration(letterList, text, iteration), ""));

		return resultList;
	}

	private String morphismIteration(ArrayList<Letter> letterList, String text,
			int iteration) {
		int length = 1;
		String newText = "";

		if (text.equals("") && iteration == 0) {
			text = letterList.get(0).getLetter();
			return text;
		}

		while (text.length() > 0) {
			for (Letter l : letterList) {
				for (int symbolLength = 1; symbolLength < text.length() + 1; symbolLength++) {
					String candidate = text.substring(0, symbolLength);
					if (candidate.equals(l.getLetter())) {
						newText = newText + l.getMorphism();
						length = symbolLength;
						break;
					}
				}
			}
			text = text.substring(length);
		}
		return newText;
	}
}
