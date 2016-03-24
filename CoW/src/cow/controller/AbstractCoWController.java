package cow.controller;

import cow.io.Dialog;
import cow.io.SaveDialog;
import cow.model.AbstractCoWModel;
import cow.model.Model;
import cow.view.AbstractCoWView;
import cow.view.View;

public abstract class AbstractCoWController extends AbstractController {

	private AbstractCoWModel m;
	private AbstractCoWView v;

	public AbstractCoWController(Model m, View v) {
		super(m, v);
		this.m = (AbstractCoWModel) m;
		this.v = (AbstractCoWView) v;
	}

	public abstract void showResults();

	public void saveResults() {
		Dialog so = new SaveDialog();
		String filePath = so.display();

		if (filePath == null) {
			return;
		} else {
			m.saveRequest(filePath);
			String statusLine = m.getResultsList().get(0).getString();
			v.getResultsArea().append(statusLine + "\n");
		}
	}

	public void stop() {
		v.getResultsArea().append("Stopped.");
	}

	public void clearResults() {
		v.getResultsArea().setText("");
		m.clearResult();
	}

}
