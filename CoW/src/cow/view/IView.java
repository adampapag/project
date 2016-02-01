package cow.view;

public interface IView {
	void createGUI();

	IGUI getGUI();

	void setGUI(String guiType);
}
