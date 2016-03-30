package cow.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class AbstractView implements View {

	private JFrame frame;
	private ArrayList<JButton> buttonList;

	public void addActionListener(ActionListener l) {
		for (JButton b : buttonList) {
			b.addActionListener(l);
		}
	}

	protected abstract void addButtons();

	public void addButtonList(ArrayList<JButton> buttonList) {
		this.buttonList = buttonList;

	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void closeWindow() {
		frame.setVisible(false);
		frame.dispose();
	}
}
