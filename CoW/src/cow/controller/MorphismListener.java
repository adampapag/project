package cow.controller;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cow.view.MorphismGUI;

public class MorphismListener implements DocumentListener {

	private JTextField textField;
	private MorphismGUI gui;

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

	public void setGUI(MorphismGUI gui) {
		this.gui = gui;
	}

	private void updateMorphismTable() {
		gui.setMorphismTable(textField.getText());
	}

}
