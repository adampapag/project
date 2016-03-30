package cow.test.blackbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;

import cow.controller.PatternController;
import cow.data.Result;
import cow.model.PatternModel;
import cow.view.PatternView;

public class PatternTests {
	PatternModel m;
	PatternView v;
	PatternController c;

	@Before
	public void setUp() {
		m = new PatternModel();
		v = new PatternView();
		c = new PatternController(m, v);
		c.show();
	}

	@org.junit.Test
	public void unorderedPatternTextTest() {
		String pattern = "xx";
		String text = "1234551234";
		String result = "55";
		ArrayList<Result> results = new ArrayList<Result>();

		while (text.length() > 1) {
			m.patternRequest(pattern, text, false);
			results.addAll(m.getResultsList());
			assertTrue(results.size() <= 1);
			text = text.substring(1);
		}

		assertEquals(results.get(0).getString(), result);

		v.closeWindow();
	}

	@org.junit.Test
	public void orderedPatternTextTest() {
		String pattern = "123";
		String text = "122123122";
		String result = "123";

		ArrayList<Result> results = new ArrayList<Result>();

		while (text.length() > 1) {
			m.patternRequest(pattern, text, true);
			results.addAll(m.getResultsList());
			assertTrue(results.size() <= 1);
			text = text.substring(1);
		}

		assertEquals(results.get(0).getString(), result);

		v.closeWindow();
	}

	@org.junit.Test
	public void patternSaveTest() {
		String pattern = "xx";
		String text = "1234551234";

		v.setText();
		v.getPatternField().setText(pattern);
		v.getTextField().setText(text);
		c.showResults();

		String filepath = c.saveResults();

		if (!filepath.endsWith(".txt")) {
			filepath = filepath + ".txt";
		}

		File f = new File(filepath);

		assertTrue(f.exists());

		v.closeWindow();

	}

	@org.junit.Test
	public void patternOpenFileTest() {
		String file = c.chooseFile();

		String result = v.getFilepathLabel().getText();

		assertEquals(result, file);

		v.closeWindow();
	}
}
