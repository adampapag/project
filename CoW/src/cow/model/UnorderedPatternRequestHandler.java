package cow.model;

import java.util.ArrayList;
import java.util.Iterator;

public class UnorderedPatternRequestHandler implements RequestHandler {

	public UnorderedPatternRequestHandler() {
	}

	public ArrayList<Result> handle(String[] args) {
		String pattern = args[0];
		String text = args[1];
		ArrayList<String> patternList = orderedPatternSplit(pattern);
		ArrayList<Result> agenda = new ArrayList<Result>();
		ArrayList<Result> newAgenda = new ArrayList<Result>();
		String candidatePattern = patternList.get(0);
		for (Result r : unaryPatternMatch(candidatePattern, text, null)) {
			agenda.add(r);
		}
		// System.out.print("\nagenda: " + "[ ");
		for (int i = 0; i < agenda.size(); i++) {
			Result r = agenda.get(i);
			// System.out.print(r.getPrefix() + "(" + r.getString() + ")"
			// + r.getRemainingString() + ", ");
		}
		// System.out.print("]");
		for (int patternIndex = 1; patternIndex < patternList.size(); patternIndex++) {
			while (!agenda.isEmpty()) {
				Result candidate = agenda.remove(0);
				ArrayList<SymbolMapping> symbolMap = candidate.getSymbolMap();
				for (int i = 0; i < symbolMap.size(); i++) {
					// System.out.println(symbolMap.get(i).getSymbol() + ": "
					// + symbolMap.get(i).getSymbolValue());
				}
				candidatePattern = patternList.get(patternIndex);
				for (Result r : unaryPatternMatch(candidatePattern,
						candidate.getRemainingString(),
						candidate.getSymbolMap())) {
					r.addPrefix(candidate.getPrefix() + candidate.getString());
					// System.out.println("adding: " + r.getPrefix() + "("
					// + r.getString() + ")" + r.getRemainingString());
					newAgenda.add(r);
				}
			}
			for (int i = 0; i < newAgenda.size(); i++) {
				agenda.add(newAgenda.get(i));
			}
			newAgenda.clear();
		}
		// System.out.print("\nchecking agenda: [");
		for (int i = 0; i < agenda.size(); i++) {
			Result r = agenda.get(i);
			// System.out.print(r.getPrefix() + "[" + r.getString() + "]"
			// + r.getRemainingString() + ", ");
		}
		// System.out.print("]");
		// check agenda
		agenda = removeSymbolicContradictions(agenda);
		// System.out.print("\nreturning agenda: [");
		for (int i = 0; i < agenda.size(); i++) {
			Result r = agenda.get(i);
			// System.out.print(r.getPrefix() + r.getString()
			// + r.getRemainingString() + ", ");
		}
		// System.out.print("]");
		return agenda;

	}

