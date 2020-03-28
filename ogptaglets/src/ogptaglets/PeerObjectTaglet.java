package ogptaglets;

import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PeerObjectTaglet extends MarkerTaglet {

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.FIELD, Location.METHOD);
	}

	@Override
	public String getName() {
		return "peerObject";
	}

	@Override
	public String getTitle() {
		return "Peer object";
	}
	
	@Override
	public String getDefaultText(Element element) {
		if (element.getKind() == ElementKind.FIELD)
			return "The object referred to by this field, if any, is a peer object of this object.";
		else if (element.getKind() == ElementKind.METHOD)
			return "The object returned by this method, if any, is a peer object of this object.";
		else
			return "";
	}

}
