package domainapp.modules.simple.dom.so.co;

import static org.apache.isis.applib.annotation.SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE;

import java.util.Comparator;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Navigable;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.types.Name;
import domainapp.modules.simple.types.Notes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(strategy=IdGeneratorStrategy.IDENTITY, column="id")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@javax.jdo.annotations.Unique(name="ChildObject_parent_name_UNQ", members = {"parent", "name"})
@DomainObject()
@DomainObjectLayout(cssClassFa = "fa-star")
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class ChildObject implements Comparable<ChildObject> {

    @Inject RepositoryService repositoryService;
    @Inject TitleService titleService;
    @Inject MessageService messageService;

	@Property(editing = Editing.DISABLED)
	@PropertyLayout(navigable = Navigable.PARENT, hidden = Where.REFERENCES_PARENT)
	@Column(allowsNull = "false")
	@Getter @Setter
	private SimpleObject parent;
	
	@Title
	@Name
	@Property(editing = Editing.ENABLED)	
	@Getter @Setter
	private String name;

    @Notes
    @Getter @Setter
    private String notes;
    
    @Action(semantics = NON_IDEMPOTENT_ARE_YOU_SURE)
    public SimpleObject delete() {
    	SimpleObject parent = getParent();
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("Child '%s' deleted", title));
        repositoryService.removeAndFlush(this);
        return parent;
    }
    
    private final static Comparator<ChildObject> comparator =
            Comparator.comparing(ChildObject::getName);

    @Override
    public int compareTo(final ChildObject other) {
        return comparator.compare(this, other);
    }
    
}
