package cow.controller;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import cow.controller.listener.FactorComplexityListener;
import cow.data.Result;
import cow.io.LoadDialog;
import cow.io.SaveDialog;
import cow.model.FactorComplexityModel;
import cow.model.Model;
import cow.view.FactorComplexityView;
import cow.view.View;

public class FactorComplexityController implements Controller {

	private FactorComplexityModel m;
	private FactorComplexityView v;
	private ActionListener listener;
	private SwingWorker w;

	public FactorComplexityController(Model m, View v) {
		this.m = (FactorComplexityModel) m;
		this.v = (FactorComplexityView) v;
		showView();
		addListener();
	}

	private void showView() {
		v.initializeGUI();
	}

	private void addListener() {
		listener = new FactorComplexityListener(this);
		v.addActionListener(listener);
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

	public void chooseFile() {
		LoadDialog lo = new LoadDialog();
		String path = lo.display();

		if (path == null) {
			return;
		}

		m.openFileRequest(path);

		File f = new File(path);

		v.setFile(f.getName());
		v.setText(m.getResultsList().get(0).getString());
	}

	public void saveResults() {
		SaveDialog so = new SaveDialog();
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
		w.cancel(true);
		v.getResultsArea().append("Stopped.");
	}

	private void clearResults() {
		v.getResultsArea().setText("");
		m.clearResult();
	}

}
