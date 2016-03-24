package cow.controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingWorker;

import cow.controller.listener.MorphismListener;
import cow.data.Result;
import cow.io.LoadMorphismDialog;
import cow.io.SaveMorphismDialog;
import cow.model.AbstractModel;
import cow.model.MorphismModel;
import cow.view.MorphismView;
import cow.view.View;

public class MorphismController extends AbstractCoWController {

	private MorphismModel m;
	private MorphismView v;
	private ActionListener listener;
	private SwingWorker w;

	public MorphismController(AbstractModel m, View v) {
		super(m, v);
		this.m = (MorphismModel) m;
		this.v = (MorphismView) v;
	}

	protected void createViewListener() {
		listener = new MorphismListener(this);
		super.attachListener(listener);
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

	public void stop() {
		w.cancel(true);
		super.stop();
	}
}
