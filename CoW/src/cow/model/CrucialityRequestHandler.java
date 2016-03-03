package cow.model;

import java.util.ArrayList;
import java.util.Iterator;

import cow.model.Result;
import cow.model.UnorderedPatternRequestHandler;
import cow.model.OrderedPatternRequestHandler;

public class CrucialityRequestHandler implements RequestHandler {

	public ArrayList<Result> handle(String[] args) {
		ArrayList<String> alphabet = new ArrayList<String>();
		ArrayList<Result> resultList = new ArrayList<Result>();
		ArrayList<Result> containsList = new ArrayList<Result>();
		String[] params = new String[2];

		String pattern = args[0];
		String text = args[1];
		boolean ordered = true;

		params[0] = pattern;
		params[1] = text;

		if (args[2].equals("false")) {
			ordered = false;
		}

		for (int i = 3; i < args.length; i++) {
			alphabet.add(args[i]);
		}

		String textCopy = text;
		String deletedText = "";
		ArrayList<Result> resultCopy;
		while (textCopy.length() > 0) {
			if (ordered) {
				resultCopy = new OrderedPatternRequestHandler().handle(params);
			} else {
				assert !ordered;
				resultCopy = new UnorderedPatternRequestHandler()
						.handle(params);
			}

			for (Result r : resultCopy) {
				if (r.getPrefix().equals("")) {
					r.addPrefix(deletedText);
				}
				r.addPrefix("word already contains pattern: " + r.getPrefix());
				// containsList.add(r);
				resultList.add(r);
				return resultList;

			}

			deletedText = deletedText + textCopy.substring(0, 1);
			textCopy = textCopy.substring(1);
			params[1] = textCopy;
		}

		// for (Result r : containsList) {
		// resultList.add(r);
		// }
		//
		// if (!resultList.isEmpty()) {
		// return resultList;
		// }

		// System.out.println("word contains: ");
		// for (int i = 0; i < containsList.size(); i++) {
		// System.out.println(containsList.get(i).getPrefix() + "["
		// + containsList.get(i).getString() + "]"
		// + containsList.get(i).getRemainingString());
		// }
		boolean rightExtension = false;
		boolean leftExtension = false;
		for (String letter : alphabet) {
			params[1] = text + letter;
			for (Result r : cruciality(params, containsList, ordered, "suffix")) {
				rightExtension = true;
				resultList.add(r);
			}
			params[1] = letter + text;
			for (Result r : (cruciality(params, containsList, ordered, "prefix"))) {
				leftExtension = true;
				resultList.add(r);
			}
		}
		if (rightExtension && leftExtension) {
			resultList.add(new Result(text + " is bi-crucial", ""));
		} else if (rightExtension || leftExtension) {
			resultList.add(new Result(text + " is crucial", ""));
		}
		return resultList;
	}

	private ArrayList<Result> cruciality(String[] params,
			ArrayList<Result> containsList, boolean ordered,
			String extensionSide) {
		ArrayList<Result> resultList = new ArrayList<Result>();

		String text = params[1];
		String deletedText = "";

		while (text.length() > 1) {
			for (Result r : cruciality2(params, containsList, ordered,
					extensionSide)) {
				r.addPrefix(deletedText + r.getPrefix());
				resultList.add(r);
			}
			deletedText = deletedText + text.substring(0, 1);
			text = text.substring(1);
			params[1] = text;
		}
		Iterator<Result> iterator = resultList.iterator();
		Result r = null;
		while (iterator.hasNext()) {
			r = iterator.next();
			for (Result c : containsList) {
				if (extensionSide.equals("prefix")) {
					if (r.getString().equals(c.getString())
							&& r.getRemainingString().equals(
									c.getRemainingString())) {
						iterator.remove();
					}
				} else {
					assert extensionSide.equals("suffix");
					if (r.getPrefix().equals(c.getPrefix())
							&& r.getString().equals(c.getString())) {
						iterator.remove();
					}
				}
			}
		}
		return resultList;
	}

	private ArrayList<Result> cruciality2(String[] params,
			ArrayList<Result> containsList, boolean ordered,
			String extensionSide) {
		ArrayList<Result> resultList;

		if (ordered) {
			resultList = new OrderedPatternRequestHandler().handle(params);
		} else {
			assert !ordered;
			resultList = new UnorderedPatternRequestHandler().handle(params);
		}

		return resultList;
	}
}
