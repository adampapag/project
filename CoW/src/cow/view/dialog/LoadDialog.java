package cow.view.dialog;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadDialog {

	public String display() {

		File file = null;

		String path = null;

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Text Files", "txt", "text");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(chooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			file = chooser.getSelectedFile();
			path = file.getAbsolutePath();

			return path;

		} else if (returnVal == JFileChooser.CANCEL_OPTION) {
			return null;
		}
		return null;
	}
}
