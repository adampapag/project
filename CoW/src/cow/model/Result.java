package cow.model;

public class Result {

	private String string;
	private String remainingString;

	public Result(String string, String remainingString) {
		this.string = string;
		this.remainingString = remainingString;
	}

	public String getString() {
		return string;
	}

	public String getRemainingString() {
		return remainingString;
	}
}
