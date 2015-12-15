package cow.view;

public interface IView {
	public void createGUI();

	public IGUI getGUI();

	public void setGUI(String guiType);
}
