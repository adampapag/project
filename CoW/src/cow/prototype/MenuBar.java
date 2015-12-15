package cow.prototype;
import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public MenuBar() {
		buildMenu();
		this.setBackground(Color.GRAY);
	}

	private void buildMenu() {
		JMenu menu = new JMenu("File");
		menu.setMnemonic('f');
		add(menu);

		JMenuItem open = new JMenuItem("Open", 'o');
		open.setToolTipText("");
		// open.addActionListener(l);
		menu.add(open);
	}
}
