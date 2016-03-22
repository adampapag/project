package cow.morphism;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingWorker;

import cow.data.Result;
import cow.interfaces.Controller;
import cow.interfaces.Model;
import cow.interfaces.View;
import cow.io.dialog.LoadMorphismDialog;
import cow.io.dialog.SaveDialog;
import cow.io.dialog.SaveMorphismDialog;

public class MorphismController implements Controller {

	private MorphismModel m;
	private MorphismView v;
	private ActionListener listener;
	private SwingWorker w;

	public MorphismController(Model m, View v) {
		this.m = (MorphismModel) m;
		this.v = (MorphismView) v;
		showView();
		addListener();
	}

	private void showView() {
		v.initializeGUI();
	}

	private void addListener() {
		listener = new MorphismListener(this);
		v.addActionListener(listener);
	}

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

	public void saveMorphism() {
		SaveMorphismDialog smo = new SaveMorphismDialog();
		String newPath = smo.display();

		if (newPath == null) {
			return;
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
