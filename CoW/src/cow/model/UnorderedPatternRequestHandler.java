package cow.model;

import java.util.ArrayList;

public class UnorderedPatternRequestHandler implements RequestHandler {

	public UnorderedPatternRequestHandler() {
	}

	public ArrayList<Result> handle(String[] args) {
		String pattern = args[0];
		String text = args[1];
		ArrayList<String> patternList = unaryPatternSplit(pattern);
		ArrayList<Result> agenda = new ArrayList<Result>();
		ArrayList<Result> newAgenda = new ArrayList<Result>();
		for (Result r : unaryPatternMatch(patternList.get(0), text)) {
			agenda.add(r);
		}
		System.out.print("\nagenda: " + "[ ");
		for (int i = 0; i < agenda.size(); i++) {
			Result r = agenda.get(i);
			System.out.print(r.getPrefix() + "(" + r.getString() + ")"
					+ r.getRemainingString() + ", ");
		}
		System.out.print("]");
		for (int patternIndex = 1; patternIndex < patternList.size(); patternIndex++) {
			while (!agenda.isEmpty()) {
				Result candidate = agenda.remove(0);
				for (Result r : unaryPatternMatch(
						patternList.get(patternIndex),
						candidate.getRemainingString())) {
					r.addPrefix(candidate.getPrefix() + candidate.getString());
					System.out.println("adding: " + r.getPrefix() + "("
							+ r.getString() + ")" + r.getRemainingString());
					newAgenda.add(r);
				}
			}
			for (int i = 0; i < newAgenda.size(); i++) {
				agenda.add(newAgenda.get(i));
			}
			newAgenda.clear();
		}
		System.out.print("\nreturning agenda: [");
		for (int i = 0; i < agenda.size(); i++) {
			Result r = agenda.get(i);
			System.out.print(r.getPrefix() + "(" + r.getString() + ")"
					+ r.getRemainingString() + ", ");
		}
		System.out.print("]");
		return agenda;
		// if (resultsList.isEmpty()) {
		// System.out.println("pattern broken: " + prefix + candidate);
		// okay = false;
		// } else {
		// for (Result r : resultList) {
		//
		// }
		// }
		// ArrayList<Result> resultsList = new ArrayList<Result>();
		// ArrayList<Result> prefixList = unaryPatternMatch(patternList.get(0),
		// text);
		// for (int prefixIndex = 0; prefixIndex < prefixList.size();
		// prefixIndex++) {
		// String restText = prefixList.get(prefixIndex).getRemainingString();
		// String candidate = prefixList.get(prefixIndex).getString();
		// System.out.println("prefix: " + candidate);
		// for (int patternIndex = 1; patternIndex < patternList.size();
		// patternIndex++) {
		// ArrayList<Result> candidateList = unaryPatternMatch(
		// patternList.get(patternIndex), restText);
		// if (patternIndex == (patternList.size() - 1)) {
		// resultsListList.add(candidateList);
		// } else {
		// for (Result r : candidateList) {
		// agenda.add(unaryPatternMatch(
		// patternList.get(patternIndex),
		// r.getRemainingString()));
		// }
		// }
		// for (int candidateIndex = 0; candidateIndex < candidateList
		// .size(); candidateIndex++) {
		//
		// }
		// for (Result r : unaryPatternMatch(
		// patternList.get(patternIndex), restText)) {
		// candidate = candidate + r.getString();
		// System.out.println(candidate);
		// }
		// }
		// }

		// System.out.println("\ntext length: " + text.length());
		// ArrayList<Result> resultsList = new ArrayList<Result>();
		// String template = "";
		// String candidate = "";
		// String result = "";
		// boolean okay = true;
		// for (int symbolLength = 1; symbolLength < text.length();
		// symbolLength++) {
		// System.out.println("length: " + symbolLength);
		// template = text.substring(0, symbolLength);
		// System.out.println("template: " + template);
		// if ((template.length() * pattern.length()) <= text.length()) {
		// if (pattern.length() == 1) {
		// System.out.println("arbitrary string");
		// resultsList.add(new Result(template, text
		// .substring(template.length())));
		// break;
		// }
		// okay = true;
		// for (int symbolCount = 1; symbolCount < pattern.length();
		// symbolCount++) {
		// candidate = text.substring((symbolCount * symbolLength),
		// (symbolCount * symbolLength) + symbolLength);
		// System.out.println("candidate: " + candidate);
		// if (!candidate.equals(template)) {
		// System.out.println("pattern broken :" + template
		// + candidate);
		// okay = false;
		// // break
		// }
		// }
		// if (okay) {
		// result = "";
		// for (int clauses = pattern.length(); clauses > 0; clauses--) {
		// result = result + candidate;
		// }
		// System.out.println("pattern found: " + result);
		// resultsList.add(new Result(result, text.substring(result
		// .length())));
		// }
		// } else {
		// System.out.println("not enough characters in text");
		// // break
		// }
		// }
		// return resultsList;
		// ArrayList<ArrayList<String>> resultsListList = new
		// ArrayList<ArrayList<String>>();
		// ArrayList<String> resultsList = unaryPatternMatch(patternList.get(0),
		// text);
		// for (int i = 0; ) {
		// resultsListList.add(unaryPatternMatch())
		// }
		// for (int i = 0; i < patternList.size() - 1; i++) {
		// for (String s : unaryPatternMatch(patternList.get(i), text)) {
		// resultsList.add(unaryPatternMatch(patternList.get(i + 1),
		// text.substring(s.getEndIndex)));
		// }
		// unaryPatternMatch(patternList.get(i), text);
		// }
		// ArrayList<ArrayList<String>> resultsList = new
		// ArrayList<ArrayList<String>>();
		// String template = "";
		// String candidate = "";
		// String congruentPattern = "";
		// for (int patternLength = 0; patternLength < pattern.length();
		// patternLength++) {
		// if (patternLength == 0) {
		// template = pattern.substring(0, 1);
		// break;
		// }
		// candidate = pattern.substring(patternLength, (patternLength + 1));
		// if (candidate.equals(template)) {
		// congruentPattern = congruentPattern + candidate;
		// } else {
		// resultsList.add(unaryPatternMatch(congruentPattern, text));
		// pattern = pattern.substring(congruentPattern.length());
		// System.out.println(congruentPattern);
		// }
		// }
	}

