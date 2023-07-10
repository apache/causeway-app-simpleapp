package domainapp.modules.simple.dom.tree;

import java.util.Optional;

public interface BookNode {

	Optional<BookNode> getParent();
	
	Optional<BookNode> getChild();
	
}
