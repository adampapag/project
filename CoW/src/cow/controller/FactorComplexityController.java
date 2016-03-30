package cow.controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import cow.controller.listener.CrucialityListener;
import cow.controller.listener.FactorComplexityListener;
import cow.data.Result;
import cow.model.AbstractModel;
import cow.model.CrucialityModel;
import cow.model.FactorComplexityModel;
import cow.view.CrucialityView;
import cow.view.FactorComplexityView;
import cow.view.View;

/**
 * Concrete implementation of the AbstractControllerWithImport. Controller for
 * the Factor Complexity window.
 * 
 * @author Adam Papageorgiou
 * 
 * @see AbstractCoWControllerWithImport
 * @see AbstractCoWController
 * @see AbstractController
 * @see Controller
 *
 */
public class FactorComplexityController extends AbstractCoWControllerWithImport {

	private FactorComplexityModel m;
	private FactorComplexityView v;
	private ActionListener listener;
	private SwingWorker w;

	/**
	 * Constructor initiates local references to the Model and View. Model and
	 * View are cast to their respective Factor Complexity types.
	 * 
	 * @param m
	 *            reference to the relevant Model
	 * @param v
	 *            reference to the relevant View
	 * 
	 * @see AbstractModel
	 * @see View
	 */
	public FactorComplexityController(AbstractModel m, View v) {
		super(m, v);
		this.m = (FactorComplexityModel) m;
		this.v = (FactorComplexityView) v;
	}

	/**
	 * Creates the FactorComplexityListener and passes is to the View using the
	 * super type method attachListener(listener).
	 * 
	 * @see FactorComplexityListener
	 */
	protected void createViewListener() {
		listener = new FactorComplexityListener(this);
		super.attachListener(listener);
	}

	/**
	 * Implementation for calculating and displaying results for Factor
	 * Complexity execution.
	 * 
	 * @see SwingWorker
	 * @see FactorComplexityView
	 * @see FactorComplexityModel
	 */
	public void showResults() {
		clearResults();
		w = new SwingWorker() {
			@Override
			protected Integer doInBackground() {
				String text = v.getText();
				if (m.isValidText(text)) {
					String resultLine = "";
					m.factorComplexityRequest(text);
					ArrayList<Result> resultList = m.getResultsList();
					text = m.removeCarriages(text);
					for (int i = 0; i < text.length(); i++) {
						if (isCancelled()) {
							return 0;
						}
						resultLine = "Length " + (i + 1) + " : ";
						m.appendResultLine(resultLine);
						v.getResultsArea().append(resultLine);
						for (Result r : resultList) {
							if ((r.getString().length() - 1) == i) {
								resultLine = r.getString();
								if (!(i == text.length() - 1))
									resultLine = resultLine + ", ";
								m.appendResultLine(resultLine);
								v.getResultsArea().append(resultLine);
							}
						}
						resultLine = "\n";
						m.appendResultLine(resultLine);
						v.getResultsArea().append(resultLine);
					}
				} else {
					v.getResultsArea().append("Text empty.");
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
