package ogptaglets;

import java.util.Set;

public class MutatesPropertiesTaglet extends FormalPartTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.CONSTRUCTOR, Location.METHOD);
	}

	@Override
	public String getName() {
		return "mutates_properties";
	}

	@Override
	public String getTitle() {
		return "Mutates properties";
	}

}
