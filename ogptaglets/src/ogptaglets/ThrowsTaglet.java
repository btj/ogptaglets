package ogptaglets;

import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.ReferenceTree;
import com.sun.source.doctree.ThrowsTree;
import com.sun.source.util.DocTreePath;
import com.sun.source.util.TreePath;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;

public class ThrowsTaglet extends OGPTaglet {
	
	private DocletEnvironment environment;
	
	@Override
	public void init(DocletEnvironment environment, Doclet doclet) {
		super.init(environment, doclet);
		this.environment = environment;
	}

	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.CONSTRUCTOR, Location.METHOD);
	}

	@Override
	public String getName() {
		return "throws";
	}

	@Override
	public String getTitle() {
		return "Throws";
	}
	
	private String referenceToString(Element referringElement, ReferenceTree rt) {
	fallback:
		{
			TreePath referringElementPath = environment.getDocTrees().getPath(referringElement);
			if (referringElementPath == null) break fallback;
			DocCommentTree commentTree = environment.getDocTrees().getDocCommentTree(referringElement);
			if (commentTree == null) break fallback;
			DocTreePath referencePath = DocTreePath.getPath(referringElementPath, commentTree, rt);
			Element referencedElement = environment.getDocTrees().getElement(referencePath);
			if (referencedElement.toString().startsWith("java."))
				return "<a href='https://docs.oracle.com/en/java/javase/13/docs/api/java.base/" + referencedElement.toString().replace('.', '/') + ".html'>" + rt.toString() + "</a>";
			else {
				// Assume the exception type is included in the JavaDoc currently being generated
				PackageElement currentPackage = environment.getElementUtils().getPackageOf(referringElement);
				String path = (currentPackage.isUnnamed() ? "" : currentPackage.toString().replace('.', '/').replaceAll("\\w+", "..") + "/") + referencedElement.toString().replace('.',  '/') + ".html";
				return "<a href='" + path + "'>" + rt.toString() + "</a>";
			}
		}
		return rt.toString();
	}

	@Override
	public String toString(List<? extends DocTree> tags, Element element) {
		StringBuilder builder = new StringBuilder();
		builder.append("<dt><span class='throwsLabel'>" + getTitle() + ":</span></dt>");
		for (DocTree docTree : tags) {
			builder.append("<dd>");
			ThrowsTree tree = (ThrowsTree)docTree;
			builder.append("<code>" + referenceToString(element, tree.getExceptionName()) + "</code> - ");
			builder.append(renderContent(tree.getDescription()));
			builder.append("</dd>");
		}
		return builder.toString();
	}

}
