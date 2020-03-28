package ogptaglets;

import java.util.List;

import javax.lang.model.element.Element;

import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.LiteralTree;
import com.sun.source.doctree.UnknownBlockTagTree;

import jdk.javadoc.doclet.Taglet;

public abstract class FormalPartTaglet implements Taglet {

	@Override
	public boolean isInlineTag() {
		return false;
	}
	
	public abstract String getTitle();
	
	public static String renderContent(List<? extends DocTree> contentTrees) {
		return renderContent(contentTrees, "<pre style='padding: 16px; background: #f8f8f8'>", "</pre>");
	}
	
	private static String literalSpanStart = String.valueOf((char)0xfffe);
	private static String literalSpanEnd = String.valueOf((char)0xffff);
	
	public static String renderContent(List<? extends DocTree> contentTrees, String prolog, String epilog) {
		StringBuilder contentBuilder = new StringBuilder();
		for (DocTree child : contentTrees)
			if (child instanceof LiteralTree) {
				// Horrible hack
				contentBuilder.append(literalSpanStart);
				contentBuilder.append(((LiteralTree)child).getBody().toString());
				contentBuilder.append(literalSpanEnd);
			} else
				contentBuilder.append(child.toString());
		String content = contentBuilder.toString();
		int bar = content.indexOf('|');
		String informalPart;
		String formalPart;
		if (0 <= bar) {
			informalPart = content.substring(0, bar); 
			int lineStart = bar + 1;
			StringBuilder formalPartBuilder = new StringBuilder();
			for (;;) {
				if (lineStart < content.length() && content.charAt(lineStart) == ' ')
					lineStart++;
				int linefeed = content.indexOf('\n', lineStart);
				if (linefeed < 0) {
					formalPartBuilder.append(content, lineStart, content.length());
					break;
				}
				formalPartBuilder.append(content, lineStart, linefeed + 1);
				lineStart = linefeed + 1;
			eatWhitespace:
				while (lineStart < content.length()) {
					switch (content.charAt(lineStart)) {
					case ' ':
					case '\t':
					case '\r':
					case '\n':
						lineStart++;
						break;
					case '|':
						lineStart++;
						break eatWhitespace;
					default:
						break eatWhitespace;
					}
				}
			}
			formalPart = formalPartBuilder.toString();
		} else {
			informalPart = content;
			formalPart = null;
		}
		String result = informalPart.replace("&", "&amp;").replace("<", "&lt;")
				.replace(literalSpanStart, "<code>").replace(literalSpanEnd, "</code>");
		if (formalPart != null)
			result += prolog + formalPart.replace("&",  "&amp;").replace("<",  "&lt;") + epilog;
		return result;
	}
	
	@Override
	public String toString(List<? extends DocTree> tags, Element element) {
		StringBuilder builder = new StringBuilder();
		builder.append("<dt><span class='throwsLabel'>" + getTitle() + ":</span></dt>");
		for (DocTree docTree : tags) {
			builder.append("<dd>");
			UnknownBlockTagTree tree = (UnknownBlockTagTree)docTree;
			builder.append(renderContent(tree.getContent()));
			builder.append("</dd>");
		}
		return builder.toString();
	}

}
