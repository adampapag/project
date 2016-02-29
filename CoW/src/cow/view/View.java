package cow.view;

public class View implements IView {
	IGUI gui;

	public View() {
		createGUI();
	}

	@Override
	public void createGUI() {
		gui = new CoWGUI();
	}

	@Override
	public IGUI getGUI() {
		return gui;
	}

	@Override
	public void setGUI(String guiType) {
		if (guiType.equals("Morphisms")) {
			gui = new MorphismGUI();
		} else if (guiType.equals("Patterns")) {
			gui = new PatternGUI();
		} else if (guiType.equals("Factor Complexity")) {
			gui = new FactorComplexityGUI();
		} else if (guiType.equals("Cruciality")) {
			gui = new CrucialityGUI();
		}
	}
}
