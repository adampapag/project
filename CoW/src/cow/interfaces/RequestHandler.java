package cow.interfaces;

import java.util.ArrayList;

import cow.data.Result;

public interface RequestHandler {

	public ArrayList<Result> handle(String[] args);

}
