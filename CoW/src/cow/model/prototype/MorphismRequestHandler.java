package cow.model.prototype;

import java.util.ArrayList;

import cow.model.RequestHandler;
import cow.model.Result;

public class MorphismRequestHandler implements RequestHandler {

	@Override
	public ArrayList<Result> handle(String[] args) {
		ArrayList<Letter> letterList = new ArrayList<Letter>();
		ArrayList<Result> resultList = new ArrayList<Result>();
		for (int i = 0; i < args.length - 2; i += 2) {
			if (!(args[i] == null)) {
				letterList.add(new Letter(args[i], args[i + 1]));
				System.out.println("letter: " + args[i]);
				System.out.println("morphism: " + args[i + 1]);
			} else {
				break;
			}
		}
		String text = args[args.length - 1];
		System.out.println("text: " + text + "\n");

		resultList.add(new Result(morphismIteration(letterList, text), ""));

		return resultList;
	}

	private String morphismIteration(ArrayList<Letter> letterList, String text) {
		int length = 1;
		String newText = "";

		if (text.equals("")) {
			text = letterList.get(0).getLetter();
		}

		while (text.length() > 0) {
			System.out.println("\ntext: " + text);
			for (Letter l : letterList) {
				for (int symbolLength = 1; symbolLength < text.length() + 1; symbolLength++) {
					String candidate = text.substring(0, symbolLength);
					System.out.println("candidate: " + candidate);
					System.out.println("target: " + l.getLetter());
					if (candidate.equals(l.getLetter())) {
						System.out.println("found");
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
