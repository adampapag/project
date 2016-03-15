package cow.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Model implements IModel {

	private RequestHandler handler;
	private String[] args;
	private ArrayList<Result> resultsList = new ArrayList<Result>();
	private String result;
	private String[] library = { "square_free_leech", "square_free_thue1",
			"square_free", "thue_morse" };

	public void orderedPatternRequest(String pattern, String text) {
		resultsList.clear();
		args = new String[2];
		args[0] = pattern;
		args[1] = text;
		handler = new OrderedPatternRequestHandler();
		resultsList = handler.handle(args);
	}

	public void unorderedPatternRequest(String pattern, String text) {
		resultsList.clear();
		args = new String[2];
		args[0] = pattern;
		args[1] = text;
		handler = new UnorderedPatternRequestHandler();
		resultsList = handler.handle(args);
	}

	public void patternRequest(String pattern, String text, boolean ordered) {
		resultsList.clear();
		args = new String[2];
		args[0] = pattern;
		args[1] = text;
		if (ordered) {
			handler = new OrderedPatternRequestHandler();
		} else {
			assert !ordered;
			handler = new UnorderedPatternRequestHandler();
		}
		resultsList = handler.handle(args);
	}

	public void factorComplexityRequest(String text) {
		resultsList.clear();
		args = new String[1];
		args[0] = text;
		handler = new FactorComplexityRequestHandler();
		resultsList = handler.handle(args);
	}

	public void morphismRequest(String text, String[] morphismData,
			int iteration) {
		resultsList.clear();
		args = new String[morphismData.length + 2];
		for (int i = 0; i < morphismData.length; i++) {
			args[i] = morphismData[i];
		}
		args[args.length - 2] = text;
		args[args.length - 1] = String.valueOf(iteration);
		handler = new MorphismRequestHandler();
		resultsList = handler.handle(args);
	}

	public void crucialityRequest(ArrayList<String> patternList, String text,
			boolean ordered, ArrayList<String> alphabet) {
		resultsList.clear();
		// args = new String[alphabet.size() + 3];
		args = new String[patternList.size() + alphabet.size() + 2];
		int index = 0;
		args[0] = text;
		for (int i = 1; i <= alphabet.size(); i++) {
			args[i] = alphabet.get(i - 1);
			index = i;
		}
		index++;

		args[index] = String.valueOf(ordered);

		index++;
		for (int i = index; i < args.length; i++) {
			args[i] = patternList.get(i - index);
		}
		handler = new CrucialityRequestHandler();
		// for (int i = 0; i < args.length; i++) {
		// System.out.println(args[i] + ", ");
		// }
		resultsList = handler.handle(args);
		// args[0] = pattern;
		// args[1] = text;
		// args[2] = String.valueOf(ordered);
		// for (int i = 3; i < args.length; i++) {
		// args[i] = alphabet.get(i - 3);
		// }
		// handler = new CrucialityRequestHandler();
		// for (int i = 0; i < args.length; i++) {
		// System.out.println(args[i] + ", ");
		// }
		// resultsList = handler.handle(args);
	}

	public void saveRequest(String filepath) {
		resultsList.clear();
		args = new String[2];
		args[0] = filepath;
		args[1] = result;
		handler = new SaveRequestHandler();
		resultsList = handler.handle(args);
	}

	public void saveMorphismRequest(String filepath, String[] morphismData) {
		resultsList.clear();
		args = new String[morphismData.length + 1];
		args[0] = filepath;
		for (int i = 1; i < args.length; i++) {
			args[i] = morphismData[i - 1];
		}
		handler = new SaveMorphismRequestHandler();
		resultsList = handler.handle(args);
	}

	public void loadMorphismRequest(String filepath) {
		resultsList.clear();
		args = new String[1];
		args[0] = filepath;
		handler = new LoadMorphismRequestHandler();
		resultsList = handler.handle(args);
	}

	public void openFileRequest(String filepath) {
		resultsList.clear();
		args = new String[1];
		args[0] = filepath;
		handler = new OpenFileRequestHandler();
		resultsList = handler.handle(args);
	}

	public void exportRequest(String filepath) {
		// TODO Auto-generated method stub

	}

	public String trimText(String text) {
		return text.substring(1);
	}

	public ArrayList<Result> getResultsList() {
		return resultsList;
	}

	public void appendResultLine(String text) {
		result = result + text;
	}

	public void clearResult() {
		result = "";
	}

	public boolean isValidPattern(String pattern) {
		if (pattern.length() < 2) {
			return false;
		}
		return true;
	}

	public boolean isValidText(String text) {
		if (text.length() == 0) {
			return false;
		}
		return true;
	}

	public boolean isValid(String[] morphismData) {
		ArrayList<String> letters = new ArrayList<String>();
		ArrayList<String> morphisms = new ArrayList<String>();

		for (int i = 0; i < morphismData.length; i++) {
			if ((i % 2) == 0) {
				letters.add(morphismData[i]);
			} else {
				morphisms.add(morphismData[i]);
			}
		}

		int occurrences = 0;
		for (String l : letters) {
			if (l.equals("")) {
				System.out.println("Letter cannot be space.");
				return false;
			} else if (l.length() > 1) {
				System.out.println("Letter must be one character.");
				return false;
			}
			occurrences = 0;
			for (String l2 : letters) {
				if (l2.equals(l)) {
					occurrences++;
				}
				if (occurrences > 1) {
					System.out.println("Letter appears more than once.");
					return false;
				}
			}
		}

		String symbol = "";
		for (String m : morphisms) {
			for (char c : m.toCharArray()) {
				symbol = String.valueOf(c);
				if (!(letters.contains(symbol))) {
					System.out.println("Symbol '" + c + "' in morphism '" + m
							+ "' has no corresponding morphism.");
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public ArrayList<String> deduceAlphabet(String text) {
		char[] textArray = text.toCharArray();
		ArrayList<String> alphabet = new ArrayList<String>();
		for (char c : textArray) {
			String letter = String.valueOf(c);
			if (!alphabet.contains(letter)) {
				alphabet.add(letter);
			}
		}
		return alphabet;
	}

	public boolean libraryExists() {
		File file = new File(System.getProperty("user.home")
				+ System.getProperty("file.separator") + "Cow"
				+ System.getProperty("file.separator") + "Morphisms");
		if (!file.isDirectory()) {
			return false;
		}
		return true;
	}

	public void createLibrary() {
		String directory = System.getProperty("user.home")
				+ System.getProperty("file.separator") + "Cow"
				+ System.getProperty("file.separator") + "Morphisms";
		boolean okay = (new File(directory)).mkdirs();
		boolean okayOut = (new File(System.getProperty("user.home")
				+ System.getProperty("file.separator") + "Cow"
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
									+ "Cow"
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
