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

public class CoWLabController extends AbstractController {

	private CoWLabModel m;
	private CoWLabView v;
	private ActionListener listener;

	public CoWLabController(Model m, View v) {
		super(m, v);
		this.m = (CoWLabModel) m;
		this.v = (CoWLabView) v;
		checkLibrary();
	}

	private void checkLibrary() {
		if (!m.libraryExists()) {
			m.createLibrary();
		}
	}

	protected void createViewListener() {
		listener = new CoWLabListener(this);
		super.attachListener(listener);
	}

	public void createMorphismWindow() {
		AbstractModel model = new MorphismModel();
		View view = new MorphismView();
		Controller c = new MorphismController(model, view);
		c.show();
	}

	public void createPatternWindow() {
		AbstractModel model = new PatternModel();
		View view = new PatternView();
		Controller c = new PatternController(model, view);
		c.show();
	}

	public void createFactorComplexityWindow() {
		AbstractModel model = new FactorComplexityModel();
		View view = new FactorComplexityView();
		Controller c = new FactorComplexityController(model, view);
		c.show();
	}

	public void createCrucialityWindow() {
		AbstractModel model = new CrucialityModel();
		View view = new CrucialityView();
		Controller c = new CrucialityController(model, view);
		c.show();
	}

}
