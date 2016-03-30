package cow.data;

/**
 * Class that models a morphism substitution rule. Each rule is a letter that
 * has a corresponding word.
 * 
 * @author Adam Papageorgiou
 *
 */
public class Letter {
	private String letter;
	private String word;

	public Letter(String letter, String word) {
		this.letter = letter;
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public String getLetter() {
		return letter;
	}
}
