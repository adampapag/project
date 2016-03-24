package cow.controller;

import java.io.File;

import cow.io.Dialog;
import cow.io.LoadDialog;
import cow.model.AbstractCoWModelWithImport;
import cow.model.AbstractModel;
import cow.view.AbstractCoWViewWithImport;
import cow.view.View;

public abstract class AbstractCoWControllerWithImport extends
		AbstractCoWController {

	private AbstractCoWViewWithImport v;
	private AbstractCoWModelWithImport m;

	public AbstractCoWControllerWithImport(AbstractModel m, View v) {
		super(m, v);
		this.m = (AbstractCoWModelWithImport) m;
		this.v = (AbstractCoWViewWithImport) v;
	}

	public void chooseFile() {
		Dialog lo = new LoadDialog();
		String path = lo.display();

		if (path == null) {
			return;
		}

		m.openFileRequest(path);

		File f = new File(path);

		v.setFile(f.getName());
		v.setText(m.getResultsList().get(0).getString());
	}

}
