package cow.test.whitebox;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;

import cow.model.CoWLabModel;

public class CoWLabModelTest {

	CoWLabModel m;
	String directory = System.getProperty("user.home")
			+ System.getProperty("file.separator") + "CoW"
			+ System.getProperty("file.separator") + "Morphisms";

	@Before
	public void setUp() {
		m = new CoWLabModel();
	}

	@org.junit.Test
	public void testLibraryExists() {

		File library = new File(directory);

		boolean libraryExists = m.libraryExists();
		if (libraryExists) {
			assertTrue(library.exists());
		} else if (!libraryExists) {
			assertTrue(!library.exists());
		}
	}

	@org.junit.Test
	public void testCreateLibrary() {
		boolean success = m.createLibrary();
		File f = new File(directory);
		if (success) {
			assertTrue(f.exists());
		}
		if (!success) {
			assertTrue(f.exists());
		}

	}
}
