package cow.data;

/**
 * Class that models the relation between a pattern symbol and a subword of the
 * input string. SymbolMappings are usually held in a list for inconsistency
 * checking.
 * 
 * @author Adam Papageorgiou
 *
 */
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
