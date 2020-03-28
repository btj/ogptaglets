package ogptaglets;

import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class ImmutableTaglet extends MarkerTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.TYPE, Location.METHOD);
	}

	@Override
	public String getName() {
		return "immutable";
	}

	@Override
	public String getTitle() {
		return "Immutable";
	}
	
	@Override
	public String getDefaultText(Element element) {
		if (element.getKind() == ElementKind.METHOD)
			return "Any two calls of this method on the same target object return equal values.";
		else if (element.getKind() == ElementKind.CLASS)
			return "Any two calls of any zero-argument getter of this class on the same target object return equal values." +
				" (A getter is a method whose name starts with \"get\" or \"is\" followed by a capital letter.)";
		else if (element.getKind() == ElementKind.INTERFACE)
			return "Any two calls of any zero-argument getter of this interface on the same target object return equal values." +
				" (A getter is a method whose name starts with \"get\" or \"is\" followed by a capital letter.)";
		else
			return "";
	}

}
