package cow.model;

public abstract class AbstractCoWModelWithImportAndTextAndPatternValidation extends
		AbstractCoWModelWithImportAndTextValidation {

	public boolean isValidPattern(String pattern) {
		if (pattern.length() < 2) {
			return false;
		}
		return true;
	}

}