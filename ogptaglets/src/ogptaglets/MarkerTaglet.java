package ogptaglets;

import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;

import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.UnknownBlockTagTree;

import jdk.javadoc.doclet.Taglet;

public abstract class MarkerTaglet implements Taglet {

	@Override
	public boolean isInlineTag() {
		return false;
	}

	public abstract String getTitle();
	
	public abstract String getDefaultText(Element element);
	
	@Override
	public String toString(List<? extends DocTree> tags, Element element) {
		StringBuilder builder = new StringBuilder();
		StringBuilder contentBuilder = new StringBuilder();
		for (DocTree docTree : tags) {
			UnknownBlockTagTree tree = (UnknownBlockTagTree)docTree;
			for (DocTree child : tree.getContent()) {
				contentBuilder.append(child.toString());
				contentBuilder.append(' ');
			}
		}
		String content = contentBuilder.toString();
		if (content.equals(""))
			content = getDefaultText(element);
		builder.append("<dt><span class='throwsLabel'>" + getTitle() + "</span></dt><dd>" + content + "</dd>");
		return builder.toString();
	}
	
}
