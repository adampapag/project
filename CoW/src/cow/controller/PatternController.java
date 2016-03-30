package cow.controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.SwingWorker;

import cow.controller.listener.FactorComplexityListener;
import cow.controller.listener.PatternListener;
import cow.data.Result;
import cow.data.SymbolMapping;
import cow.model.AbstractModel;
import cow.model.FactorComplexityModel;
import cow.model.PatternModel;
import cow.view.FactorComplexityView;
import cow.view.PatternView;
import cow.view.View;

/**
 * Concrete implementation of the AbstractControllerWithImport. Controller for
 * the Pattern window.
 * 
 * @author Adam Papageorgiou
 * 
 * @see AbstractCoWControllerWithImport
 * @see AbstractCoWController
 * @see AbstractController
 * @see Controller
 *
 */
public class PatternController extends AbstractCoWControllerWithImport {

	private PatternModel m;
	private PatternView v;
	private ActionListener listener;
	private SwingWorker w;

	/**
	 * Constructor initiates local references to the Model and View. Model and
	 * View are cast to their respective Pattern types.
	 * 
	 * @param m
	 *            reference to the relevant Model
	 * @param v
	 *            reference to the relevant View
	 * 
	 * @see AbstractModel
	 * @see View
	 */
	public PatternController(AbstractModel m, View v) {
		super(m, v);
		this.m = (PatternModel) m;
		this.v = (PatternView) v;
	}

	/**
	 * Creates the PatternListener and passes is to the View using the super
	 * type method attachListener(listener).
	 * 
	 * @see PatternListener
	 */
	protected void createViewListener() {
		listener = new PatternListener(this);
		super.attachListener(listener);
	}

