package cow.io;

import javax.swing.JOptionPane;

public class OverwriteFileDialog extends JOptionPane {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "File already exists";
	private static final String MESSAGE = "Overwrite?";

	public int getInput() {
		// System.out.println("option: "
		// + showConfirmDialog(null, MESSAGE, TITLE,
		// JOptionPane.YES_NO_OPTION));
		return showConfirmDialog(null, MESSAGE, TITLE,
				JOptionPane.YES_NO_OPTION);
	}

}
