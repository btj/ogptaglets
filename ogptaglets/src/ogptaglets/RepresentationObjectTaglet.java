package ogptaglets;

import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class RepresentationObjectTaglet extends MarkerTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.FIELD, Location.METHOD);
	}

	@Override
	public String getName() {
		return "representationObject";
	}

	@Override
	public String getTitle() {
		return "Representation object";
	}
	
	@Override
	public String getDefaultText(Element element) {
		if (element.getKind() == ElementKind.FIELD)
			return "The object referred to by this field, if any, is a representation object of this object.";
		else if (element.getKind() == ElementKind.METHOD)
			return "The object returned by this method, if any, is a representation object of this object.";
		else
			return "";
	}

}
