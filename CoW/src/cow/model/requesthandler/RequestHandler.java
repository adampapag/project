package cow.model.requesthandler;

import java.util.ArrayList;

import cow.data.Result;

/**
 * The interface which all classes that execute CoW-related code should inherit.
 * Offers the default handling method, other implementation is down to the
 * coders discretion.
 * 
 * @author Adam Papageorgiou
 *
 */
public interface RequestHandler {

	public ArrayList<Result> handle(String[] args);

}
