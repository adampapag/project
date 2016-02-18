package cow.model;

import java.util.ArrayList;

public class Result {

	private String string;
	private String remainingString;
	private String prefix;
	private ArrayList<SymbolMapping> symbolMap;

	public Result(String string, String remainingString) {
		this.string = string;
		this.remainingString = remainingString;
		this.prefix = "";
		this.symbolMap = new ArrayList<SymbolMapping>();
	}

	public String getString() {
		return string;
	}

	public String getRemainingString() {
		return remainingString;
	}

	public String getPrefix() {
		return prefix;
	}

	public ArrayList<SymbolMapping> getSymbolMap() {
		return symbolMap;
	}

	public void addPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void addSymbolMapping(SymbolMapping sm) {
		symbolMap.add(sm);
	}
}
