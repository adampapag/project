package cow.controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.SwingWorker;

import cow.controller.listener.CrucialityListener;
import cow.data.Result;
import cow.data.SymbolMapping;
import cow.model.AbstractModel;
import cow.model.CrucialityModel;
import cow.view.CrucialityView;
import cow.view.View;

public class CrucialityController extends AbstractCoWControllerWithImport {

	private CrucialityModel m;
	private CrucialityView v;
	private ActionListener listener;
	private SwingWorker w;

	public CrucialityController(AbstractModel m, View v) {
		super(m, v);
		this.m = (CrucialityModel) m;
		this.v = (CrucialityView) v;
	}

	protected void createViewListener() {
		listener = new CrucialityListener(this);
		super.attachListener(listener);
	}

	public void showResults() {
		clearResults();
		w = new SwingWorker() {
			@Override
			protected Integer doInBackground() {
				JTable patternTable = v.getPatternTable();
				final ArrayList<String> patternList = new ArrayList<String>(
						patternTable.getRowCount()
								* patternTable.getColumnCount());
				for (int row = 0; row < patternTable.getRowCount(); row++) {
					for (int column = 0; column < patternTable.getColumnCount(); column++) {
						patternList.add((String) patternTable.getValueAt(row,
								column));
					}
				}
				if (v.OnWordsOrText().equals("on words")) {
					int alphaSize = v.getAlphabetSize();
					int lengthFrom = v.getFromLength();
					int lengthTo = v.getToLength();
					int currentLength = 1;
					int numberCrucialWords = 0;
					int numberBiCrucialWords = 0;
					String resultLine = "";
					for (String p : patternList) {
						if (!(m.isValidPattern(p))) {
							v.getResultsArea().append(
									"pattern '" + p + "' is too short.");
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
						currentLength++;
						if (currentLength >= lengthFrom) {
							resultLine = "Length " + currentLength + ": \n";
							m.appendResultLine(resultLine);
							v.getResultsArea().append(resultLine);
							for (int i = 0; i < words.size(); i++) {
								int occurrences = 0;
								word = words.get(i);
								if (m.isValidText(word)) {
									ArrayList<Result> resultsList;
									m.crucialityRequest(patternList, word,
											v.isOrdered(), alphabet);
									resultsList = m.getResultsList();
									if (!resultsList.isEmpty()) {
										for (Result r : resultsList) {
											if (!(r.getPrefix()
													.startsWith("word already contains pattern"))) {
												if (r.getPrefix().startsWith(
														"crucial")) {
													numberCrucialWords++;
												} else if (r.getPrefix()
														.startsWith(
																"bi-crucial")) {
													numberBiCrucialWords++;
												}

												if (v.printWordsOrNumberWords()
														.equals("print words")) {
													resultLine = r.getPrefix()
															+ r.getString()
															+ r.getRemainingString();
													m.appendResultLine(resultLine);
													v.getResultsArea().append(
															resultLine);
													ArrayList<SymbolMapping> symbolMap = r
															.getSymbolMap();
													if (!symbolMap.isEmpty()) {
														// + ", ";
														resultLine = " ( Symbol Mapping : ";
														m.appendResultLine(resultLine);
														v.getResultsArea()
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
															v.getResultsArea()
																	.append(resultLine);
														}
														resultLine = ")";
														m.appendResultLine(resultLine);
														v.getResultsArea()
																.append(resultLine);
													}
													resultLine = "\n";
													m.appendResultLine(resultLine);
													v.getResultsArea().append(
															resultLine);
												}
											}
										}
									}
								}
							}
							if (v.printWordsOrNumberWords().equals(
									"number words")) {
								resultLine = numberCrucialWords
										+ " crucial word(s)" + "\n"
										+ numberBiCrucialWords
										+ " bi-crucial word(s)" + "\n";
								m.appendResultLine(resultLine);
								v.getResultsArea().append(resultLine);
							}
						}
						if (isCancelled()) {
							return 0;
						}
					}
					resultLine = "Complete :)" + "\n";
					v.getResultsArea().append(resultLine);
					return 1;
				} else {
					assert v.OnWordsOrText().equals("text");
					String text = v.getText();
					String resultLine = "";
					if (m.isValidText(text)) {
						for (String p : patternList) {
							if (!(m.isValidPattern(p))) {
								v.getResultsArea().append(
										"pattern '" + p + "' is too short.");
							}
						}
						ArrayList<Result> resultsList;
						try {
							ArrayList<String> alphabet = m.deduceAlphabet(text);
							m.crucialityRequest(patternList, text,
									v.isOrdered(), alphabet);
							resultsList = m.getResultsList();
							if (!resultsList.isEmpty()) {
								for (Result r : resultsList) {
									if (isCancelled()) {
										return 0;
									}
									resultLine = r.getPrefix() + r.getString()
											+ r.getRemainingString();
									m.appendResultLine(resultLine);
									v.getResultsArea().append(resultLine);
									ArrayList<SymbolMapping> symbolMap = r
											.getSymbolMap();
									if (!symbolMap.isEmpty()) {
										resultLine = " ( Symbol Mapping : ";
										m.appendResultLine(resultLine);
										v.getResultsArea().append(resultLine);
										for (int i = symbolMap.size() - 1; i >= 0; i--) {
											resultLine = symbolMap.get(i)
													.getSymbol()
													+ " -> "
													+ symbolMap.get(i)
															.getSymbolValue()
													+ ", ";
											m.appendResultLine(resultLine);
											v.getResultsArea().append(
													resultLine);
										}
										resultLine = ")";
										m.appendResultLine(resultLine);
										v.getResultsArea().append(resultLine);
									}
									resultLine = "\n";
									m.appendResultLine(resultLine);
									v.getResultsArea().append(resultLine);
								}
								v.getResultsArea().append("\n");
							}

							if (v.getResultsArea().getText().isEmpty()) {
								resultLine = "neither crucial nor bi-crucial: "
										+ text + "\n";
								v.getResultsArea().append(resultLine);
							}
						} catch (Exception ex) {
							System.out.println("cruciality controller exception");
							ex.printStackTrace();
						}
					} else {
						v.getResultsArea().append("text field is empty\n");
					}
					resultLine = "Complete :)" + "\n";
					v.getResultsArea().append(resultLine);
					return 1;
				}

			}
		};
		w.execute();
	}

	public void stop() {
		w.cancel(true);
		super.stop();
	}

}
