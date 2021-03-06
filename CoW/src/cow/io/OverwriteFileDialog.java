package cow.io;

import javax.swing.JOptionPane;

public class OverwriteFileDialog extends JOptionPane implements Dialog {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "File already exists";
	private static final String MESSAGE = "Overwrite file?";

	public int getInput() {
		return showConfirmDialog(null, MESSAGE, TITLE,
				JOptionPane.YES_NO_OPTION);
	}

	@Override
	public String display() {
		return String.valueOf(getInput());
	}

}
