package cow.controller.listener.table;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cow.view.AbstractCoWViewWithImportAndTable;
import cow.view.View;

/**
 * Concrete implementation of the DocumentListener. Attached to a field element,
 * any changes to the field are recorded and the relevant Table updated in
 * relation to the field's value.
 * 
 * @author Adam Papageorgiou
 *
 * @see DocumentListener
 * @see AbstractCoWViewWithImportAndTable
 */
public class TableListener implements DocumentListener {

	private AbstractCoWViewWithImportAndTable v;
	private JTextField textField;

	@Override
	public void insertUpdate(DocumentEvent e) {
		updateTable();

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateTable();

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		updateTable();

	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public void setGUI(View v) {
		this.v = (AbstractCoWViewWithImportAndTable) v;
	}

	private void updateTable() {
		v.setTable(textField.getText());
	}

}
