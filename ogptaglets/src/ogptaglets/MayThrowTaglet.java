package ogptaglets;

import java.util.Set;

public class MayThrowTaglet extends FormalPartTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.CONSTRUCTOR, Location.METHOD);
	}

	@Override
	public String getName() {
		return "may_throw";
	}

	@Override
	public String getTitle() {
		return "May throw";
	}

}
