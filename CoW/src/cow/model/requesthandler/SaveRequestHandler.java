package cow.model.requesthandler;

import java.io.File;
import java.util.ArrayList;

import cow.data.Result;
import cow.io.Dialog;
import cow.io.OverwriteFileDialog;
import cow.io.TextWriter;

public class SaveRequestHandler implements RequestHandler {

	@Override
	public ArrayList<Result> handle(String[] args) {
		String filepath = args[0];

		if (!filepath.endsWith(".txt.")) {
			filepath = filepath + ".txt";
		}
		
		String text = args[1];
		ArrayList<Result> resultList = new ArrayList<Result>();

		File file = new File(filepath);

		if (file.exists()) {
			Dialog o = new OverwriteFileDialog();
			String response = o.display();
			if (response.equals("1")) {
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
