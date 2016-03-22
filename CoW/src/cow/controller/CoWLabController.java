package cow.controller;

import java.awt.event.ActionListener;

import cow.controller.listener.CoWLabListener;
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

public class CoWLabController implements Controller {

	private CoWLabModel m;
	private CoWLabView v;
	private ActionListener listener;

	public CoWLabController(Model m, View v) {
		this.m = (CoWLabModel) m;
		this.v = (CoWLabView) v;
		checkLibrary();
		showView();
		addListener();
	}

	private void checkLibrary() {
		if (!m.libraryExists()) {
			m.createLibrary();
		}
	}

	private void showView() {
		v.initializeGUI();
	}

	private void addListener() {
		listener = new CoWLabListener(this);
		v.addActionListener(listener);
	}

	public void createMorphismWindow() {
		Model model = new MorphismModel();
		View view = new MorphismView();
		new MorphismController(model, view);
	}

	public void createPatternWindow() {
		Model model = new PatternModel();
		View view = new PatternView();
		new PatternController(model, view);
	}

	public void createFactorComplexityWindow() {
		Model model = new FactorComplexityModel();
		View view = new FactorComplexityView();
		new FactorComplexityController(model, view);
	}

	public void createCrucialityWindow() {
		Model model = new CrucialityModel();
		View view = new CrucialityView();
		new CrucialityController(model, view);
	}

}
