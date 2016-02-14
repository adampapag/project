package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import cow.model.IModel;
import cow.model.Result;
import cow.view.FactorComplexityGUI;
import cow.view.IGUI;
import cow.view.IView;
import cow.view.MorphismGUI;
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
			System.out.println("Show pressed in patterns");
			final PatternGUI pGui;
			try {
				assert v.getGUI() instanceof PatternGUI : "GUI should be of type PatternGUI; is of type "
						+ v.getGUI().getClass().getName();
				pGui = (PatternGUI) v.getGUI();
				pGui.getResultsArea().setText("");
				m.clearResult();
				// check if ordered//unordered
				if (pGui.isOrdered()) {
					System.out.println("ordered");
					// ordered execution
				} else {
					assert !pGui.isOrdered() : "";
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
							String text = pGui.getText();
							String pattern = pGui.getPattern();
							String deletedText = "";
							String resultLine = "";
							if (m.isValid(pattern, text)) {
								ArrayList<Result> resultsList;
								System.out.println("text: " + text);
								try {
									while (text.length() > 1) {
										m.unorderedPatternRequest(pattern, text);
										resultsList = m.getResultsList();
										if (!resultsList.isEmpty()) {
											for (Result r : resultsList) {
												resultLine = "occurrence found: "
														+ deletedText
														+ "["
														+ r.getPrefix()
														+ r.getString()
														+ "]"
														+ r.getRemainingString()
														+ "\n";
												m.appendResultLine(resultLine);
												pGui.getResultsArea().append(
														resultLine);
											}
										}
										deletedText = deletedText
												+ text.substring(0, 1);
										text = m.trimText(text);
										Thread.sleep(5);
									}
									if (pGui.getResultsArea().getText()
											.isEmpty()) {
										resultLine = "No patterns found" + "\n";
										pGui.getResultsArea()
												.append(resultLine);
									}
									resultLine = "Pattern matching complete :)"
											+ "\n";
									pGui.getResultsArea().append(resultLine);

								} catch (Exception ex) {
									System.out
											.println("button listener exception");
								}
							} else {
								System.out.println("invalid fields");
								resultLine = "Field invalid!" + "\n";
								pGui.getResultsArea().append(resultLine);
							}
							return 1;
						}
					};
					w.execute();
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Error: Text in number field!");
			}
			break;
		case "Show2":
			System.out.println("Show pressed in factor complexity");
			final FactorComplexityGUI fGui;
			try {
				assert v.getGUI() instanceof FactorComplexityGUI : "GUI should be of type FactorComplexityGUI; is of type "
						+ v.getGUI().getClass().getName();
				fGui = (FactorComplexityGUI) v.getGUI();
				fGui.getResultsArea().setText("");
				m.clearResult();
				w = new SwingWorker() {
					@Override
					protected Integer doInBackground() {
						String text = fGui.getText();
						String textCopy = text;
						String resultLine = "";
						ArrayList<String> seenList = new ArrayList<String>();
						ArrayList<Result> resultsList = new ArrayList<Result>();
						while (text.length() > 0) {
							System.out.println("\n text: " + text);
							m.factorComplexityRequest(text);
							ArrayList<Result> results = m.getResultsList();
							for (Result r : results) {
								String result = r.getString();
								if (!(seenList.contains(result))) {
									seenList.add(result);
									resultsList.add(r);
								}
								// for (Result seen : resultsList) {
								// System.out.println("seen: " +
								// seen.getString());
								// if (!(result.equals(seen.getString()))) {
								// resultsList.add(r);
								// }
								// }
							}
							text = text.substring(1);
						}
						resultLine = "\n Factors: ";
						System.out.println(resultLine);
						m.appendResultLine(resultLine);
						fGui.getResultsArea().append(resultLine);
						resultLine = "(";
						System.out.print(resultLine);
						m.appendResultLine(resultLine);
						fGui.getResultsArea().append(resultLine);
						for (int i = 0; i < textCopy.length(); i++) {
							resultLine = "Length " + (i + 1) + " ; {";
							System.out.print(resultLine);
							m.appendResultLine(resultLine);
							fGui.getResultsArea().append(resultLine);
							for (Result r : resultsList) {
								if ((r.getString().length() - 1) == i) {
									resultLine = r.getString();
									if (!(i == textCopy.length() - 1))
										resultLine = resultLine + ", ";
									System.out.print(resultLine);
									m.appendResultLine(resultLine);
									fGui.getResultsArea().append(resultLine);
								}
							}
							resultLine = "} ";
							System.out.print(resultLine);
							m.appendResultLine(resultLine);
							fGui.getResultsArea().append(resultLine);
						}
						resultLine = ")\n\n";
						System.out.print(resultLine);
						m.appendResultLine(resultLine);
						fGui.getResultsArea().append(resultLine);
						return 1;
					}
				};
				w.execute();
			} catch (NumberFormatException nfe) {
				System.out.println("Error: Text in number field!");
			}
			break;
		case "Show3":
			System.out.println("Show pressed in morphisms");
			final MorphismGUI mGui;
			try {
				assert v.getGUI() instanceof MorphismGUI : "GUI should be of type MorphismGUI; is of type "
						+ v.getGUI().getClass().getName();
				mGui = (MorphismGUI) v.getGUI();
				mGui.getResultsArea().setText("");
				m.clearResult();
				w = new SwingWorker() {
					@Override
					protected Integer doInBackground() {
						JTable morphismTable = mGui.getMorphismTable();
						int startIteration = 0;
						int endIteration = Integer.parseInt(mGui
								.getToIterationField().getText());
						String text = "";
						String resultLine = "";

						String[] morphismData = new String[morphismTable
								.getRowCount() * morphismTable.getColumnCount()];
						int index = 0;
						for (int row = 0; row < morphismTable.getRowCount(); row++) {
							for (int column = 0; column < morphismTable
									.getColumnCount(); column++) {
								morphismData[index] = (String) morphismTable
										.getValueAt(row, column);
								index++;
							}
						}

						if (m.isValid(morphismData) == false) {
							resultLine = "Morphism invalid; symbol in morphism has no corresponding morphism";
							System.out.println(resultLine);
							m.appendResultLine(resultLine);
							mGui.getResultsArea().append(resultLine);
							return 0;
						}

						if (!mGui.getFromIterationField().getText().equals("")) {
							startIteration = Integer.parseInt(mGui
									.getFromIterationField().getText());
						}

						for (int iteration = 0; iteration <= endIteration; iteration++) {
							m.morphismRequest(text, morphismData, iteration);
							text = m.getResultsList().get(0).getString();
							if (iteration >= startIteration) {
								resultLine = "Iteration " + iteration + ": "
										+ text + "\n";
								m.appendResultLine(resultLine);
								mGui.getResultsArea().append(resultLine);
							}
						}
						return 1;
					}
				};
				w.execute();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			break;
		case "Export":
			System.out.println("Export pressed");
			String filepath = "/Users/adam/Desktop/CoWoutput.txt";
			m.exportRequest(filepath);
			String statusLine = m.getResultsList().get(0).getString();
			final IGUI gui = v.getGUI();
			gui.getResultsArea().append(statusLine);
			break;
		}
	}
}
