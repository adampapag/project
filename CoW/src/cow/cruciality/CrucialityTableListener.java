package cow.cruciality;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CrucialityTableListener implements DocumentListener {

	private JTextField textField;
	private CrucialityView gui;

	@Override
	public void insertUpdate(DocumentEvent e) {
		updateMorphismTable();

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateMorphismTable();

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		updateMorphismTable();

	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public void setGUI(CrucialityView gui) {
		this.gui = gui;
	}

	private void updateMorphismTable() {
		gui.setTable(textField.getText());
	}
}