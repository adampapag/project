package cow.test.whitebox;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;

import cow.data.Result;
import cow.model.CrucialityModel;
import cow.model.requesthandler.CrucialityRequestHandler;
import cow.model.requesthandler.RequestHandler;

public class CrucialityRequestHandlerTest {
	CrucialityModel m;
	RequestHandler h;

	@Before
	public void setUp() {
		m = new CrucialityModel();
		h = new CrucialityRequestHandler();
	}

	@org.junit.Test
	public void testNotCrucial() {
		String text = "12";
		String alphabet1 = "1";
		String alphabet2 = "2";
		String ordered = "false";
		String pattern = "xx";
		String[] args = new String[5];
		args[0] = text;
		args[1] = alphabet1;
		args[2] = alphabet2;
		args[3] = ordered;
		args[4] = pattern;

		ArrayList<Result> results = h.handle(args);

		for (Result r : results) {
			System.out.println(r.getPrefix());
			assertTrue(r.getPrefix().contains("neither"));
		}

	}

	@org.junit.Test
	public void testAlreadyContainsPattern() {

		String text = "11";
		String alphabet = "1";
		String ordered = "false";
		String pattern = "xx";
		String[] args = new String[4];
		args[0] = text;
		args[1] = alphabet;
		args[2] = ordered;
		args[3] = pattern;

		ArrayList<Result> results = h.handle(args);

		for (Result r : results) {
			assertTrue(r.getPrefix().contains("word already contains pattern"));
		}

	}

	@org.junit.Test
	public void testBiCrucial() {
		String text = "121";
		String alphabet1 = "1";
		String alphabet2 = "2";
		String ordered = "false";
		String pattern = "xx";
		String[] args = new String[5];
		args[0] = text;
		args[1] = alphabet1;
		args[2] = alphabet2;
		args[3] = ordered;
		args[4] = pattern;

		ArrayList<Result> results = h.handle(args);

		for (Result r : results) {
			assertTrue(r.getPrefix().contains("bi-crucial word"));
		}

	}

}
