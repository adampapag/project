package cow.model;

import java.util.ArrayList;

public interface RequestHandler {

	public ArrayList<Result> handle(String[] args);

}
