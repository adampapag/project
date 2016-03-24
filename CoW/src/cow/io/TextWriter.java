package cow.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TextWriter {
	private String filePath;
	private String text;

	public TextWriter(String filePath, String text) {
		this.filePath = filePath;
		this.text = text;
	}

	public int write() {

		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new FileWriter(filePath));
			pw.print(text);
		} catch (IOException ioe) {
			System.out.println("IOException in TextWriter; error writing to: "
					+ filePath);
			return 0;
		}

		pw.flush();
		pw.close();

		return 1;
	}
}
