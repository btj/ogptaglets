package ogptaglets;

import java.util.Set;

import javax.lang.model.element.Element;

public class BasicTaglet extends MarkerTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.METHOD);
	}

	@Override
	public String getName() {
		return "basic";
	}

	@Override
	public String getTitle() {
		return "Basic inspector";
	}
	
	@Override
	public String getDefaultText(Element element) {
		return "If a method uses a <code>@mutates_properties</code> clause to specify its effect, " +
				"and some object O of this class is in the peer group of some object mentioned in the clause, " +
				"and O.M() is not mentioned in the clause, where M is this method, then O.M remains unchanged.";
	}

}
