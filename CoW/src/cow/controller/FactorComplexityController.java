package cow.controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import cow.controller.listener.FactorComplexityListener;
import cow.data.Result;
import cow.model.AbstractModel;
import cow.model.FactorComplexityModel;
import cow.view.FactorComplexityView;
import cow.view.View;

public class FactorComplexityController extends AbstractCoWControllerWithImport {

	private FactorComplexityModel m;
	private FactorComplexityView v;
	private ActionListener listener;
	private SwingWorker w;

	public FactorComplexityController(AbstractModel m, View v) {
		super(m, v);
		this.m = (FactorComplexityModel) m;
		this.v = (FactorComplexityView) v;
	}

	protected void createViewListener() {
		listener = new FactorComplexityListener(this);
		super.attachListener(listener);
	}

	public void showResults() {
		clearResults();
		w = new SwingWorker() {
			@Override
			protected Integer doInBackground() {
				String text = v.getText();
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
						}
						text = text.substring(1);
					} else {
						v.getResultsArea().append("Text empty.");
						return 0;
					}
				}
				for (int i = 0; i < textCopy.length(); i++) {
					if (isCancelled()) {
						return 0;
					}
					resultLine = "Length " + (i + 1) + " : ";
					m.appendResultLine(resultLine);
					v.getResultsArea().append(resultLine);
					for (Result r : resultsList) {
						if ((r.getString().length() - 1) == i) {
							resultLine = r.getString();
							if (!(i == textCopy.length() - 1))
								resultLine = resultLine + ", ";
							m.appendResultLine(resultLine);
							v.getResultsArea().append(resultLine);
						}
					}
					resultLine = "\n";
					m.appendResultLine(resultLine);
					v.getResultsArea().append(resultLine);
				}
				return 1;
			}
		};
		w.execute();
	}

	public void stop() {
		w.cancel(true);
		super.stop();
	}

}
