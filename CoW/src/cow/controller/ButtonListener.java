package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import cow.model.IModel;
import cow.view.IView;
import cow.view.PatternGUI;

public class ButtonListener implements ActionListener {

	private IModel m;
	private IView v;
	private SwingWorker w;

	public ButtonListener(IModel m, IView v) {
		this.m = m;
		this.v = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		System.out.println("Button pressed: " + button);
		switch (button) {
		case "Show":
			final PatternGUI gui;
			try {
				assert v.getGUI() instanceof PatternGUI : "GUI should be of type PatternGUI; is of type "
						+ v.getGUI().getClass().getName();
				gui = (PatternGUI) v.getGUI();
				gui.getResultsArea().setText("");
				// check if ordered//unordered
				if (gui.isOrdered()) {
					System.out.println("ordered!");
				} else {
					assert !gui.isOrdered() : "";
					System.out.println("unordered!");
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							String text = gui.getText();
							String pattern = gui.getPattern();
							if (!text.equals("") && !pattern.equals("")) {
								ArrayList<String> resultsList;
								while (text.length() > 1) {
									resultsList = m.unorderedPatternRequest(
											pattern, text);
									if (!resultsList.isEmpty()) {
										for (String r : resultsList) {
											gui.getResultsArea().append(
													"pattern(s) found: " + r
															+ "\n");
										}
									}
									text = text.substring(1);
								}
								if (gui.getResultsArea().getText().isEmpty()) {
									gui.getResultsArea().append(
											"No patterns found" + "\n");
								}
								gui.getResultsArea().append(
										"Pattern matching complete :)");
							} else {
								System.out.println("Empty field");
								gui.getResultsArea().append(
										"Empty field" + "\n");
							}
						}
					});
					// w = new SwingWorker() {
					// @Override
					// protected Integer doInBackground() {
					// try {
					// String text = gui.getText();
					// String pattern = gui.getPattern();
					// while (text.length() > 1) {
					// gui.getResultsArea().append(
					// m.unorderedPatternRequest(pattern,
					// text));
					// text = text.substring(1);
					// Thread.sleep(5);
					// }
					// } catch (Exception ex) {
					// System.out.println("button listener exception");
					// }
					//
					// return 0;
					// }
					// };
					// w.execute();
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Error: Text in number field!");
				// inform user
			}
			break;
		}

	}
}
