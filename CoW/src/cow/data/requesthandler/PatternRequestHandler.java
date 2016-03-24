package cow.data.requesthandler;

import java.util.ArrayList;
import java.util.Iterator;

import cow.data.Result;
import cow.data.SymbolMapping;

public abstract class PatternRequestHandler implements RequestHandler {

	public ArrayList<Result> removeSymbolicContradictions(
			ArrayList<Result> resultsList) {
		Iterator<Result> iterator = resultsList.iterator();
		Result r = null;
		while (iterator.hasNext()) {
			r = iterator.next();
			ArrayList<SymbolMapping> symbolMap = r.getSymbolMap();
			boolean contradiction = false;
			for (int i = 0; i < symbolMap.size(); i++) {
				if (!contradiction) {
					SymbolMapping currentSymbol = symbolMap.get(i);
					String symbol = currentSymbol.getSymbol();
					String symbolValue = currentSymbol.getSymbolValue();
					for (int j = 0; j < symbolMap.size(); j++) {
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
}
