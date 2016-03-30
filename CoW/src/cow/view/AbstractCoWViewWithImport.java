package cow.view;

import javax.swing.JLabel;

public abstract class AbstractCoWViewWithImport extends AbstractCoWView {

	private JLabel lblFilepath;

	public void addLabel(JLabel lblFilepath) {
		this.lblFilepath = lblFilepath;
	}

	public void setFile(String filepath) {
		lblFilepath.setText(filepath);
	}

	public abstract void setText(String text);

	public JLabel getFilepathLabel() {
		return lblFilepath;
	}

}
