package cow.model.prototype;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TextWriter {
	private String filePath;
	// private String[] text;
	private String text;

	public TextWriter(String filePath, String text) {
		this.filePath = filePath;
		this.text = text;
	}

	public int write() {

		PrintWriter pw = null;
		FileReader fr = null;

		try {
			fr = new FileReader(filePath);
			System.out.println("File already exists.");
			fr.close();
			return 0;
		} catch (FileNotFoundException fnfe) {
			System.out.println("FileNotFoundException in TextWriter; "
					+ filePath);
		} catch (IOException ioe) {
			System.out.println("IOException in TextWriter");
		}

		try {
			pw = new PrintWriter(new FileWriter(filePath));
			System.out.println("TextWriter; writing to file");

			// lines of text in array
			// for (int i = 0; i < text.length; i++) {
			// pw.println(text[i]);
			// }
			// lines of text in String variable
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
