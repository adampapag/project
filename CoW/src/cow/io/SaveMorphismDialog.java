package cow.io;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveMorphismDialog {

	public String display() {

		File file = null;

		String path = null;

		JFileChooser chooser = new JFileChooser(System.getProperty("user.home")
				+ System.getProperty("file.separator") + "Cow"
				+ System.getProperty("file.separator") + "Morphisms");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Text Files", "txt", "text");
		// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(chooser);
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
