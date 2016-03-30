package cow.controller;

import cow.io.Dialog;
import cow.io.SaveDialog;
import cow.model.AbstractCoWModel;
import cow.model.Model;
import cow.view.AbstractCoWView;
import cow.view.View;

/**
 * The abstract class which all CoW-related Controllers will extend.
 * 
 * @author Adam Papageorgiou
 * 
 * @version 1.0
 * 
 * @see AbstractController
 * @see Controller
 *
 */
public abstract class AbstractCoWController extends AbstractController {

	private AbstractCoWModel m;
	private AbstractCoWView v;

	/**
	 * AbstractCoWController constructor initiates local reference to the Model
	 * and View. Model and View are cast to their respective AbstractCoW types.
	 * 
	 * @param m
	 *            reference to the relevant Model
	 * @param v
	 *            reference to the relevant View
	 * 
	 * @see Model
	 * @see View
	 */
	public AbstractCoWController(Model m, View v) {
		super(m, v);
		this.m = (AbstractCoWModel) m;
		this.v = (AbstractCoWView) v;
	}

	/**
	 * Inheritors of this class implement this method to execute relevant CoW
	 * computation.
	 */
	public abstract void showResults();

	/**
	 * Creates a dialog that prompts the user to select a file. If a file is
	 * selected, a save request is fired to the Model.
	 * 
	 * @see SaveDialog
	 * @see AbstractCoWModel
	 * @see AbstractCoWView
	 * 
	 * @return if a file was selected, the filepath; else, null.
	 */
	public String saveResults() {
		Dialog so = new SaveDialog();
		String filepath = so.display();

		if (filepath == null) {
			return null;
		} else {
			m.saveRequest(filepath);
			String statusLine = m.getResultsList().get(0).getString();
			v.getResultsArea().append(statusLine + "\n");
		}

		return filepath;
	}

	/**
	 * Should stop any execution. User is notified execution has been stopped.
	 * 
	 * Inheritors must override this method, and implement execution halting.
	 * 
	 * super.stop() must then be called for notifying user.
	 * 
	 * @see AbstractCoWView
	 */
	public void stop() {
		v.getResultsArea().append("Stopped.");
	}

	/**
	 * Clears the results display in the View. Clears the result maintained in
	 * the Model.
	 * 
	 * @see AbstractCoWModel
	 * @see AbstractCoWView
	 */
	public void clearResults() {
		v.getResultsArea().setText("");
		m.clearResult();
	}

}
