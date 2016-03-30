package cow.model;

/**
 * Abstract class which all CoW classes with the import functionality, and input
 * text validation, extend.
 * 
 * @author Adam Papageorgiou
 *
 * @see AbstractCoWModelWithImport
 * @see AbstractCoWModel
 * @see AbstractModel
 * @see Model
 */
public abstract class AbstractCoWModelWithImportAndTextValidation extends
		AbstractCoWModelWithImport {

	/**
	 * Ensures the text is of a length > 0.
	 * 
	 * @param text
	 *            for validation
	 * @return true if valid, false if not
	 */
	public boolean isValidText(String text) {
		if (text.length() == 0) {
			return false;
		}
		return true;
	}
}
