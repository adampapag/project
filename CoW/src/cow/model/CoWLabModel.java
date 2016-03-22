package cow.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CoWLabModel implements Model {

	private String[] library = { "square_free_leech", "square_free_thue1",
			"square_free", "thue_morse" };

	public boolean libraryExists() {
		File file = new File(System.getProperty("user.home")
				+ System.getProperty("file.separator") + "CoW"
				+ System.getProperty("file.separator") + "Morphisms");
		if (!file.isDirectory()) {
			return false;
		}
		return true;
	}

	public void createLibrary() {
		String directory = System.getProperty("user.home")
				+ System.getProperty("file.separator") + "CoW"
				+ System.getProperty("file.separator") + "Morphisms";
		boolean okay = (new File(directory)).mkdirs();
		boolean okayOut = (new File(System.getProperty("user.home")
				+ System.getProperty("file.separator") + "CoW"
				+ System.getProperty("file.separator") + "Results")).mkdirs();
		if (!(okay || okayOut)) {
			System.out.println("Failed to create directory: " + directory);
		} else {
			assert okay;
			String file = null;
			try {
				InputStream in = null;
				OutputStream out = null;
				for (String f : library) {
					file = f;
					in = Model.class.getResourceAsStream(System
							.getProperty("file.separator") + f + ".txt");
					out = new FileOutputStream(new File(
							System.getProperty("user.home")
									+ System.getProperty("file.separator")
									+ "CoW"
									+ System.getProperty("file.separator")
									+ "Morphisms"
									+ System.getProperty("file.separator") + f
									+ ".txt"));
					int readBytes;
					byte[] buffer = new byte[4096];
					while ((readBytes = in.read(buffer)) > 0) {
						out.write(buffer, 0, readBytes);
					}
				}
				in.close();
				out.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found: " + file);
			} catch (IOException e) {
				System.out.println("Error reading file: " + file);
			} catch (NullPointerException npe) {
				System.out.println("Nothing to read from: " + file);
				npe.printStackTrace();
			}
		}

	}
}
