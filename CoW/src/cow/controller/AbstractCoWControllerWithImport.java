package cow.controller;

import java.io.File;

import cow.io.Dialog;
import cow.io.LoadDialog;
import cow.model.AbstractCoWModelWithImport;
import cow.model.AbstractModel;
import cow.view.AbstractCoWViewWithImport;
import cow.view.View;

/**
 * Provides implementation for a CoWController with the import functionality.
 * 
 * @author Adam Papageorgiou
 * 
 * @see AbstractCoWController
 * @see AbstractController
 * @see Controller
 *
 */
public abstract class AbstractCoWControllerWithImport extends
		AbstractCoWController {

	private AbstractCoWViewWithImport v;
	private AbstractCoWModelWithImport m;

	/**
	 * Constructor initiates local references to the Model and View. Model and
	 * View are cast to their respective AbstractCoWWithImport types.
	 * 
	 * @param m
	 *            reference to the relevant Model
	 * @param v
	 *            reference to the relevant View
	 * 
	 * @see AbstractModel
	 * @see View
	 */
	public AbstractCoWControllerWithImport(AbstractModel m, View v) {
		super(m, v);
		this.m = (AbstractCoWModelWithImport) m;
		this.v = (AbstractCoWViewWithImport) v;
	}

	/**
	 * Creates a dialog that prompts the user to select a file. If a file is
	 * selected, an open file request is fired to the Model.
	 * 
	 * @see LoadDialog
	 * @see AbstractCoWModelWithImport
	 * @see AbstractCoWViewWithImport
	 * 
	 * @return if a file was selected, the filepath; else, null.
	 */
	public String chooseFile() {
		Dialog lo = new LoadDialog();
		String path = lo.display();

		if (path == null) {
			return null;
		}

		m.openFileRequest(path);

		File f = new File(path);

		v.setFile(f.getName());
		v.setText(m.getResultsList().get(0).getString());

		return f.getName();
	}

}
