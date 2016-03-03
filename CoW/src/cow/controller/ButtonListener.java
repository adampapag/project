package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import cow.model.IModel;
import cow.model.Result;
import cow.model.SymbolMapping;
import cow.view.CrucialityGUI;
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
				final String pattern = pGui.getPattern();
				if (pGui.OnWordsOrText().equals("on words")) {
					// swing worker
					int alphaSize = pGui.getAlphabetSize();
					int lengthFrom = pGui.getFromLength();
					int lengthTo = pGui.getToLength();
					int currentLength = 1;
					int avoidances = 0;
					String resultLine = "";
					ArrayList<String> words = new ArrayList<String>();

					// initiate array {0, ..., k}
					for (int i = 0; i < alphaSize; i++) {
						words.add(String.valueOf(i));
					}

					while (currentLength < lengthTo) {
						String current = "";
						String word = "";
						for (int alphaIndex = 0; alphaIndex < Math.pow(
								alphaSize, currentLength); alphaIndex++) {
							current = words.remove(0);
							for (int suffix = 0; suffix < alphaSize; suffix++) {
								words.add(current + suffix);
							}
						}
						System.out.println(Arrays.toString(words.toArray()));
						currentLength++;
						if (currentLength >= lengthFrom) {
							avoidances = words.size();
							resultLine = "Length " + currentLength + ": ";
							m.appendResultLine(resultLine);
							pGui.getResultsArea().append(resultLine);
							for (int i = 0; i < words.size(); i++) {
								int occurrences = 0;
								word = words.get(i);
								if (pGui.isOrdered()) {
									while (word.length() > 1) {
										m.orderedPatternRequest(pattern, word);
										if (!m.getResultsList().isEmpty()) {
											occurrences++;
											break;
										}
										word = word.substring(1);
									}
								} else {
									assert !pGui.isOrdered();
									while (word.length() > 1) {
										m.unorderedPatternRequest(pattern, word);
										if (!m.getResultsList().isEmpty()) {
											occurrences++;
											break;
										}
										word = word.substring(1);
									}
								}
								avoidances -= occurrences;
							}
							resultLine = avoidances + "\n";
							m.appendResultLine(resultLine);
							pGui.getResultsArea().append(resultLine);
						}
					}
					resultLine = "Pattern matching complete :)" + "\n";
					pGui.getResultsArea().append(resultLine);
				} else {
					assert pGui.OnWordsOrText().equals("text");
					// check if ordered//unordered
					if (pGui.isOrdered()) {
						System.out.println("ordered");
						// ordered execution
						w = new SwingWorker() {
							@Override
							protected Integer doInBackground() {
								String text = pGui.getText();
								String deletedText = "";
								String resultLine = "";
								if (m.isValid(pattern, text)) {
									ArrayList<Result> resultsList;
									System.out.println("text: " + text);
									try {
										while (text.length() > 1) {
											m.orderedPatternRequest(pattern,
													text);
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
															// + "\n";
															+ " ( Symbol Mapping : ";
													m.appendResultLine(resultLine);
													pGui.getResultsArea()
															.append(resultLine);
													ArrayList<SymbolMapping> symbolMap = r
															.getSymbolMap();
													for (int i = symbolMap
															.size() - 1; i >= 0; i--) {
														resultLine = symbolMap
																.get(i)
																.getSymbol()
																+ " -> "
																+ symbolMap
																		.get(i)
																		.getSymbolValue()
																+ ", ";
														pGui.getResultsArea()
																.append(resultLine);
													}
													pGui.getResultsArea()
															.append(")\n");
												}
											}
											deletedText = deletedText
													+ text.substring(0, 1);
											text = m.trimText(text);
											Thread.sleep(5);
										}
										if (pGui.getResultsArea().getText()
												.isEmpty()) {
											resultLine = "No patterns found"
													+ "\n";
											pGui.getResultsArea().append(
													resultLine);
										}
										resultLine = "Pattern matching complete :)"
												+ "\n";
										pGui.getResultsArea()
												.append(resultLine);

									} catch (Exception ex) {
										System.out
												.println("button listener exception");
										ex.printStackTrace();
									}
								} else {
									System.out.println("invalid fields");
									resultLine = "Field invalid!" + "\n";
									pGui.getResultsArea().append(resultLine);
									return 0;
								}
								return 1;
							}
						};
						w.execute();
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
											m.unorderedPatternRequest(pattern,
													text);
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
															// + "\n";
															+ " ( Symbol Mapping : ";
													m.appendResultLine(resultLine);
													pGui.getResultsArea()
															.append(resultLine);
													ArrayList<SymbolMapping> symbolMap = r
															.getSymbolMap();
													for (int i = symbolMap
															.size() - 1; i >= 0; i--) {
														resultLine = symbolMap
																.get(i)
																.getSymbol()
																+ " -> "
																+ symbolMap
																		.get(i)
																		.getSymbolValue()
																+ ", ";
														pGui.getResultsArea()
																.append(resultLine);
													}
													pGui.getResultsArea()
															.append(")\n");
												}
											}
											deletedText = deletedText
													+ text.substring(0, 1);
											text = m.trimText(text);
											Thread.sleep(5);
										}
										if (pGui.getResultsArea().getText()
												.isEmpty()) {
											resultLine = "No patterns found."
													+ "\n";
											pGui.getResultsArea().append(
													resultLine);
										}
										resultLine = "Pattern matching complete :)"
												+ "\n";
										pGui.getResultsArea()
												.append(resultLine);

									} catch (Exception ex) {
										System.out
												.println("button listener exception");
										ex.printStackTrace();
									}
								} else {
									System.out.println("invalid fields");
									resultLine = "Field invalid!" + "\n";
									pGui.getResultsArea().append(resultLine);
									return 0;
								}
								return 1;
							}
						};
						w.execute();
					}
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
						for (int i = 0; i < textCopy.length(); i++) {
							resultLine = "Length " + (i + 1) + " : ";
							m.appendResultLine(resultLine);
							fGui.getResultsArea().append(resultLine);
							for (Result r : resultsList) {
								if ((r.getString().length() - 1) == i) {
									resultLine = r.getString();
									if (!(i == textCopy.length() - 1))
										resultLine = resultLine + ", ";
									m.appendResultLine(resultLine);
									fGui.getResultsArea().append(resultLine);
								}
							}
							resultLine = "\n";
							m.appendResultLine(resultLine);
							fGui.getResultsArea().append(resultLine);
						}
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
							resultLine = "Morphism is invalid.";
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
		case "Show4":
			System.out.println("Show pressed in cruciality");
			final CrucialityGUI cGui;
			try {
				assert v.getGUI() instanceof CrucialityGUI : "GUI should be of type CrucialityGUI; is of type "
						+ v.getGUI().getClass().getName();
				cGui = (CrucialityGUI) v.getGUI();
				cGui.getResultsArea().setText("");
				m.clearResult();
				JTable patternTable = cGui.getPatternTable();
				final String[] patternList = new String[patternTable
						.getRowCount() * patternTable.getColumnCount()];
				int index = 0;
				for (int row = 0; row < patternTable.getRowCount(); row++) {
					for (int column = 0; column < patternTable.getColumnCount(); column++) {
						patternList[index] = (String) patternTable.getValueAt(
								row, column);
						index++;
					}
				}
				// final String pattern = "";
				if (cGui.OnWordsOrText().equals("on words")) {
					// swing worker
					int alphaSize = cGui.getAlphabetSize();
					int lengthFrom = cGui.getFromLength();
					int lengthTo = cGui.getToLength();
					int currentLength = 1;
					int avoidances = 0;
					String resultLine = "";

					for (String p : patternList) {
						ArrayList<String> words = new ArrayList<String>();
						ArrayList<String> alphabet = new ArrayList<String>();
						// initiate array {0, ..., k}
						for (int i = 0; i < alphaSize; i++) {
							words.add(String.valueOf(i));
							alphabet.add(String.valueOf(i));
						}
						resultLine = "Pattern =  " + p + "\n";
						m.appendResultLine(resultLine);
						cGui.getResultsArea().append(resultLine);
						while (currentLength < lengthTo) {

							String current = "";
							String word = "";
							for (int alphaIndex = 0; alphaIndex < Math.pow(
									alphaSize, currentLength); alphaIndex++) {
								current = words.remove(0);
								for (int suffix = 0; suffix < alphaSize; suffix++) {
									words.add(current + suffix);
								}
							}
							System.out
									.println(Arrays.toString(words.toArray()));
							currentLength++;
							if (currentLength >= lengthFrom) {
								avoidances = words.size();
								resultLine = "Length " + currentLength + ": \n";
								m.appendResultLine(resultLine);
								cGui.getResultsArea().append(resultLine);
								for (int i = 0; i < words.size(); i++) {
									int occurrences = 0;
									word = words.get(i);
									if (m.isValid(p, word)) {
										ArrayList<Result> resultsList;
										m.crucialityRequest(p, word,
												cGui.isOrdered(), alphabet);
										resultsList = m.getResultsList();
										if (!resultsList.isEmpty()) {
											for (Result r : resultsList) {
												if (r.getPrefix()
														.startsWith(
																"word already contains pattern")) {
													occurrences++;
												}
												if (cGui.printWordsOrNumberWords()
														.equals("print words")) {
													resultLine = r.getPrefix()
															+ r.getString()
															+ r.getRemainingString();
													m.appendResultLine(resultLine);
													cGui.getResultsArea()
															.append(resultLine);
													ArrayList<SymbolMapping> symbolMap = r
															.getSymbolMap();
													if (!symbolMap.isEmpty()) {
														// + ", ";
														resultLine = " ( Symbol Mapping : ";
														m.appendResultLine(resultLine);
														cGui.getResultsArea()
																.append(resultLine);
														for (int j = symbolMap
																.size() - 1; j >= 0; j--) {
															resultLine = symbolMap
																	.get(j)
																	.getSymbol()
																	+ " -> "
																	+ symbolMap
																			.get(j)
																			.getSymbolValue()
																	+ ", ";
															cGui.getResultsArea()
																	.append(resultLine);
														}
														cGui.getResultsArea()
																.append(")");
													}
													cGui.getResultsArea()
															.append("\n");
												}
											}
										}
									}
									avoidances -= occurrences;
								}
								if (cGui.printWordsOrNumberWords().equals(
										"number words")) {
									resultLine = avoidances + " avoid" + "\n";
									m.appendResultLine(resultLine);
									cGui.getResultsArea().append(resultLine);
								}
							}
						}
						// m.appendResultLine(resultLine);
						currentLength = 1;
						cGui.getResultsArea().append("\n");
					}
					resultLine = "Complete :)" + "\n";
					cGui.getResultsArea().append(resultLine);
				} else {
					assert cGui.OnWordsOrText().equals("text");
					System.out.println("text");
					w = new SwingWorker() {
						@Override
						protected Integer doInBackground() {
							String text = cGui.getText();
							String resultLine = "";
							for (String p : patternList) {
								if (m.isValid(p, text)) {
									ArrayList<Result> resultsList;
									resultLine = "pattern = " + p + "\n";
									m.appendResultLine(resultLine);
									cGui.getResultsArea().append(resultLine);
									try {
										ArrayList<String> alphabet = m
												.deduceAlphabet(text);
										m.crucialityRequest(p, text,
												cGui.isOrdered(), alphabet);
										resultsList = m.getResultsList();
										if (!resultsList.isEmpty()) {
											for (Result r : resultsList) {
												resultLine = r.getPrefix()
														+ r.getString()
														+ r.getRemainingString();
												m.appendResultLine(resultLine);
												cGui.getResultsArea().append(
														resultLine);
												ArrayList<SymbolMapping> symbolMap = r
														.getSymbolMap();
												if (!symbolMap.isEmpty()) {
													// + ", ";
													resultLine = " ( Symbol Mapping : ";
													m.appendResultLine(resultLine);
													cGui.getResultsArea()
															.append(resultLine);
													for (int i = symbolMap
															.size() - 1; i >= 0; i--) {
														resultLine = symbolMap
																.get(i)
																.getSymbol()
																+ " -> "
																+ symbolMap
																		.get(i)
																		.getSymbolValue()
																+ ", ";
														cGui.getResultsArea()
																.append(resultLine);
													}
													cGui.getResultsArea()
															.append(")");
												}
												cGui.getResultsArea().append(
														"\n");
											}
											// m.appendResultLine(resultLine);
											cGui.getResultsArea().append("\n");
										}

										if (cGui.getResultsArea().getText()
												.isEmpty()) {
											resultLine = "Not crucial" + "\n";
											cGui.getResultsArea().append(
													resultLine);
										}
									} catch (Exception ex) {
										System.out
												.println("button listener exception");
										ex.printStackTrace();
									}
								} else {
									System.out.println("invalid fields");
									resultLine = "Field invalid!" + "\n";
									cGui.getResultsArea().append(resultLine);
									return 0;
								}
							}
							resultLine = "Complete :)" + "\n";
							cGui.getResultsArea().append(resultLine);
							return 1;
						}
					};
					w.execute();

				}
			} catch (NumberFormatException nfe) {
				System.out.println("Error: Text in number field!");
			}
			break;
		case "Save":
			System.out.println("Save pressed");
			String filepath = "/Users/adam/Desktop/CoWoutput.txt";
			m.saveRequest(filepath);
			String statusLine = m.getResultsList().get(0).getString();
			final IGUI gui = v.getGUI();
			gui.getResultsArea().append(statusLine);
			break;
		}
	}
}