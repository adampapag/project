package cow.cowlab;

import java.awt.event.ActionListener;

import cow.cruciality.CrucialityController;
import cow.cruciality.CrucialityModel;
import cow.cruciality.CrucialityView;
import cow.factorcomplexity.FactorComplexityController;
import cow.factorcomplexity.FactorComplexityModel;
import cow.factorcomplexity.FactorComplexityView;
import cow.interfaces.Controller;
import cow.interfaces.Model;
import cow.interfaces.View;
import cow.morphism.MorphismController;
import cow.morphism.MorphismModel;
import cow.morphism.MorphismView;
import cow.pattern.PatternController;
import cow.pattern.PatternModel;
import cow.pattern.PatternView;

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
