package domainapp.modules.simple.dom.tree;

import java.util.Optional;
import java.util.stream.Stream;

import org.apache.causeway.applib.graph.tree.TreeAdapter;

public class BookNodeTreeAdapter implements TreeAdapter<BookNode> {

	@Override
	public Optional<BookNode> parentOf(BookNode value) {
		return value.getParent();
	}

	@Override
	public int childCountOf(BookNode value) {
		return value.getChild().map(__ -> 1).orElse(0);
	}

	@Override
	public Stream<BookNode> childrenOf(BookNode value) {
		return value.getChild().stream();
	}

}
