package cow.test.blackbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;

import cow.controller.FactorComplexityController;
import cow.data.Result;
import cow.model.FactorComplexityModel;
import cow.view.FactorComplexityView;

public class FactorComplexityTests {

	FactorComplexityModel m;
	FactorComplexityView v;
	FactorComplexityController c;

	@Before
	public void setUp() {
		m = new FactorComplexityModel();
		v = new FactorComplexityView();
		c = new FactorComplexityController(m, v);
		c.show();
	}

	@org.junit.Test
	public void factorComplexityTest() {
		String text = "fac\n com.1";
		String[] resultData = { "f", "a", "c", " ", "o", "m", ".", "1", "fa",
				"c ", "co", "m.", "ac", " c", "om", ".1", "fac", " co", "m.1",
				"ac ", "com", "c c", "om.", "fac ", "com.", "ac c", "om.1",
				"c co", " com", "fac c", "ac co", "c com", " com.", "com.1",
				"fac co", "ac com", "c com.", " com.1", "fac com", "ac com.",
				"c com.1", "fac com.", "ac com.1", "fac com.1" };

		m.factorComplexityRequest(text);
		ArrayList<Result> resultList = m.getResultsList();
		ArrayList<String> results = new ArrayList<String>();

		for (Result r : resultList) {
			results.add(r.getString());
		}

		for (int i = 0; i < resultData.length; i++) {
			assertTrue(results.contains(resultData[i]));
		}

		v.closeWindow();

	}

	@org.junit.Test
	public void factorComplexitySaveTest() {
		String text = "fac\n com.1";

		v.getTextField().setText(text);
		c.showResults();

		String filepath = c.saveResults();

		if (!filepath.endsWith(".txt")) {
			filepath = filepath + ".txt";
		}

		File f = new File(filepath);

		assertTrue(f.exists());

	}

	@org.junit.Test
	public void factorComplexityOpenFileTest() {
		String file = c.chooseFile();

		String result = v.getFilepathLabel().getText();

		assertEquals(result, file);

		v.closeWindow();
	}
}
