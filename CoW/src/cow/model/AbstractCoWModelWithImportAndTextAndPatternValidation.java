package cow.model;

/**
 * Abstract class which all CoW classes with the import functionality, and input
 * text and pattern validation, extend.
 * 
 * @author Adam Papageorgiou
 *
 * @see AbstractCoWModelWithImportAndTextValidation
 * @see AbstractCoWModelWithImport
 * @see AbstractCoWModel
 * @see AbstractModel
 * @see Model
 */
public abstract class AbstractCoWModelWithImportAndTextAndPatternValidation
		extends AbstractCoWModelWithImportAndTextValidation {

	/**
	 * Ensures the pattern is of a length > 1.
	 * 
	 * @param text
	 *            for validation
	 * @return true if valid, false if not
	 */
	public boolean isValidPattern(String pattern) {
		if (pattern.length() < 2) {
			return false;
		}
		return true;
	}

}