	public ArrayList<Result> unaryPatternMatch(String pattern, String text) {
		System.out.println("\ntext: " + text);
		System.out.println("pattern: " + pattern);
		ArrayList<Result> resultsList = new ArrayList<Result>();
		if (pattern.length() == 1 && text.length() == 1) {
			resultsList.add(new Result(text, ""));
			return resultsList;
		}
		String template = "";
		String candidate = "";
		String result = "";
		boolean okay = true;
		for (int symbolLength = 1; symbolLength < text.length(); symbolLength++) {
			System.out.println("length: " + symbolLength);
			template = text.substring(0, symbolLength);
			System.out.println("template: " + template);
			if ((template.length() * pattern.length()) <= text.length()) {
				if (pattern.length() == 1) {
					System.out.println("arbitrary string");
					resultsList.add(new Result(template, text
							.substring(template.length())));
					break;
				}
				okay = true;
				for (int symbolCount = 1; symbolCount < pattern.length(); symbolCount++) {
					candidate = text.substring((symbolCount * symbolLength),
							(symbolCount * symbolLength) + symbolLength);
					System.out.println("candidate: " + candidate);
					if (!candidate.equals(template)) {
						System.out.println("pattern broken :" + template
								+ candidate);
						okay = false;
						// break
					}
				}
				if (okay) {
					result = "";
					for (int clauses = pattern.length(); clauses > 0; clauses--) {
						result = result + candidate;
					}
					System.out.println("pattern found: " + result);
					resultsList.add(new Result(result, text.substring(result
							.length())));
				}
			} else {
				System.out.println("not enough characters in text");
				// break
			}
		}
		return resultsList;
	}

	public ArrayList<String> unaryPatternSplit(String pattern) {
		System.out.print("\nsplitting pattern: " + pattern);
		ArrayList<String> patternList = new ArrayList<String>();
		String current = "";
		String next = "";
		String unaryPattern = "";
		while (pattern.length() > 0) {
			for (int i = 0; i < pattern.length(); i++) {
				if (i == 0) {
					current = pattern.substring(0, 1);
					unaryPattern = current;
				} else {
					next = pattern.substring(i, i + 1);
					if (next.equals(current)) {
						unaryPattern = unaryPattern + next;
					} else {
						break;
					}
				}
			}
			System.out.println("unary pattern found: " + unaryPattern);
			patternList.add(unaryPattern);
			pattern = pattern.substring(unaryPattern.length());
		}
		return patternList;
	}

	// public ArrayList<String> pattern(String pattern, String text) {
	// System.out.println("Text " + text);
	// ArrayList<String> resultsList = new ArrayList<String>();
	// for (int i = 1; i <= (text.length() / 2); i++) {
	// System.out.println("i: " + i);
	// String first = text.substring(0, i);
	// String second = text.substring(i, i + i);
	// // xx pattern
	// if (first.equals(second)) {
	// System.out.println("xx found: " + first + second);
	// resultsList.add(first + second);
	// } else {
	// // xy pattern
	// System.out.println("xy found: " + first + second);
	// }
	// }
	// return resultsList;
	// }
	//
	// public int countOccurrences(String pattern, String text) {
	// int occurrences = 0;
	// System.out.println("Pattern is: " + pattern);
	// System.out.println("Text is: " + text);
	// // split pattern into character array
	// // while text.length > 0
	// // for all chars in pattern array
	// char[] patArray = pattern.toCharArray();
	// if (patArray.length == 2) {
	// System.out.println("Pattern is of length 2");
	// if (patArray[0] == patArray[1]) {
	// System.out.println("Squares pattern identified");
	// // for each beginning-split of text {
	// while (text.length() > 1) {
	// System.out.println("Text " + text);
	// for (int i = 1; i <= (text.length() / 2); i++) {
	// System.out.println("i: " + i);
	// String first = text.substring(0, i);
	// String second = text.substring(i, i + i);
	// // xx pattern
	// if (first.equals(second)) {
	// System.out.println("pattern found: " + first
	// + second);
	// occurrences++;
	// }
	// }
	// text = text.substring(1);
	// }
	// }
	// }
	// System.out.println("occurrences: " + occurrences);
	// return occurrences;
	// }
}
