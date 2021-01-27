package domainapp.modules.simple.dom.so;

import java.util.Comparator;

import javax.inject.Inject;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.persistence.jpa.applib.integration.JpaEntityInjectionPointResolver;

import static org.apache.isis.applib.annotation.SemanticsOf.IDEMPOTENT;
import static org.apache.isis.applib.annotation.SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.types.Name;
import domainapp.modules.simple.types.Notes;

@javax.persistence.Entity
@javax.persistence.Table(
    schema="simple",
    uniqueConstraints = {
        @javax.persistence.UniqueConstraint(name = "SimpleObject_name_UNQ", columnNames = {"name"})
    }
)
@javax.persistence.EntityListeners(JpaEntityInjectionPointResolver.class) // injection support
@DomainObject(objectType = "simple.SimpleObject")
@DomainObjectLayout()
@NoArgsConstructor
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class SimpleObject implements Comparable<SimpleObject> {

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(nullable = false)
    @Programmatic
    @Getter @Setter
    private Long id;

    @javax.persistence.Version
    @Programmatic
    @javax.persistence.Column(name = "OPTLOCK")
    private int version;

    public static SimpleObject withName(String name) {
        val simpleObject = new SimpleObject();
        simpleObject.setName(name);
        return simpleObject;
    }

    public static class ActionDomainEvent extends SimpleModule.ActionDomainEvent<SimpleObject> {}

    @Inject @javax.persistence.Transient RepositoryService repositoryService;
    @Inject @javax.persistence.Transient TitleService titleService;
    @Inject @javax.persistence.Transient MessageService messageService;


    public String title() {
        return "Object: " + getName();
    }

    @Name
    @javax.persistence.Column(length = Name.MAX_LEN, nullable = false)
    @Getter @Setter @ToString.Include
    private String name;

    @Notes
    @javax.persistence.Column(length = Notes.MAX_LEN, nullable = true)
    @Getter @Setter
    private String notes;


    public static class UpdateNameActionDomainEvent extends ActionDomainEvent {}
    @Action(semantics = IDEMPOTENT,
            commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED,
            associateWith = "name", domainEvent = UpdateNameActionDomainEvent.class)
    public SimpleObject updateName(
            @Name final String name) {
        setName(name);
        return this;
    }

    public String default0UpdateName() {
        return getName();
    }

    public static class DeleteActionDomainEvent extends ActionDomainEvent {}
    @Action(semantics = NON_IDEMPOTENT_ARE_YOU_SURE, domainEvent = DeleteActionDomainEvent.class)
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.removeAndFlush(this);
    }

    private final static Comparator<SimpleObject> comparator =
            Comparator.comparing(SimpleObject::getName);

    @Override
    public int compareTo(final SimpleObject other) {
        return comparator.compare(this, other);
    }

}