	/**
	 * Implementation for calculating and displaying results for Pattern
	 * execution.
	 * 
	 * @see SwingWorker
	 * @see PatternView
	 * @see PatternModel
	 */
	public void showResults() {
		clearResults();
		w = new SwingWorker() {
			@Override
			protected Integer doInBackground() {
				final String pattern = v.getPattern();
				String resultLine = "";
				if (m.isValidPattern(pattern)) {
					if (v.onWordsOrText().equals("on words")) {
						int alphaSize = v.getAlphabetSize();
						int lengthFrom = v.getFromLength();
						int lengthTo = v.getToLength();
						int currentLength = 1;
						int avoidances = 0;
						boolean present = false;
						ArrayList<String> words = new ArrayList<String>();
						ArrayList<Result> resultList;
						ArrayList<Integer> frequencyList;
						HashMap<Integer, Integer> frequencyMap;
						ArrayList<String> avoids;
						ArrayList<Result> result;
						for (int i = 0; i <= alphaSize; i++) {
							words.add(String.valueOf(i));
						}
						while (currentLength < lengthTo) {
							result = new ArrayList<Result>();
							avoids = new ArrayList<String>();
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
								avoidances = words.size();
								frequencyList = new ArrayList<Integer>();
								frequencyMap = new HashMap<Integer, Integer>();
								resultLine = "Length " + currentLength + ": ";
								m.appendResultLine(resultLine);
								v.getResultsArea().append(resultLine);
								for (int i = 0; i < words.size(); i++) {
									int occurrences = 0;
									present = false;
									word = words.get(i);
									while (word.length() > 1) {
										m.patternRequest(pattern, word,
												v.isOrdered());
										resultList = m.getResultsList();
										if (!resultList.isEmpty()) {
											present = true;
											for (int j = 0; j < resultList
													.size(); j++) {
												occurrences++;
											}
										}
										word = word.substring(1);
									}
									if (present) {
										avoidances--;
										result.add(new Result(words.get(i),
												String.valueOf(occurrences)));
									} else {
										avoids.add(words.get(i));
									}
									frequencyList.add(occurrences);
								}
								if (v.avoidanceOrDistribution().equals(
										"avoidance")) {
									if (v.numberWordsOrPrintWords().equals(
											"number words")) {
										resultLine = avoidances + " avoid"
												+ "\n";
										m.appendResultLine(resultLine);
										v.getResultsArea().append(resultLine);
									} else {
										assert v.numberWordsOrPrintWords()
												.equals("print words");
										resultLine = "\n";
										m.appendResultLine(resultLine);
										v.getResultsArea().append(resultLine);
										for (String s : avoids) {
											resultLine = s + " avoids " + "\n";
											m.appendResultLine(resultLine);
											v.getResultsArea().append(
													resultLine);
										}
									}

								} else if (v.avoidanceOrDistribution().equals(
										"distribution")) {
									int f;
									for (int i = 0; i < frequencyList.size(); i++) {
										f = frequencyList.get(i);
										if (frequencyMap.containsKey(f)) {
											int oldF = frequencyMap.get(f);
											frequencyMap.put(f, (oldF + 1));
										} else {
											frequencyMap.put(f, 1);
										}
									}
									if (v.numberWordsOrPrintWords().equals(
											"number words")) {
										Object[][] table = new Object[2][frequencyMap
												.size() + 1];
										table[0][0] = "# of occurr.";
										table[1][0] = "# of words";
										int index = 1;
										for (int key : frequencyMap.keySet()) {
											table[0][index] = key;
											table[1][index] = frequencyMap
													.get(key);
											index++;
										}
										resultLine = "\n";
										m.appendResultLine(resultLine);
										v.getResultsArea().append(resultLine);
										for (int row = 0; row < 2; row++) {
											for (int col = 0; col < index; col++) {
												resultLine = String
														.valueOf(table[row][col]);
												m.appendResultLine(resultLine
														+ "\t");
												v.getResultsArea().append(
														resultLine + "\t");
											}
											resultLine = "\n";
											m.appendResultLine(resultLine);
											v.getResultsArea().append(
													resultLine);
										}
									} else {
										assert v.numberWordsOrPrintWords()
												.equals("print words");
										resultLine = "\n";
										m.appendResultLine(resultLine);
										v.getResultsArea().append(resultLine);
										for (Result r : result) {
											resultLine = r.getString() + ",  "
													+ r.getRemainingString()
													+ " occurrence(s)" + "\n";
											m.appendResultLine(resultLine);
											v.getResultsArea().append(
													resultLine);
										}
									}
								}
							}
							if (isCancelled()) {
								return 0;
							}
						}
						resultLine = "Complete." + "\n";
						v.getResultsArea().append(resultLine);
					} else {
						assert v.onWordsOrText().equals("text");
						// check if ordered//unordered
						String text = v.getText();
						String deletedText = "";
						if (m.isValidText(text)) {
							ArrayList<Result> resultsList;
							try {
								while (text.length() > 1) {
									m.patternRequest(pattern, text,
											v.isOrdered());
									resultsList = m.getResultsList();
									if (!resultsList.isEmpty()) {
										for (Result r : resultsList) {
											resultLine = "occurrence found: "
													+ deletedText + "["
													+ r.getPrefix()
													+ r.getString() + "]"
													+ r.getRemainingString()
													// + "\n";
													+ " ( Symbol Mapping : ";
											m.appendResultLine(resultLine);
											v.getResultsArea().append(
													resultLine);
											ArrayList<SymbolMapping> symbolMap = r
													.getSymbolMap();
											for (int i = symbolMap.size() - 1; i >= 0; i--) {
												resultLine = symbolMap.get(i)
														.getSymbol()
														+ " -> "
														+ symbolMap
																.get(i)
																.getSymbolValue()
														+ ", ";
												m.appendResultLine(resultLine);
												v.getResultsArea().append(
														resultLine);
											}
											resultLine = ")\n";
											m.appendResultLine(resultLine);
											v.getResultsArea().append(
													resultLine);
										}
									}
									deletedText = deletedText
											+ text.substring(0, 1);
									text = m.trimText(text);
									if (isCancelled()) {
										return 0;
									}
								}
								if (v.getResultsArea().getText().isEmpty()) {
									resultLine = "No patterns found" + "\n";
									v.getResultsArea().append(resultLine);
								}
								resultLine = "Complete :)" + "\n";
								v.getResultsArea().append(resultLine);

							} catch (Exception ex) {
								// format
							}
						} else {
							resultLine = "Invalid text" + "\n";
							v.getResultsArea().append(resultLine);
							return 0;
						}

					}
				} else {
					resultLine = "Invalid pattern" + "\n";
					v.getResultsArea().append(resultLine);
					return 0;
				}
				return 1;
			}
		};
		w.execute();
	}

	/**
	 * Stops the Worker thread execution, and calls up the hierarchy.
	 */
	public void stop() {
		w.cancel(true);
		super.stop();
	}

}
