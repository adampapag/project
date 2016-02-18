package cow.model;

public class SymbolMapping {

	private String symbol;
	private String symbolValue;

	public SymbolMapping(String symbol, String symbolValue) {
		this.symbol = symbol;
		this.symbolValue = symbolValue;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getSymbolValue() {
		return symbolValue;
	}

}
