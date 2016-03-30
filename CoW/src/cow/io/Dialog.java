package cow.io;

/**
 * Interface provides the default methods for a concrete Dialog class.
 * 
 * @author Adam Papageorgiou
 *
 */
public interface Dialog {

	/**
	 * Prompt user input by displaying a dialog window.
	 * 
	 * @return if selected, a filepath.
	 */
	public String display();
}
