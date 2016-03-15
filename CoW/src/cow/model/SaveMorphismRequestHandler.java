package cow.model;

import java.io.File;
import java.util.ArrayList;

import cow.model.RequestHandler;
import cow.model.Result;
import cow.view.dialog.OverwriteFileDialog;

public class SaveMorphismRequestHandler implements RequestHandler {

	@Override
	public ArrayList<Result> handle(String[] args) {
		String filepath = args[0] + ".txt";
		String text = "";

		for (int i = 1; i < args.length; i++) {
			text = text + args[i] + "\n";
		}
		ArrayList<Result> resultList = new ArrayList<Result>();

		File file = new File(filepath);

		if (file.exists()) {
			System.out.println("already exists");
			OverwriteFileDialog o = new OverwriteFileDialog();
			int response = o.getInput();
			if (response == 1) {
				resultList.add(new Result("Save aborted.", ""));
				return resultList;
			}
		}

		TextWriter w = new TextWriter(filepath, text);
		int writeStatus = w.write();

		if (writeStatus == 1) {
			resultList.add(new Result("Morphism saved.", ""));
		} else if (writeStatus == 0) {
			resultList.add(new Result("Save failed.", ""));
		}

		return resultList;
	}

}
