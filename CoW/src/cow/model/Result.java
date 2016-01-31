package cow.model;

public class Result {

	private String string;
	private String remainingString;
	private String prefix;

	public Result(String string, String remainingString) {
		this.string = string;
		this.remainingString = remainingString;
		this.prefix = "";
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

	public void addPrefix(String prefix) {
		this.prefix = prefix;
	}
}
