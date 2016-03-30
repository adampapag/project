package cow.controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingWorker;

import cow.controller.listener.MorphismListener;
import cow.data.Result;
import cow.io.Dialog;
import cow.io.LoadMorphismDialog;
import cow.io.SaveMorphismDialog;
import cow.model.AbstractModel;
import cow.model.MorphismModel;
import cow.view.MorphismView;
import cow.view.View;

/**
 * Concrete implementation of the AbstractController. Controller for the
 * Morphism window.
 * 
 * @author Adam Papageorgiou
 * 
 * @see AbstractCoWController
 * @see AbstractController
 * @see Controller
 *
 */
public class MorphismController extends AbstractCoWController {

	private MorphismModel m;
	private MorphismView v;
	private ActionListener listener;
	private SwingWorker w;

	/**
	 * Constructor initiates local references to the Model and View. Model and
	 * View are cast to their respective Morphism types.
	 * 
	 * @param m
	 *            reference to the relevant Model
	 * @param v
	 *            reference to the relevant View
	 * 
	 * @see AbstractModel
	 * @see View
	 */
	public MorphismController(AbstractModel m, View v) {
		super(m, v);
		this.m = (MorphismModel) m;
		this.v = (MorphismView) v;
	}

	/**
	 * Creates the MorphismListener and passes is to the View using the super
	 * type method attachListener(listener).
	 * 
	 * @see MorphismListener
	 */
	protected void createViewListener() {
		listener = new MorphismListener(this);
		super.attachListener(listener);
	}

	/**
	 * Implementation for calculating and displaying results for Morphism
	 * execution.
	 * 
	 * @see SwingWorker
	 * @see MorphismView
	 * @see MorphismModel
	 */
	public void showResults() {
		clearResults();
		w = new SwingWorker() {
			@Override
			protected Integer doInBackground() {
				JTable morphismTable = v.getMorphismTable();
				int startIteration = 0;
				int endIteration = Integer.parseInt(v.getToIterationField()
						.getText());
				String text = "";
				String resultLine = "";

				String[] morphismData = new String[morphismTable.getRowCount()
						* morphismTable.getColumnCount()];
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
						v.getResultsArea().append(s);
					}
					return 0;
				}

				if (!v.getFromIterationField().getText().equals("")) {
					startIteration = Integer.parseInt(v.getFromIterationField()
							.getText());
				}

				for (int iteration = 0; iteration <= endIteration; iteration++) {

					m.morphismRequest(text, morphismData, iteration);
					text = m.getResultsList().get(0).getString();
					if (iteration >= startIteration) {
						resultLine = "Iteration " + iteration + ": " + text
								+ "\n";
						if (isCancelled()) {
							return 0;
						}
						m.appendResultLine(resultLine);
						v.getResultsArea().append(resultLine);
					}
				}
				return 1;
			}
		};
		w.execute();
	}

	/**
	 * Method for loading a pre-saved morphism into the grid. Model returns list
	 * of Results, the information of which is parsed into an array of String
	 * for passing to the View.
	 * 
	 * @see LoadMorphismDialog
	 * @see MorphismModel
	 * @see MorphismView
	 */
	public void loadMorphism() {
		LoadMorphismDialog lmo = new LoadMorphismDialog();
		String morphismPath = lmo.display();

		if (morphismPath == null) {
			return;
		}

		m.loadMorphismRequest(morphismPath);

		ArrayList<Result> resultList = m.getResultsList();
		String[] data = new String[resultList.size()];

		for (int i = 0; i < resultList.size(); i++) {
			data[i] = resultList.get(i).getString();
		}

		v.setTable(data);
	}

	/**
	 * Method for saving morphism grid data to file. Prompts user to select
	 * filepath via Dialog box. Parses grid data into array for passing to
	 * Model.
	 * 
	 * @return selected filepath
	 * 
	 * @see SaveMorphismDialog
	 * @see MorphismView
	 * @see MorphismModel
	 */
	public String saveMorphism() {
		Dialog smo = new SaveMorphismDialog();
		String newPath = smo.display();

		if (newPath == null) {
			return null;
		}

		JTable morphismTable = v.getMorphismTable();
		String[] morphismData = new String[morphismTable.getRowCount()
				* morphismTable.getColumnCount()];
		int index = 0;
		for (int row = 0; row < morphismTable.getRowCount(); row++) {
			for (int column = 0; column < morphismTable.getColumnCount(); column++) {
				morphismData[index] = (String) morphismTable.getValueAt(row,
						column);
				index++;
			}
		}

		m.saveMorphismRequest(newPath, morphismData);
		return newPath;
	}

	/**
	 * Stops the Worker thread execution, and calls up the hierarchy.
	 */
	public void stop() {
		w.cancel(true);
		super.stop();
	}
}
