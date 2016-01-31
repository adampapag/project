package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import cow.model.IModel;
import cow.model.Result;
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
		System.out.println("\n=============================");
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
					System.out.println("ordered");
				} else {
					assert !gui.isOrdered() : "";
					System.out.println("unordered");
					// SwingUtilities.invokeLater(new Runnable() {
					// @Override
					// public void run() {
					// String text = gui.getText();
					// String pattern = gui.getPattern();
					// ArrayList<String> resultsList;
					// try {
					// while (text.length() > 1) {
					// m.unorderedPatternRequest(pattern, text);
					// resultsList = m.getResultsList();
					// if (!resultsList.isEmpty()) {
					// for (String r : resultsList) {
					// gui.getResultsArea().append(
					// "pattern(s) found: " + r
					// + "\n");
					// }
					// }
					// text = m.trimText(text);
					// }
					// if (gui.getResultsArea().getText().isEmpty()) {
					// gui.getResultsArea().append(
					// "No patterns found" + "\n");
					// }
					// gui.getResultsArea().append(
					// "Pattern matching complete :)");
					//
					// } catch (Exception e) {
					// System.out.println("listener:"
					// + e.getStackTrace());
					//
					// }
					// }
					// });
					w = new SwingWorker() {
						@Override
						protected Integer doInBackground() {
							String text = gui.getText();
							String pattern = gui.getPattern();
							String deletedText = "";
							if (m.isValid(pattern, text)) {
								ArrayList<Result> resultsList;
								System.out.println("text: " + text);
								try {
									while (text.length() > 1) {
										m.unorderedPatternRequest(pattern, text);
										resultsList = m.getResultsList();
										if (!resultsList.isEmpty()) {
											for (Result r : resultsList) {
												gui.getResultsArea()
														.append("occurrence found: "
																+ deletedText
																+ "["
																+ r.getString()
																+ "]"
																+ r.getRemainingString()
																+ "\n");
											}
										}
										deletedText = deletedText
												+ text.substring(0, 1);
										text = m.trimText(text);
										Thread.sleep(5);
									}
									if (gui.getResultsArea().getText()
											.isEmpty()) {
										gui.getResultsArea().append(
												"No patterns found" + "\n");
									}
									gui.getResultsArea().append(
											"Pattern matching complete :)");

								} catch (Exception ex) {
									System.out
											.println("button listener exception");
								}
							} else {
								System.out.println("invalid fields");
								gui.getResultsArea().append(
										"Field invalid!" + "\n");
							}
							return 0;
						}
					};
					w.execute();
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Error: Text in number field!");
			}
		}

	}
}
