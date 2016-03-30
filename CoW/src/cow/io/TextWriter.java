package cow.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * I/O class for writing a block of text to the specified file.
 * 
 * @author Adam Papageorgiou
 * 
 */
public class TextWriter {
	private String filePath;
	private String text;

	/**
	 * Creates a new TextWriter object, instantiated with the filepath and data
	 * to be written. Could be refactored.
	 * 
	 * @param filePath
	 *            filepath to be written to
	 * @param text
	 *            data to be written to file
	 */
	public TextWriter(String filePath, String text) {
		this.filePath = filePath;
		this.text = text;
	}

	/**
	 * Method for executing I/O operation.
	 * 
	 * @return 0 if failure, 1 if success
	 * 
	 * @see PrintWriter
	 * @see FileWriter
	 */
	public int write() {

		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new FileWriter(filePath));
			pw.print(text);
		} catch (IOException ioe) {
			return 0;
		}

		pw.flush();
		pw.close();

		return 1;
	}
}
