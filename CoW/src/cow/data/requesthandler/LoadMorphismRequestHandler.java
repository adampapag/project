package cow.data.requesthandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import cow.data.Result;

public class LoadMorphismRequestHandler implements RequestHandler {

	@Override
	public ArrayList<Result> handle(String[] args) {
		String filepath = args[0];
		ArrayList<Result> resultList = new ArrayList<Result>();

		String line = "";
		try (InputStream fis = new FileInputStream(filepath);
				InputStreamReader isr = new InputStreamReader(fis,
						Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				resultList.add(new Result(line, ""));
			}
		} catch (FileNotFoundException fnfe) {
			System.err.println("FileNotFoundException on morphism load");
		} catch (IOException ioe) {
			System.err.println("IOException on morphism load");
		}

		return resultList;
	}

}