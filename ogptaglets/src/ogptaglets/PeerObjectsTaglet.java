package ogptaglets;

import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PeerObjectsTaglet extends MarkerTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.FIELD, Location.METHOD);
	}

	@Override
	public String getName() {
		return "peerObjects";
	}

	@Override
	public String getTitle() {
		return "Peer objects";
	}
	
	@Override
	public String getDefaultText(Element element) {
		if (element.getKind() == ElementKind.FIELD)
			return "The elements of the array or collection object referred to by this field, if any, are peer objects of this object.";
		else if (element.getKind() == ElementKind.METHOD)
			return "The elements of the array or collection object returned by this method, if any, are peer objects of this object.";
		else
			return "";
	}

}
