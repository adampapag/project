package cow.prototype;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu {
	private JFrame frame;
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();

	public Menu() {
		initializeGUI();
	}

	public void initializeGUI() {
		frame = new JFrame("CoW");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCowLab = new JLabel("CoW Lab");
		lblCowLab.setBounds(193, 29, 61, 16);
		frame.getContentPane().add(lblCowLab);

		JButton morphismsButton = new JButton("Morphisms");
		morphismsButton.setBounds(165, 69, 117, 29);
		frame.getContentPane().add(morphismsButton);
		buttonList.add(morphismsButton);

		JButton patternsButton = new JButton("Patterns");
		patternsButton.setBounds(165, 130, 117, 29);
		frame.getContentPane().add(patternsButton);
		buttonList.add(patternsButton);

		JButton crucialityButton = new JButton("Cruciality");
		crucialityButton.setBounds(165, 187, 117, 29);
		frame.getContentPane().add(crucialityButton);
		buttonList.add(crucialityButton);

		frame.setVisible(true);
	}

}
