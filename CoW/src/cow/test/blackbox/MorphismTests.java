package cow.test.blackbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.swing.JTable;

import org.junit.Before;

import cow.controller.MorphismController;
import cow.model.MorphismModel;
import cow.view.MorphismView;

public class MorphismTests {

	MorphismModel m;
	MorphismView v;
	MorphismController c;

	@Before
	public void setUp() {
		m = new MorphismModel();
		v = new MorphismView();
		c = new MorphismController(m, v);
		c.show();

	}

	@org.junit.Test
	public void morphismTest() {
		String[] morphismData = { "a", "abc", "b", "ac", "c", "b" };
		String[] resultData = { "a", "abc", "abcacb", "abcacbabcbac",
				"abcacbabcbacabcacbacabcb",
				"abcacbabcbacabcacbacabcbabcacbabcbacabcbabcacbac" };
		int fromLength = 0;
		int toLength = 0;

		String text = morphismData[0];
		while (fromLength <= toLength) {
			assertEquals(text, resultData[fromLength]);
			m.morphismRequest(text, morphismData, fromLength);
			text = m.getResultsList().get(0).getString();
			fromLength++;
		}

		v.closeWindow();

	}

	@org.junit.Test
	public void morphismSaveMorphismTest() {
		String[] morphismData = { "a", "abc", "b", "ac", "c", "b" };

		v.setTable(morphismData);
		String filepath = c.saveMorphism();

		if (!filepath.endsWith(".txt")) {
			filepath = filepath + ".txt";
		}

		File f = new File(filepath);

		assertTrue(f.exists());

		v.closeWindow();
	}

	@org.junit.Test
	public void morphismLoadMorphismTest() {
		c.loadMorphism();

		JTable morphismTable = v.getMorphismTable();
		String[] morphismData = new String[morphismTable.getRowCount()
				* morphismTable.getColumnCount()];

		int index = 0;
		for (int row = 0; row < morphismTable.getRowCount(); row++) {
			for (int column = 0; column < morphismTable.getColumnCount(); column++) {
				morphismData[index] = (String) morphismTable.getValueAt(row,
						column);
				index++;
			}
		}

		assertTrue(!(morphismData.length == 0));

		v.closeWindow();
	}

	@org.junit.Test
	public void morphismSaveTest() {
		String[] morphismData = { "a", "abc", "b", "ac", "c", "b" };

		v.getFromIterationField().setText("0");
		v.getToIterationField().setText("5");
		v.setTable(morphismData);
		c.showResults();

		String filepath = c.saveResults();

		if (!filepath.endsWith(".txt")) {
			filepath = filepath + ".txt";
		}

		File f = new File(filepath);

		assertTrue(f.exists());

		v.closeWindow();

	}
}
