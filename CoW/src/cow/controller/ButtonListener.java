package cow.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JTable;
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
import cow.view.dialog.LoadDialog;
import cow.view.dialog.LoadMorphismDialog;
import cow.view.dialog.SaveDialog;
import cow.view.dialog.SaveMorphismDialog;

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
			pGui = (PatternGUI) v.getGUI();
			try {
				assert v.getGUI() instanceof PatternGUI : "GUI should be of type PatternGUI; is of type "
						+ v.getGUI().getClass().getName();
				pGui.getResultsArea().setText("");
				m.clearResult();
				final String pattern = pGui.getPattern();

				if (m.isValidPattern(pattern)) {
					if (pGui.onWordsOrText().equals("on words")) {
						w = new SwingWorker() {
							@Override
							protected Integer doInBackground() {
								// swing worker
								int alphaSize = pGui.getAlphabetSize();
								int lengthFrom = pGui.getFromLength();
								int lengthTo = pGui.getToLength();
								int currentLength = 1;
								int avoidances = 0;
								boolean present = false;
								String resultLine = "";
								ArrayList<String> words = new ArrayList<String>();
								ArrayList<Result> resultList;
								ArrayList<Integer> frequencyList;
								HashMap<Integer, Integer> frequencyMap;
								ArrayList<String> avoids = new ArrayList<String>();
								ArrayList<Result> result = new ArrayList<Result>();
								for (int i = 0; i <= alphaSize; i++) {
									words.add(String.valueOf(i));
								}
								while (currentLength < lengthTo) {
									String current = "";
									String word = "";
									for (int alphaIndex = 0; alphaIndex < Math
											.pow(alphaSize + 1, currentLength); alphaIndex++) {
										current = words.remove(0);
										for (int suffix = 0; suffix <= alphaSize; suffix++) {
											words.add(current + suffix);
										}
									}
									System.out.println(Arrays.toString(words
											.toArray()));
									currentLength++;
									if (currentLength >= lengthFrom) {
										avoidances = words.size();
										frequencyList = new ArrayList<Integer>();
										frequencyMap = new HashMap<Integer, Integer>();
										resultLine = "Length " + currentLength
												+ ": ";
										m.appendResultLine(resultLine);
										pGui.getResultsArea()
												.append(resultLine);
										for (int i = 0; i < words.size(); i++) {
											int occurrences = 0;
											present = false;
											word = words.get(i);
											if (pGui.isOrdered()) {
												while (word.length() > 1) {
													m.orderedPatternRequest(
															pattern, word);
													resultList = m
															.getResultsList();
													if (!resultList.isEmpty()) {
														present = true;
														for (int j = 0; j < resultList
																.size(); j++) {
															occurrences++;
															// result.add(resultList
															// .get(j));
														}
													} else {
														avoids.add(words.get(i));
													}
													word = word.substring(1);
												}
											} else {
												assert !pGui.isOrdered();
												while (word.length() > 1) {
													m.unorderedPatternRequest(
															pattern, word);
													resultList = m
															.getResultsList();
													if (!resultList.isEmpty()) {
														present = true;
														for (int j = 0; j < resultList
																.size(); j++) {
															occurrences++;
															// result.add(resultList
															// .get(j));
														}
													} else {
														avoids.add(words.get(i));
													}
													word = word.substring(1);
												}
											}
											if (present) {
												avoidances--;
												result.add(new Result(words
														.get(i), String
														.valueOf(occurrences)));
											}
											frequencyList.add(occurrences);
										}
										if (pGui.avoidanceOrDistribution()
												.equals("avoidance")) {
											if (pGui.numberWordsOrPrintWords()
													.equals("number words")) {
												resultLine = avoidances
														+ " avoid" + "\n";
												m.appendResultLine(resultLine);
												pGui.getResultsArea().append(
														resultLine);
											} else {
												assert pGui
														.numberWordsOrPrintWords()
														.equals("print words");
												resultLine = "\n";
												m.appendResultLine(resultLine);
												pGui.getResultsArea().append(
														resultLine);
												for (String s : avoids) {
													resultLine = s + " avoids "
															+ "\n";
													m.appendResultLine(resultLine);
													pGui.getResultsArea()
															.append(resultLine);
												}
											}

										} else if (pGui
												.avoidanceOrDistribution()
												.equals("distribution")) {
											int f;
											for (int i = 0; i < frequencyList
													.size(); i++) {
												f = frequencyList.get(i);
												if (frequencyMap.containsKey(f)) {
													int oldF = frequencyMap
															.get(f);
													frequencyMap.put(f,
															(oldF + 1));
												} else {
													frequencyMap.put(f, 1);
												}
											}
											if (pGui.numberWordsOrPrintWords()
													.equals("number words")) {
												Object[][] table = new Object[2][frequencyMap
														.size() + 1];
												table[0][0] = "# of occurr.";
												table[1][0] = "# of words";
												int index = 1;
												for (int key : frequencyMap
														.keySet()) {
													table[0][index] = key;
													table[1][index] = frequencyMap
															.get(key);
													index++;
												}
												resultLine = "\n";
												m.appendResultLine(resultLine);
												pGui.getResultsArea().append(
														resultLine);
												for (int row = 0; row < 2; row++) {
													for (int col = 0; col < index; col++) {
														resultLine = String
																.valueOf(table[row][col]);
														m.appendResultLine(resultLine
																+ "\t");
														pGui.getResultsArea()
																.append(resultLine
																		+ "\t");
													}
													resultLine = "\n";
													m.appendResultLine(resultLine);
													pGui.getResultsArea()
															.append(resultLine);
												}
											} else {
												assert pGui
														.numberWordsOrPrintWords()
														.equals("print words");
												resultLine = "\n";
												m.appendResultLine(resultLine);
												pGui.getResultsArea().append(
														resultLine);
												for (Result r : result) {
													resultLine = r.getString();
													if (resultLine.length() == currentLength) {
														resultLine = resultLine
																+ ",  "
																+ r.getRemainingString()
																+ " occurrence(s)"
																+ "\n";
														m.appendResultLine(resultLine);
														pGui.getResultsArea()
																.append(resultLine);
														// resultLine = "\n";
														// m.appendResultLine(resultLine);
														// pGui.getResultsArea()
														// .append(resultLine);
													}
												}
											}
										}
										// else {
										// // print words
										// assert pGui
										// .avoidanceOrDistributionOrPrintWords()
										// .equals("print words");
										// resultLine = "\n";
										// m.appendResultLine(resultLine);
										// pGui.getResultsArea().append(
										// resultLine);
										// for (Result r : result) {
										// resultLine = r.getPrefix()
										// + r.getString()
										// + r.getRemainingString();
										// if (resultLine.length() ==
										// currentLength) {
										// resultLine = "occurrence found: "
										// + resultLine
										// + " ( Symbol Mapping : ";
										// m.appendResultLine(resultLine);
										// pGui.getResultsArea()
										// .append(resultLine);
										// ArrayList<SymbolMapping> symbolMap =
										// r
										// .getSymbolMap();
										// for (int i = symbolMap
										// .size() - 1; i >= 0; i--) {
										// resultLine = symbolMap
										// .get(i)
										// .getSymbol()
										// + " -> "
										// + symbolMap
										// .get(i)
										// .getSymbolValue()
										// + ", ";
										// m.appendResultLine(resultLine);
										// pGui.getResultsArea()
										// .append(resultLine);
										// }
										// resultLine = ")\n";
										// m.appendResultLine(resultLine);
										// pGui.getResultsArea()
										// .append(resultLine);
										// }
										// }
										// }
									}
									if (isCancelled()) {
										return 0;
									}
								}
								resultLine = "Complete." + "\n";
								pGui.getResultsArea().append(resultLine);
								return 1;
							}
						};
						w.execute();
					} else {
						assert pGui.onWordsOrText().equals("text");
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
									if (m.isValidText(text)) {
										ArrayList<Result> resultsList;
										System.out.println("text: " + text);
										try {
											while (text.length() > 1) {
												m.orderedPatternRequest(
														pattern, text);
												resultsList = m
														.getResultsList();
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
															m.appendResultLine(resultLine);
															pGui.getResultsArea()
																	.append(resultLine);
														}
														resultLine = ")\n";
														m.appendResultLine(resultLine);
														pGui.getResultsArea()
																.append(resultLine);
													}
												}
												deletedText = deletedText
														+ text.substring(0, 1);
												text = m.trimText(text);
												if (isCancelled()) {
													return 0;
												}
											}
											if (pGui.getResultsArea().getText()
													.isEmpty()) {
												resultLine = "No patterns found"
														+ "\n";
												pGui.getResultsArea().append(
														resultLine);
											}
											resultLine = "Complete :)" + "\n";
											pGui.getResultsArea().append(
													resultLine);

										} catch (Exception ex) {
											System.out
													.println("button listener exception");
											ex.printStackTrace();
										}
									} else {
										resultLine = "Invalid text" + "\n";
										pGui.getResultsArea()
												.append(resultLine);
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
									if (m.isValidText(text)) {
										ArrayList<Result> resultsList;
										System.out.println("text: " + text);
										try {
											while (text.length() > 1) {
												m.unorderedPatternRequest(
														pattern, text);
												resultsList = m
														.getResultsList();
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
															m.appendResultLine(resultLine);
															pGui.getResultsArea()
																	.append(resultLine);
														}
														resultLine = ")\n";
														m.appendResultLine(resultLine);
														pGui.getResultsArea()
																.append(resultLine);
													}
												}
												deletedText = deletedText
														+ text.substring(0, 1);
												text = m.trimText(text);
												if (isCancelled()) {
													return 0;
												}
											}
											if (pGui.getResultsArea().getText()
													.isEmpty()) {
												resultLine = "No patterns found."
														+ "\n";
												pGui.getResultsArea().append(
														resultLine);
											}
											resultLine = "Complete :)" + "\n";
											pGui.getResultsArea().append(
													resultLine);

										} catch (Exception ex) {
											System.out
													.println("button listener exception");
											ex.printStackTrace();
										}
									} else {
										resultLine = "Text too short." + "\n";
										pGui.getResultsArea()
												.append(resultLine);
										return 0;
									}
									return 1;
								}
							};
							w.execute();
						}
					}
				} else {
					pGui.getResultsArea().append("Pattern too short.");
				}
			} catch (NumberFormatException nfe) {
				String resultLine = "";
				if (pGui.isOrdered()) {
					resultLine = "fields should contain numbers only.";
				} else {
					resultLine = "k and length fields should contain numbers only.";
				}
				System.out.println(resultLine);
				m.appendResultLine(resultLine);
				pGui.getResultsArea().append(resultLine);
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
						text = text.replaceAll("[\n\r]", "");
						String textCopy = text;
						String resultLine = "";
						ArrayList<String> seenList = new ArrayList<String>();
						ArrayList<Result> resultsList = new ArrayList<Result>();
						while (text.length() > 0) {
							if (m.isValidText(text)) {
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
							} else {
								fGui.getResultsArea().append("Text empty.");
								return 0;
							}
						}
						for (int i = 0; i < textCopy.length(); i++) {
							if (isCancelled()) {
								return 0;
							}
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
				System.out.println("Error: Number format exception.");
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
						ArrayList<String> validData = m.validate(morphismData);
						if (!validData.isEmpty()) {
							for (String s : validData) {
								m.appendResultLine(s);
								mGui.getResultsArea().append(s);
							}
							return 0;
						}

						// if (m.isValid(morphismData) == false) {
						// resultLine = "Morphism is invalid.";
						// System.out.println(resultLine);
						// m.appendResultLine(resultLine);
						// mGui.getResultsArea().append(resultLine);
						// return 0;
						// }

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
								if (isCancelled()) {
									return 0;
								}
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
			cGui = (CrucialityGUI) v.getGUI();
			try {
				assert v.getGUI() instanceof CrucialityGUI : "GUI should be of type CrucialityGUI; is of type "
						+ v.getGUI().getClass().getName();
				cGui.getResultsArea().setText("");
				m.clearResult();
				JTable patternTable = cGui.getPatternTable();
				final ArrayList<String> patternList = new ArrayList<String>(
						patternTable.getRowCount()
								* patternTable.getColumnCount());
				// final String[] patternList = new String[patternTable
				// .getRowCount() * patternTable.getColumnCount()];
				// int index = 0;
				for (int row = 0; row < patternTable.getRowCount(); row++) {
					for (int column = 0; column < patternTable.getColumnCount(); column++) {
						// patternList[index] = (String)
						// patternTable.getValueAt(
						// row, column);
						patternList.add((String) patternTable.getValueAt(row,
								column));
						// index++;
					}
				}
				// final String pattern = "";
				if (cGui.OnWordsOrText().equals("on words")) {
					w = new SwingWorker() {
						@Override
						protected Integer doInBackground() {
							// swing worker
							int alphaSize = cGui.getAlphabetSize();
							int lengthFrom = cGui.getFromLength();
							int lengthTo = cGui.getToLength();
							int currentLength = 1;
							int avoidances = 0;
							int numberCrucialWords = 0;
							int numberBiCrucialWords = 0;
							String resultLine = "";
							for (String p : patternList) {
								if (!(m.isValidPattern(p))) {
									cGui.getResultsArea()
											.append("pattern '" + p
													+ "' is too short.");
								}
							}
							ArrayList<String> words = new ArrayList<String>();
							ArrayList<String> alphabet = new ArrayList<String>();
							// initiate array {0, ..., k}
							for (int i = 0; i <= alphaSize; i++) {
								words.add(String.valueOf(i));
								alphabet.add(String.valueOf(i));
							}
							while (currentLength < lengthTo) {
								String current = "";
								String word = "";
								for (int alphaIndex = 0; alphaIndex < Math.pow(
										alphaSize + 1, currentLength); alphaIndex++) {
									current = words.remove(0);
									for (int suffix = 0; suffix <= alphaSize; suffix++) {
										words.add(current + suffix);
									}
								}
								System.out.println(Arrays.toString(words
										.toArray()));
								currentLength++;
								if (currentLength >= lengthFrom) {
									avoidances = words.size();
									resultLine = "Length " + currentLength
											+ ": \n";
									m.appendResultLine(resultLine);
									cGui.getResultsArea().append(resultLine);
									for (int i = 0; i < words.size(); i++) {
										int occurrences = 0;
										word = words.get(i);
										if (m.isValidText(word)) {
											ArrayList<Result> resultsList;
											m.crucialityRequest(patternList,
													word, cGui.isOrdered(),
													alphabet);
											resultsList = m.getResultsList();
											if (!resultsList.isEmpty()) {
												for (Result r : resultsList) {
													// if (r.getPrefix()
													// .startsWith(
													// "word already contains pattern"))
													// {
													// occurrences++;
													// }
													if (!(r.getPrefix()
															.startsWith("word already contains pattern"))) {
														if (r.getPrefix()
																.startsWith(
																		"crucial")) {
															numberCrucialWords++;
														} else if (r
																.getPrefix()
																.startsWith(
																		"bi-crucial")) {
															numberBiCrucialWords++;
														}

														if (cGui.printWordsOrNumberWords()
																.equals("print words")) {
															resultLine = r
																	.getPrefix()
																	+ r.getString()
																	+ r.getRemainingString();
															m.appendResultLine(resultLine);
															cGui.getResultsArea()
																	.append(resultLine);
															ArrayList<SymbolMapping> symbolMap = r
																	.getSymbolMap();
															if (!symbolMap
																	.isEmpty()) {
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
																	m.appendResultLine(resultLine);
																	cGui.getResultsArea()
																			.append(resultLine);
																}
																resultLine = ")";
																m.appendResultLine(resultLine);
																cGui.getResultsArea()
																		.append(resultLine);
															}
															resultLine = "\n";
															m.appendResultLine(resultLine);
															cGui.getResultsArea()
																	.append(resultLine);
														}
													} else {
														// resultLine = word
														// +
														// " already contains pattern"
														// + "\n";
														// m.appendResultLine(resultLine);
														// cGui.getResultsArea()
														// .append(resultLine);
													}
												}
											}
										}
										avoidances -= occurrences;
									}
									if (cGui.printWordsOrNumberWords().equals(
											"number words")) {
										resultLine = numberCrucialWords
												+ " crucial word(s)" + "\n"
												+ numberBiCrucialWords
												+ " bi-crucial word(s)" + "\n";
										m.appendResultLine(resultLine);
										cGui.getResultsArea()
												.append(resultLine);
									}
								}
								if (isCancelled()) {
									return 0;
								}
							}

							//
							// for (String p : patternList) {
							// if (m.isValidPattern(p)) {
							// ArrayList<String> words = new
							// ArrayList<String>();
							// ArrayList<String> alphabet = new
							// ArrayList<String>();
							// // initiate array {0, ..., k}
							// for (int i = 0; i < alphaSize; i++) {
							// words.add(String.valueOf(i));
							// alphabet.add(String.valueOf(i));
							// }
							// resultLine = "Pattern =  " + p + "\n";
							// m.appendResultLine(resultLine);
							// cGui.getResultsArea().append(resultLine);
							// while (currentLength < lengthTo) {
							//
							// String current = "";
							// String word = "";
							// for (int alphaIndex = 0; alphaIndex < Math.pow(
							// alphaSize, currentLength); alphaIndex++) {
							// current = words.remove(0);
							// for (int suffix = 0; suffix < alphaSize;
							// suffix++) {
							// words.add(current + suffix);
							// }
							// }
							// System.out.println(Arrays.toString(words
							// .toArray()));
							// currentLength++;
							// if (currentLength >= lengthFrom) {
							// avoidances = words.size();
							// resultLine = "Length " + currentLength
							// + ": \n";
							// m.appendResultLine(resultLine);
							// cGui.getResultsArea().append(resultLine);
							// for (int i = 0; i < words.size(); i++) {
							// int occurrences = 0;
							// word = words.get(i);
							// if (m.isValidText(word)) {
							// ArrayList<Result> resultsList;
							// // m.crucialityRequest(p, word,
							// // cGui.isOrdered(), alphabet);
							// resultsList = m.getResultsList();
							// if (!resultsList.isEmpty()) {
							// for (Result r : resultsList) {
							// if (r.getPrefix()
							// .startsWith(
							// "word already contains pattern")) {
							// occurrences++;
							// }
							// if (cGui.printWordsOrNumberWords()
							// .equals("print words")) {
							// resultLine = r
							// .getPrefix()
							// + r.getString()
							// + r.getRemainingString();
							// m.appendResultLine(resultLine);
							// cGui.getResultsArea()
							// .append(resultLine);
							// ArrayList<SymbolMapping> symbolMap = r
							// .getSymbolMap();
							// if (!symbolMap
							// .isEmpty()) {
							// // + ", ";
							// resultLine = " ( Symbol Mapping : ";
							// m.appendResultLine(resultLine);
							// cGui.getResultsArea()
							// .append(resultLine);
							// for (int j = symbolMap
							// .size() - 1; j >= 0; j--) {
							// resultLine = symbolMap
							// .get(j)
							// .getSymbol()
							// + " -> "
							// + symbolMap
							// .get(j)
							// .getSymbolValue()
							// + ", ";
							// m.appendResultLine(resultLine);
							// cGui.getResultsArea()
							// .append(resultLine);
							// }
							// resultLine = ")";
							// m.appendResultLine(resultLine);
							// cGui.getResultsArea()
							// .append(resultLine);
							// }
							// resultLine = "\n";
							// m.appendResultLine(resultLine);
							// cGui.getResultsArea()
							// .append(resultLine);
							// }
							// }
							// }
							// }
							// avoidances -= occurrences;
							// }
							// if
							// (cGui.printWordsOrNumberWords().equals("number words"))
							// {
							// resultLine = avoidances + " avoid" + "\n";
							// m.appendResultLine(resultLine);
							// cGui.getResultsArea().append(resultLine);
							// }
							// }
							// }
							// // m.appendResultLine(resultLine);
							// currentLength = 1;
							// cGui.getResultsArea().append("\n");
							// } else {
							// cGui.getResultsArea().append(
							// "Empty pattern field(s)\n");
							// }
							// }
							resultLine = "Complete :)" + "\n";
							cGui.getResultsArea().append(resultLine);
							return 1;
						}
					};
					w.execute();
				} else {
					assert cGui.OnWordsOrText().equals("text");
					System.out.println("text");
					w = new SwingWorker() {
						@Override
						protected Integer doInBackground() {
							String text = cGui.getText();
							String resultLine = "";
							if (m.isValidText(text)) {
								for (String p : patternList) {
									if (!(m.isValidPattern(p))) {
										cGui.getResultsArea().append(
												"pattern '" + p
														+ "' is too short.");
									}
								}
								ArrayList<Result> resultsList;
								// resultLine = "pattern = " + p + "\n";
								// m.appendResultLine(resultLine);
								// cGui.getResultsArea().append(resultLine);
								try {
									ArrayList<String> alphabet = m
											.deduceAlphabet(text);
									m.crucialityRequest(patternList, text,
											cGui.isOrdered(), alphabet);
									resultsList = m.getResultsList();
									if (!resultsList.isEmpty()) {
										for (Result r : resultsList) {
											if (isCancelled()) {
												return 0;
											}
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
												cGui.getResultsArea().append(
														resultLine);
												for (int i = symbolMap.size() - 1; i >= 0; i--) {
													resultLine = symbolMap.get(
															i).getSymbol()
															+ " -> "
															+ symbolMap
																	.get(i)
																	.getSymbolValue()
															+ ", ";
													m.appendResultLine(resultLine);
													cGui.getResultsArea()
															.append(resultLine);
												}
												resultLine = ")";
												m.appendResultLine(resultLine);
												cGui.getResultsArea().append(
														resultLine);
											}
											resultLine = "\n";
											m.appendResultLine(resultLine);
											cGui.getResultsArea().append(
													resultLine);
										}
										// m.appendResultLine(resultLine);
										cGui.getResultsArea().append("\n");
									}

									if (cGui.getResultsArea().getText()
											.isEmpty()) {
										resultLine = "neither crucial nor bi-crucial: "
												+ text + "\n";
										cGui.getResultsArea()
												.append(resultLine);
									}
								} catch (Exception ex) {
									System.out
											.println("button listener exception");
									ex.printStackTrace();
								}
								// }
								// else {
								// resultLine = "Empty pattern field(s)"
								// + "\n";
								// cGui.getResultsArea()
								// .append(resultLine);
								// return 0;
								// }

							} else {
								cGui.getResultsArea().append(
										"text field is empty\n");
							}
							resultLine = "Complete :)" + "\n";
							cGui.getResultsArea().append(resultLine);
							return 1;
						}
					};
					w.execute();

				}
			} catch (NumberFormatException nfe) {
				String resultLine = "";
				if (cGui.isOrdered()) {
					resultLine = "fields should contain numbers only.";
				} else {
					resultLine = "k and length fields should contain numbers only.";
				}
				System.out.println(resultLine);
				m.appendResultLine(resultLine);
				cGui.getResultsArea().append(resultLine);
			}
			break;
		case "Stop":
			System.out.println("Stop pressed");
			w.cancel(true);
			v.getGUI().getResultsArea().append("Stopped");
			break;
		case "Choose File":
			System.out.println("Choose file pressed");

			// w = new SwingWorker() {
			// @Override
			// protected Integer doInBackground() {
			LoadDialog lo = new LoadDialog();
			String path = lo.display();

			if (path == null) {
				return;
			}

			m.openFileRequest(path);

			File f = new File(path);

			v.getGUI().setFile(f.getName());
			v.getGUI().setText(m.getResultsList().get(0).getString());
			// return;
			// }
			// };
			// w.execute();

			break;
		case "Save":
			System.out.println("Save pressed");

			// w = new SwingWorker() {
			// @Override
			// protected Integer doInBackground() {
			SaveDialog so = new SaveDialog();
			String filePath = so.display();

			if (filePath == null) {
				return;
			} else {
				m.saveRequest(filePath);
				String statusLine = m.getResultsList().get(0).getString();
				final IGUI gui = v.getGUI();
				gui.getResultsArea().append(statusLine + "\n");
			}
			// return;
			// }
			// };
			// w.execute();

			break;
		case "Save Morphism":
			System.out.println("Save Morphism Pressed");

			SaveMorphismDialog smo = new SaveMorphismDialog();
			String newPath = smo.display();

			if (newPath == null) {
				return;
			}

			assert v.getGUI() instanceof MorphismGUI : "GUI should be of type MorphismGUI; is of type "
					+ v.getGUI().getClass().getName();
			MorphismGUI saveGUI = (MorphismGUI) v.getGUI();

			JTable morphismTable = saveGUI.getMorphismTable();
			String[] morphismData = new String[morphismTable.getRowCount()
					* morphismTable.getColumnCount()];
			int index = 0;
			for (int row = 0; row < morphismTable.getRowCount(); row++) {
				for (int column = 0; column < morphismTable.getColumnCount(); column++) {
					morphismData[index] = (String) morphismTable.getValueAt(
							row, column);
					index++;
				}
			}

			m.saveMorphismRequest(newPath, morphismData);

			break;
		case "Load Morphism":
			System.out.println("Load Morphism Pressed");

			LoadMorphismDialog lmo = new LoadMorphismDialog();
			String morphismPath = lmo.display();

			if (morphismPath == null) {
				return;
			}

			assert v.getGUI() instanceof MorphismGUI : "GUI should be of type MorphismGUI; is of type "
					+ v.getGUI().getClass().getName();
			MorphismGUI loadGUI = (MorphismGUI) v.getGUI();

			m.loadMorphismRequest(morphismPath);

			ArrayList<Result> resultList = m.getResultsList();
			String[] data = new String[resultList.size()];

			for (int i = 0; i < resultList.size(); i++) {
				data[i] = resultList.get(i).getString();
			}

			loadGUI.setTable(data);

			break;
		}
	}
}