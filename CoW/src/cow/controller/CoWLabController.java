package cow.controller;

import java.awt.event.ActionListener;

import cow.controller.listener.CoWLabListener;
import cow.model.AbstractModel;
import cow.model.CoWLabModel;
import cow.model.CrucialityModel;
import cow.model.FactorComplexityModel;
import cow.model.Model;
import cow.model.MorphismModel;
import cow.model.PatternModel;
import cow.view.CoWLabView;
import cow.view.CrucialityView;
import cow.view.FactorComplexityView;
import cow.view.MorphismView;
import cow.view.PatternView;
import cow.view.View;

/**
 * Concrete implementation of the AbstractController. Controller for the CoW
 * menu.
 * 
 * @author Adam Papageorgiou
 * 
 * @see AbstractController
 * @see Controller
 *
 */
public class CoWLabController extends AbstractController {

	private CoWLabModel m;
	private CoWLabView v;
	private ActionListener listener;

	/**
	 * Constructor initiates local references to the Model and View. Model and
	 * View are cast to their respective CoWLab types.
	 * 
	 * @param m
	 *            reference to the relevant Model
	 * @param v
	 *            reference to the relevant View
	 * 
	 * @see Model
	 * @see View
	 */
	public CoWLabController(Model m, View v) {
		super(m, v);
		this.m = (CoWLabModel) m;
		this.v = (CoWLabView) v;
		checkLibrary();
	}

	/**
	 * Fires a request to the Model, ensuring the Morphism Library exists.
	 * 
	 * @see CoWLabModel
	 */
	private void checkLibrary() {
		if (!m.libraryExists()) {
			m.createLibrary();
		}
	}

	/**
	 * Creates the CoWLabListener and passes is to the View using the super type
	 * method attachListener(listener).
	 * 
	 * @see CoWLabListener
	 */
	protected void createViewListener() {
		listener = new CoWLabListener(this);
		super.attachListener(listener);
	}

	/**
	 * Creates the M-V-C structure for Morphisms, and displays.
	 * 
	 * @see MorphismModel
	 * @see MorphismView
	 * @see MorphismController
	 */
	public void createMorphismWindow() {
		AbstractModel model = new MorphismModel();
		View view = new MorphismView();
		Controller c = new MorphismController(model, view);
		c.show();
	}

	/**
	 * Creates the M-V-C structure for Patterns, and displays.
	 * 
	 * @see PatternModel
	 * @see PatternView
	 * @see PatternController
	 */
	public void createPatternWindow() {
		AbstractModel model = new PatternModel();
		View view = new PatternView();
		Controller c = new PatternController(model, view);
		c.show();
	}

	/**
	 * Creates the M-V-C structure for Factor Complexity, and displays.
	 * 
	 * @see FactorComplexityModel
	 * @see FactorComplexityView
	 * @see FactorComplexityController
	 */
	public void createFactorComplexityWindow() {
		AbstractModel model = new FactorComplexityModel();
		View view = new FactorComplexityView();
		Controller c = new FactorComplexityController(model, view);
		c.show();
	}

	/**
	 * Creates the M-V-C structure for Cruciality, and displays.
	 * 
	 * @see CrucialityModel
	 * @see CrucialityView
	 * @see CrucialityController
	 */
	public void createCrucialityWindow() {
		AbstractModel model = new CrucialityModel();
		View view = new CrucialityView();
		Controller c = new CrucialityController(model, view);
		c.show();
	}

}
