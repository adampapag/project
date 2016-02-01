package cow.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CoWGUI implements IGUI {
	private JFrame frame;
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();

	public CoWGUI() {
		initializeGUI();
	}

	@Override
	public void initializeGUI() {
		frame = new JFrame("CoW");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		addButtons();

		JLabel lblCowLab = new JLabel("CoW Lab");
		lblCowLab.setBounds(193, 29, 61, 16);
		frame.getContentPane().add(lblCowLab);

		frame.setVisible(true);
	}

	@Override
	public void addActionListener(ActionListener l) {
		for (JButton b : buttonList) {
			b.addActionListener(l);
		}
	}

	private void addButtons() {

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

		JButton factorComplexityButton = new JButton("Factor Complexity");
		factorComplexityButton.setBounds(145, 220, 157, 29);
		frame.getContentPane().add(factorComplexityButton);
		buttonList.add(factorComplexityButton);
	}

}
