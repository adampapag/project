package cow.controller;

import java.awt.event.ActionListener;

import cow.model.AbstractModel;
import cow.model.Model;
import cow.view.AbstractView;
import cow.view.View;

/**
 * 
 * AbstractController is the abstract base class for all further extension of
 * the Controller. All methods provided must be utilised by every inheritor.
 * 
 * @author Adam Papageorgiou
 * 
 * @version 1.0
 * 
 * @see Controller
 *
 */
public abstract class AbstractController implements Controller {

	private AbstractModel m;
	private AbstractView v;

	/**
	 * AbstractController constructor initiates local reference to the Model and
	 * View. Model and View are cast to their respective Abstract types.
	 * 
	 * @param m
	 *            reference to the relevant Model
	 * @param v
	 *            reference to the relevant View
	 * 
	 * @see Model
	 * @see View
	 */
	public AbstractController(Model m, View v) {
		this.m = (AbstractModel) m;
		this.v = (AbstractView) v;
	}

	/**
	 * Template method for showing the interface.
	 */
	public void show() {
		createView();
		createViewListener();
	}

	/**
	 * Initiates building of user interface.
	 * 
	 * @see View
	 */
	private void createView() {
		v.initializeGUI();
	}

	/**
	 * FactoryMethod for creating the relevant View Listener.
	 */
	protected abstract void createViewListener();

	/**
	 * Adds a Listener to the View.
	 * 
	 * @param listener
	 *            the Listener to attach to the View
	 * 
	 * @see ActionListener
	 * @see View
	 */
	protected void attachListener(ActionListener listener) {
		v.addActionListener(listener);
	}

}
