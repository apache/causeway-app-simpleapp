package domainapp.modules.simple.dom.tree;

import java.util.Optional;

import javax.inject.Named;

import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.Programmatic;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.PropertyLayout;
import org.apache.causeway.applib.annotation.Title;
import org.apache.causeway.applib.graph.tree.TreeNode;
import org.apache.causeway.applib.graph.tree.TreePath;

import domainapp.modules.simple.SimpleModule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Named(SimpleModule.NAMESPACE + ".BookView")
@DomainObject(nature = Nature.VIEW_MODEL)
@NoArgsConstructor
@AllArgsConstructor
public class BookView implements BookNode {

	@Title
	public String title() {
		return String.format("Book %s written by %s", getBook().getName(), getBook().getAuthor().getName());
	}
	
	@Property
	@Getter @Setter
	private Book book;

	@Programmatic
	@Override
	public Optional<BookNode> getParent() {
		return Optional.empty();
	}

	@Programmatic
	@Override
	public Optional<BookNode> getChild() {
		return Optional.of(getBook());
	}

	@Property()
	@PropertyLayout(named = "Book View")
	public TreeNode<BookNode> getTree() {
		TreeNode<BookNode> tree = TreeNode.lazy(this, BookNodeTreeAdapter.class);
		tree.expand(TreePath.of(0));
		return tree;
	}	
		
}
