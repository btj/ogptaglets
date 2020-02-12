package ogptaglets;

import java.util.Set;

public class PreconditionsTaglet extends OGPTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.CONSTRUCTOR, Location.METHOD);
	}

	@Override
	public String getName() {
		return "pre";
	}

	@Override
	public String getTitle() {
		return "Preconditions";
	}

}
