package cow.data.requesthandler;

import java.util.ArrayList;

import cow.data.Result;
import cow.data.SymbolMapping;

public class UnorderedPatternRequestHandler extends PatternRequestHandler {

	public ArrayList<Result> handle(String[] args) {
		String pattern = args[0];
		String text = args[1];
		ArrayList<String> patternList = unorderedPatternSplit(pattern);
		ArrayList<Result> resultsList = new ArrayList<Result>();

		resultsList = unorderedPatternMatch(text, patternList);

		resultsList = removeSymbolicContradictions(resultsList);

		return resultsList;

	}

	private ArrayList<Result> unorderedPatternMatch(String text,
			ArrayList<String> patternList) {
		ArrayList<Result> agenda = new ArrayList<Result>();
		ArrayList<Result> newAgenda = new ArrayList<Result>();
		String candidatePattern = patternList.get(0);
		for (Result r : unaryPatternMatch(candidatePattern, text, null)) {
			agenda.add(r);
		}
		for (int patternIndex = 1; patternIndex < patternList.size(); patternIndex++) {
			while (!agenda.isEmpty()) {
				Result candidate = agenda.remove(0);
				ArrayList<SymbolMapping> symbolMap = candidate.getSymbolMap();
				for (int i = 0; i < symbolMap.size(); i++) {
				}
				candidatePattern = patternList.get(patternIndex);
				for (Result r : unaryPatternMatch(candidatePattern,
						candidate.getRemainingString(),
						candidate.getSymbolMap())) {
					r.addPrefix(candidate.getPrefix() + candidate.getString());
					newAgenda.add(r);
				}
			}
			for (int i = 0; i < newAgenda.size(); i++) {
				agenda.add(newAgenda.get(i));
			}
			newAgenda.clear();
		}
		agenda = removeSymbolicContradictions(agenda);
		return agenda;
	}

	private ArrayList<Result> unaryPatternMatch(String pattern, String text,
			ArrayList<SymbolMapping> symbolMap) {
		ArrayList<Result> resultsList = new ArrayList<Result>();

		String template = "";
		String candidate = "";
		String result = "";
		boolean okay = true;
		for (int symbolLength = 1; symbolLength <= text.length(); symbolLength++) {
			okay = true;
			template = text.substring(0, symbolLength);
			if ((template.length() * pattern.length()) <= text.length()) {
				if (pattern.length() == 1) {
					Result x = new Result(template, text.substring(template
							.length()));
					x.addSymbolMapping(new SymbolMapping(pattern, template));
					if (!(symbolMap == null)) {
						for (int i = 0; i < symbolMap.size(); i++) {
							x.addSymbolMapping(symbolMap.get(i));
						}
					}
					resultsList.add(x);
					okay = false;
				}
				for (int symbolCount = 1; symbolCount < pattern.length(); symbolCount++) {
					candidate = text.substring((symbolCount * symbolLength),
							(symbolCount * symbolLength) + symbolLength);
					if (!candidate.equals(template)) {
						okay = false;
						break;
					}
				}
				if (okay) {
					result = "";
					for (int clauses = pattern.length(); clauses > 0; clauses--) {
						result = result + candidate;
					}
					Result y = new Result(result, text.substring(result
							.length()));
					String[] symbolTuple = findSymbolValue(pattern, result);
					y.addSymbolMapping(new SymbolMapping(symbolTuple[0],
							symbolTuple[1]));
					if (!(symbolMap == null)) {
						for (int i = 0; i < symbolMap.size(); i++) {
							y.addSymbolMapping(symbolMap.get(i));
						}
					}
					resultsList.add(y);
				}
			} else {
				// System.out.println("not enough characters in text");
				// break
			}
		}
		return resultsList;
	}

	private ArrayList<String> unorderedPatternSplit(String pattern) {
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
}
