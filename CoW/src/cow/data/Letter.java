package cow.data;

public class Letter {
	private String letter;
	private String morphism;

	public Letter(String letter, String morphism) {
		this.letter = letter;
		this.morphism = morphism;
	}

	public String getMorphism() {
		return morphism;
	}

	public String getLetter() {
		return letter;
	}
}
