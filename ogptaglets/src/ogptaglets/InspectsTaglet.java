package ogptaglets;

import java.util.Set;

public class InspectsTaglet extends FormalPartTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.CONSTRUCTOR, Location.METHOD);
	}

	@Override
	public String getName() {
		return "inspects";
	}

	@Override
	public String getTitle() {
		return "Inspects";
	}

}
