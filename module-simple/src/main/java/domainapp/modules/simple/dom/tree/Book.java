package domainapp.modules.simple.dom.tree;

import java.util.Optional;

import javax.inject.Named;
import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.causeway.applib.annotation.BookmarkPolicy;
import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.Programmatic;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.PropertyLayout;
import org.apache.causeway.applib.annotation.TableDecorator;
import org.apache.causeway.applib.annotation.Title;
import org.apache.causeway.applib.graph.tree.TreeNode;
import org.apache.causeway.applib.graph.tree.TreePath;
import org.apache.causeway.applib.jaxb.PersistentEntityAdapter;

import domainapp.modules.simple.SimpleModule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@PersistenceCapable(
    schema = SimpleModule.SCHEMA,
    identityType=IdentityType.DATASTORE)
@DatastoreIdentity(strategy=IdGeneratorStrategy.IDENTITY, column="id")
@Version(strategy= VersionStrategy.DATE_TIME, column="version")
@DomainObjectLayout(
    tableDecorator = TableDecorator.DatatablesNet.class,
    bookmarking = BookmarkPolicy.AS_ROOT)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
@Named(SimpleModule.NAMESPACE + ".Book")
@DomainObject(nature = Nature.ENTITY)
public class Book implements BookNode {
	
	@Property()
	@Title
	@Getter @Setter
	private String name;

	@Property()
	@Getter @Setter
	private Author author;

	@Programmatic
	@Override
	public Optional<BookNode> getParent() {
		return Optional.of(new BookView(this));
	}

	@Programmatic
	@Override
	public Optional<BookNode> getChild() {
		return Optional.empty();
	}
	
	@Property
	public BookView getBookView() {
		return new BookView(this);
	}

}
