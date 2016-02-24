package cow.controller.prototype;

import java.util.ArrayList;
import java.util.Arrays;

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

		// String pattern = "11";
		// String text = "243112";
		// String[] args2 = new String[2];
		//
		// args2[0] = pattern;
		//
		// String deletedText = "";
		// System.out.println(new OrderedPatternRequestHandler()
		// .orderedPatternSplit(pattern));

		// while (text.length() > 1) {
		// args2[1] = text;
		// ArrayList<Result> resultsList = new OrderedPatternRequestHandler()
		// .handle(args2);
		// for (int i = 0; i < resultsList.size(); i++) {
		// System.out.println("pattern found: " + deletedText
		// + resultsList.get(i).getPrefix() + "["
		// + resultsList.get(i).getString() + "]"
		// + resultsList.get(i).getRemainingString());
		// }
		// deletedText = deletedText + text.substring(0, 1);
		// text = text.substring(1);
		// System.out.println(text);
		// }

		int alphaSize = 2;
		int lengthFrom = 1;
		int lengthTo = 4;
		int currentLength = 0;
		ArrayList<String> words = new ArrayList<String>();

		currentLength = 1;

		// initiate array {0, ..., k}
		for (int i = 0; i < alphaSize; i++) {
			words.add(String.valueOf(i));
		}

		while (currentLength < lengthTo) {
			for (int alphaIndex = 0; alphaIndex < Math.pow(alphaSize,
					currentLength); alphaIndex++) {
				String current = words.remove(0);
				for (int suffix = 0; suffix < alphaSize; suffix++) {
					words.add(current + suffix);
				}
			}
			currentLength++;
			if (currentLength >= lengthFrom)
				System.out.println(Arrays.toString(words.toArray()));
		}
	}
}
