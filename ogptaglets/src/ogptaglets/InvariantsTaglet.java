package ogptaglets;

import java.util.Set;

public class InvariantsTaglet extends FormalPartTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.FIELD, Location.TYPE);
	}

	@Override
	public String getName() {
		return "invar";
	}

	@Override
	public String getTitle() {
		return "Invariants";
	}

}
