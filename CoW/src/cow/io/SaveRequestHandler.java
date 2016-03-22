package cow.io;

import java.io.File;
import java.util.ArrayList;

import cow.data.Result;
import cow.interfaces.RequestHandler;
import cow.io.dialog.OverwriteFileDialog;

public class SaveRequestHandler implements RequestHandler {

	@Override
	public ArrayList<Result> handle(String[] args) {
		String filepath = args[0] + ".txt";
		String text = args[1];
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
			resultList.add(new Result("Saved.", ""));
		} else if (writeStatus == 0) {
			resultList.add(new Result("Save failed.", ""));
		}

		return resultList;
	}

}
