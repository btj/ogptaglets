package ogptaglets;

import java.util.Set;

public class PostconditionsTaglet extends FormalPartTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.CONSTRUCTOR, Location.METHOD);
	}

	@Override
	public String getName() {
		return "post";
	}

	@Override
	public String getTitle() {
		return "Postconditions";
	}

}
