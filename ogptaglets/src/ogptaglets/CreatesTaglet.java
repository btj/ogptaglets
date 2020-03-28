package ogptaglets;

import java.util.Set;

public class CreatesTaglet extends FormalPartTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.CONSTRUCTOR, Location.METHOD);
	}

	@Override
	public String getName() {
		return "creates";
	}

	@Override
	public String getTitle() {
		return "Creates";
	}

}
