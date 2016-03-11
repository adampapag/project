package cow.model;

import java.io.File;
import java.util.ArrayList;

import cow.model.RequestHandler;
import cow.model.Result;
import cow.view.dialog.OverwriteFileDialog;

public class ExportRequestHandler implements RequestHandler {

	@Override
	public ArrayList<Result> handle(String[] args) {
		String filepath = args[0];
		String text = args[1];
		ArrayList<Result> resultList = new ArrayList<Result>();

		File file = new File(filepath);

		if (file.exists()) {
			System.out.println("already exists");
			OverwriteFileDialog o = new OverwriteFileDialog();
			int response = o.getInput();
			if (response == 1) {
				resultList.add(new Result("Export aborted.", ""));
				return resultList;
			}
		}

		TextWriter w = new TextWriter(filepath, text);
		int writeStatus = w.write();

		if (writeStatus == 1) {
			resultList.add(new Result("Export successful.", ""));
		} else if (writeStatus == 0) {
			resultList.add(new Result("Export unsuccessful.", ""));
		}

		return resultList;
	}

}
