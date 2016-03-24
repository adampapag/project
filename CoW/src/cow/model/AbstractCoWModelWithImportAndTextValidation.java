package cow.model;

public abstract class AbstractCoWModelWithImportAndTextValidation extends
		AbstractCoWModelWithImport {

	public boolean isValidText(String text) {
		if (text.length() == 0) {
			return false;
		}
		return true;
	}
}
