package cow.test.blackbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;

import cow.controller.CrucialityController;
import cow.data.Result;
import cow.model.CrucialityModel;
import cow.view.CrucialityView;

public class CrucialityTests {
	CrucialityModel m;
	CrucialityView v;
	CrucialityController c;

	@Before
	public void setUp() {
		m = new CrucialityModel();
		v = new CrucialityView();
		c = new CrucialityController(m, v);
		c.show();
	}

	@org.junit.Test
	public void unorderedCrucialityTextTest() {
		ArrayList<String> patternData = new ArrayList<String>();
		patternData.add("xx");
		patternData.add("xxy");
		ArrayList<String> alphabet = new ArrayList<String>();
		alphabet.add("1");
		alphabet.add("2");
		alphabet.add("3");
		alphabet.add("4");
		String text = "121312141213121";
		String result = "bi-crucial";

		m.crucialityRequest(patternData, text, false, alphabet);
		ArrayList<Result> resultsList = m.getResultsList();

		assertEquals(resultsList.get(0).getPrefix().contains(result), true);

		v.closeWindow();
	}

	@org.junit.Test
	public void orderedCrucialityTextTest() {
		ArrayList<String> patternData = new ArrayList<String>();
		patternData.add("21");
		patternData.add("22");
		ArrayList<String> alphabet = new ArrayList<String>();
		alphabet.add("1");
		alphabet.add("2");
		String text = "2";
		String result = "crucial";

		m.crucialityRequest(patternData, text, true, alphabet);
		ArrayList<Result> resultsList = m.getResultsList();

		assertEquals(resultsList.get(0).getPrefix().contains(result), true);

		v.closeWindow();
	}

	@org.junit.Test
	public void crucialitySaveTest() {
		String[] patternData = { "xx" };
		String text = "121312141213121";

		v.setText();
		v.setTable(patternData);
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
	public void crucialityOpenFileTest() {
		String file = c.chooseFile();

		String result = v.getFilepathLabel().getText();

		assertEquals(result, file);

		v.closeWindow();
	}
}
