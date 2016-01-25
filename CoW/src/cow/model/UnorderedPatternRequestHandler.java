package cow.model;

public class UnorderedPatternRequestHandler {

	public UnorderedPatternRequestHandler() {
	}

	public int countOccurrences(String pattern, String text) {
		int occurrences = 0;
		System.out.println("Pattern is: " + pattern);
		System.out.println("Text is: " + text);
		// split pattern into character array
		// while text.length > 0
		// for all chars in pattern array
		char[] patArray = pattern.toCharArray();
		if (patArray.length == 2) {
			System.out.println("Pattern is of length 2");
			if (patArray[0] == patArray[1]) {
				System.out.println("Squares pattern identified");
				// for each beginning-split of text {
				while (text.length() > 1) {
					System.out.println("Text " + text);
					for (int i = 1; i <= (text.length() / 2); i++) {
						System.out.println("i: " + i);
						String first = text.substring(0, i);
						String second = text.substring(i, i + i);
						// xx pattern
						if (first.equals(second)) {
							System.out.println("pattern found: " + first
									+ second);
							occurrences++;
						}
					}
					text = text.substring(1);
				}
			}
		}
		System.out.println("occurrences: " + occurrences);
		return occurrences;
	}
}
