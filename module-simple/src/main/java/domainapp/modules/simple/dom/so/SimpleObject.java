package domainapp.modules.simple.dom.so;

import java.util.Comparator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.lang.Nullable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberSupport;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.applib.layout.LayoutConstants;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.applib.value.Blob;
import org.apache.isis.extensions.pdfjs.applib.annotations.PdfJsViewer;

import static org.apache.isis.applib.annotation.SemanticsOf.IDEMPOTENT;
import static org.apache.isis.applib.annotation.SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.types.Name;
import domainapp.modules.simple.types.Notes;


@PersistenceCapable(
    schema = SimpleModule.SCHEMA,
    identityType=IdentityType.DATASTORE)
@Unique(
        name = "SimpleObject__name__UNQ", members = { "name" }
)
@Queries({
        @Query(
                name = SimpleObject.NAMED_QUERY__FIND_BY_NAME_LIKE,
                value = "SELECT " +
                        "FROM domainapp.modules.simple.dom.so.SimpleObject " +
                        "WHERE name.indexOf(:name) >= 0"
        ),
        @Query(
                name = SimpleObject.NAMED_QUERY__FIND_BY_NAME_EXACT,
                value = "SELECT " +
                        "FROM domainapp.modules.simple.dom.so.SimpleObject " +
                        "WHERE name == :name"
        )
})
@DatastoreIdentity(strategy=IdGeneratorStrategy.IDENTITY, column="id")
@Version(strategy= VersionStrategy.DATE_TIME, column="version")
@Named(SimpleModule.NAMESPACE + ".SimpleObject")
@DomainObject(entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class SimpleObject implements Comparable<SimpleObject> {

    static final String NAMED_QUERY__FIND_BY_NAME_LIKE = "SimpleObject.findByNameLike";
    static final String NAMED_QUERY__FIND_BY_NAME_EXACT = "SimpleObject.findByNameExact";

    public static SimpleObject withName(String name) {
        val simpleObject = new SimpleObject();
        simpleObject.setName(name);
        return simpleObject;
    }

    @Inject RepositoryService repositoryService;
    @Inject TitleService titleService;
    @Inject MessageService messageService;



    @Title
    @Name
    @Getter @Setter @ToString.Include
    @PropertyLayout(fieldSetId = LayoutConstants.FieldSetId.IDENTITY, sequence = "1")
    private String name;

    @Notes
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = LayoutConstants.FieldSetId.DETAILS, sequence = "2")
    private String notes;


    @PdfJsViewer
    @Getter @Setter
    @Persistent(defaultFetchGroup="false", columns = {
            @Column(name = "attachment_name"),
            @Column(name = "attachment_mimetype"),
            @Column(name = "attachment_bytes")
    })
    @Property()
    @PropertyLayout(fieldSetId = "content", sequence = "1")
    private Blob attachment;


    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @ActionLayout(
            associateWith = "name", promptStyle = PromptStyle.INLINE,
            describedAs = "Updates the name of this object, certain characters (" + PROHIBITED_CHARACTERS + ") are not allowed.")
    public SimpleObject updateName(
            @Name final String name) {
        setName(name);
        return this;
    }
    @MemberSupport public String default0UpdateName() {
        return getName();
    }
    @MemberSupport public String validate0UpdateName(String newName) {
        for (char prohibitedCharacter : PROHIBITED_CHARACTERS.toCharArray()) {
            if( newName.contains(""+prohibitedCharacter)) {
                return "Character '" + prohibitedCharacter + "' is not allowed.";
            }
        }
        return null;
    }
    static final String PROHIBITED_CHARACTERS = "&%$!";



    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @ActionLayout(associateWith = "attachment", position = ActionLayout.Position.PANEL)
    public SimpleObject updateAttachment(
            @Nullable final Blob attachment) {
        setAttachment(attachment);
        return this;
    }
    @MemberSupport public Blob default0UpdateAttachment() {
        return getAttachment();
    }



    @Action(semantics = NON_IDEMPOTENT_ARE_YOU_SURE)
    @ActionLayout(
            fieldSetId = LayoutConstants.FieldSetId.IDENTITY,
            position = ActionLayout.Position.PANEL,
            describedAs = "Deletes this object from the persistent datastore")
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
