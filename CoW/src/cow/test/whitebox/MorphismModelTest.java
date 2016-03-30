package cow.test.whitebox;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;

import cow.model.MorphismModel;

public class MorphismModelTest {

	MorphismModel m;

	@Before
	public void setUp() {
		m = new MorphismModel();
	}

	@org.junit.Test
	public void testMorphismSuccessfulIntegers() {
		String[] morphismData = { "1", "2", "2", "" };
		ArrayList<String> invalid = m.validate(morphismData);

		assertTrue(invalid.isEmpty());
	}

	@org.junit.Test
	public void testMorphismSuccessfulSymbols() {
		String[] morphismData = { "1", "&", "&", "" };
		ArrayList<String> invalid = m.validate(morphismData);

		assertTrue(invalid.isEmpty());
	}

	@org.junit.Test
	public void testMorphismSpaceLetter() {

		String[] morphismData = { " ", "", "", "" };
		ArrayList<String> invalid = m.validate(morphismData);

		for (String s : invalid) {
			assertTrue(s.contains("Letter cannot be space." + "\n"));
		}

	}

	@org.junit.Test
	public void testMorphismDuplicate() {

		String[] morphismData = { "1", "", "1", "" };

		ArrayList<String> invalid = m.validate(morphismData);

		for (String s : invalid) {
			assertTrue(s.contains("letters should be unique."));
		}

	}

	@org.junit.Test
	public void testMorphismLetterTooLong() {

		String[] morphismData = { "21", "1" };
		ArrayList<String> invalid = m.validate(morphismData);

		assertTrue(invalid.contains("Letter must be one character." + "\n"));

	}

	@org.junit.Test
	public void testMorphismSymbolNoRule() {

		String[] morphismData = { "1", "2", "3", "" };

		ArrayList<String> invalid = m.validate(morphismData);

		for (String s : invalid) {
			assertTrue(s.contains("has no corresponding morphism"));
		}
	}
}
