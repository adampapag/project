package cow.prototype;

import java.util.ArrayList;

import cow.model.RequestHandler;
import cow.model.Result;

public class ExportRequestHandler implements RequestHandler {

	@Override
	public ArrayList<Result> handle(String[] args) {
		String filepath = args[0];
		String text = args[1];

		TextWriter w = new TextWriter(filepath, text);
		int writeStatus = w.write();

		ArrayList<Result> resultList = new ArrayList<Result>();

		if (writeStatus == 1) {
			resultList.add(new Result("Export successful.\n", ""));
		} else if (writeStatus == 0) {
			resultList.add(new Result("Export unsuccessful.\n", ""));
		}

		return resultList;
	}

}
