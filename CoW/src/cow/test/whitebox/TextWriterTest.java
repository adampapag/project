package cow.test.whitebox;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;

import cow.data.Result;
import cow.io.TextWriter;
import cow.model.CrucialityModel;
import cow.model.requesthandler.CrucialityRequestHandler;

public class TextWriterTest {
	TextWriter w;

	@Before
	public void setUp() {
	}

	@org.junit.Test
	public void testSuccess() {
		String filepath = System.getProperty("user.home")
				+ System.getProperty("file.separator") + "CoW"
				+ System.getProperty("file.separator") + "Results"
				+ System.getProperty("file.separator") + "file.txt";
		String text = "";
		w = new TextWriter(filepath, text);

		assertTrue(w.write() == 1);

	}

	@org.junit.Test
	public void testFailure() {

		String filepath = "";
		String text = "";
		w = new TextWriter(filepath, text);

		assertTrue(w.write() == 0);

	}

}