	private ArrayList<Result> unaryPatternMatch(String pattern, String text,
			ArrayList<SymbolMapping> symbolMap) {
		// System.out.println("\ntext: " + text);
		// System.out.println("pattern: " + pattern);
		ArrayList<Result> resultsList = new ArrayList<Result>();
		// if (pattern.length() == 1 && text.length() == 1) {
		// resultsList.add(new Result(text, ""));
		// return resultsList;
		// }

		// if (pattern.length() == 1) {
		// for (int symbolLength = 1; symbolLength < text.length();
		// symbolLength++) {
		//
		// }
		// }

		String template = "";
		String candidate = "";
		String result = "";
		boolean okay = true;
		for (int symbolLength = 1; symbolLength <= text.length(); symbolLength++) {
			okay = true;
			// System.out.println("length: " + symbolLength);
			template = text.substring(0, symbolLength);
			// System.out.println("template: " + template);
			// candidateList = new ArrayList<String>();
			if ((template.length() * pattern.length()) <= text.length()) {
				if (pattern.length() == 1) {
					// System.out.println(pattern + " == 1, " + text);
					// System.out.println("arbitrary string");
					Result x = new Result(template, text.substring(template
							.length()));
					x.addSymbolMapping(new SymbolMapping(pattern, template));
					// ArrayList<SymbolMapping> symbolMap = x.getSymbolMap();
					// System.out.println("match: " + x.getPrefix() + "["
					// + x.getString() + "]" + x.getRemainingString());
					if (!(symbolMap == null)) {
						for (int i = 0; i < symbolMap.size(); i++) {
							// System.out.println(symbolMap.get(i).getSymbol()
							// + ": " + symbolMap.get(i).getSymbolValue());
							x.addSymbolMapping(symbolMap.get(i));
						}
					}
					// System.out.println(pattern + ": " + template);
					resultsList.add(x);
					okay = false;
				}
				// okay = true;
				for (int symbolCount = 1; symbolCount < pattern.length(); symbolCount++) {
					candidate = text.substring((symbolCount * symbolLength),
							(symbolCount * symbolLength) + symbolLength);
					// System.out.println("candidate: " + candidate);
					if (!candidate.equals(template)) {
						// System.out.println("pattern broken :" + template
						// + candidate);
						okay = false;
						break;
						// break
					}
				}
				if (okay) {
					result = "";
					for (int clauses = pattern.length(); clauses > 0; clauses--) {
						result = result + candidate;
					}
					// System.out.println("pattern found: " + result);
					Result y = new Result(result, text.substring(result
							.length()));
					String[] symbolTuple = findSymbolValue(pattern, result);
					y.addSymbolMapping(new SymbolMapping(symbolTuple[0],
							symbolTuple[1]));
					if (!(symbolMap == null)) {
						for (int i = 0; i < symbolMap.size(); i++) {
							// System.out.println(symbolMap.get(i).getSymbol()
							// + ": " + symbolMap.get(i).getSymbolValue());
							y.addSymbolMapping(symbolMap.get(i));
						}
					}
					// System.out.println(y.getPrefix() + "[" + y.getString()
					// + "]" + y.getRemainingString());
					resultsList.add(y);
				}
			} else {
				// System.out.println("not enough characters in text");
				// break
			}
		}
		return resultsList;
	}

	private ArrayList<String> orderedPatternSplit(String pattern) {
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

	private String[] findSymbolValue(String pattern, String text) {
		int symbolSize = (text.length() / pattern.length());
		String symbol = pattern.substring(0, 1);
		String symbolValue = text.substring(0, symbolSize);

		String[] symbolMap = new String[2];
		symbolMap[0] = symbol;
		symbolMap[1] = symbolValue;

		return symbolMap;

	}

	private ArrayList<Result> removeSymbolicContradictions(
			ArrayList<Result> resultsList) {
		Iterator<Result> iterator = resultsList.iterator();
		Result r = null;
		while (iterator.hasNext()) {
			r = iterator.next();
			// System.out.println(r.getString());
			ArrayList<SymbolMapping> symbolMap = r.getSymbolMap();
			boolean contradiction = false;
			for (int i = 0; i < symbolMap.size(); i++) {
				if (!contradiction) {
					SymbolMapping currentSymbol = symbolMap.get(i);
					String symbol = currentSymbol.getSymbol();
					String symbolValue = currentSymbol.getSymbolValue();
					// System.out.println(symbol + ": " + symbolValue);
					for (int j = 0; j < symbolMap.size(); j++) {
						// // System.out.println("->" +
						// symbolMap.get(j).getSymbol()
						// + ": " + symbolMap.get(j).getSymbolValue());
						String candidateSymbol = symbolMap.get(j).getSymbol();
						String candidateSymbolValue = symbolMap.get(j)
								.getSymbolValue();
						if (candidateSymbol.equals(symbol)
								&& (!candidateSymbolValue.equals(symbolValue))) {
							// System.out
							// .println("contradiction found! same symbol different value.");
							iterator.remove();
							contradiction = true;
							break;
						}
						if (candidateSymbolValue.equals(symbolValue)
								&& (!candidateSymbol.equals(symbol))) {
							// System.out
							// .println("contradiction found! same value different symbol.");
							iterator.remove();
							contradiction = true;
							break;
						}
					}
				} else {
					contradiction = false;
					break;
				}
			}
		}
		return resultsList;
	}

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
