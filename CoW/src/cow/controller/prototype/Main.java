package cow.controller.prototype;

import java.util.ArrayList;

import cow.model.Result;
import cow.model.prototype.OrderedPatternRequestHandler;

public class Main {

	public static void main(String[] args) {
		//
		// String[] args2 = new String[50];
		// args2[0] = "1";
		// args2[1] = "2";
		// args2[2] = "2";
		// args2[3] = "1";
		// // args2[4] = "3";
		// // args2[5] = "33";
		// args2[args2.length-1] = "121";
		// System.out.println(new
		// MorphismRequestHandler().handle(args2).get(0).getString());

		String pattern = "11";
		String text = "243112";
		String[] args2 = new String[2];

		args2[0] = pattern;

		String deletedText = "";
		// System.out.println(new OrderedPatternRequestHandler()
		// .orderedPatternSplit(pattern));

		while (text.length() > 1) {
			args2[1] = text;
			ArrayList<Result> resultsList = new OrderedPatternRequestHandler()
					.handle(args2);
			for (int i = 0; i < resultsList.size(); i++) {
				System.out.println("pattern found: " + deletedText
						+ resultsList.get(i).getPrefix() + "["
						+ resultsList.get(i).getString() + "]"
						+ resultsList.get(i).getRemainingString());
			}
			deletedText = deletedText + text.substring(0, 1);
			text = text.substring(1);
			System.out.println(text);
		}

	}
}
