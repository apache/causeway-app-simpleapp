package domainapp.modules.simple.dom.so;

import java.util.Comparator;
import java.util.SortedSet;

import javax.inject.Inject;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;

import static org.apache.isis.applib.annotation.SemanticsOf.IDEMPOTENT;
import static org.apache.isis.applib.annotation.SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE;

import domainapp.modules.simple.dom.so.co.ChildObject;
import domainapp.modules.simple.types.Name;
import domainapp.modules.simple.types.Notes;

import lombok.Getter;
import lombok.Setter;
import lombok.val;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(strategy=IdGeneratorStrategy.IDENTITY, column="id")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@javax.jdo.annotations.Unique(name="SimpleObject_name_UNQ", members = {"name"})
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "findByName",
			value = "SELECT FROM domainapp.modules.simple.dom.so.SimpleObject WHERE "
					+ " name.indexOf(:name) != -1 "
			)
	,
	@javax.jdo.annotations.Query(name = "findByNameExact",
	value = "SELECT FROM domainapp.modules.simple.dom.so.SimpleObject WHERE "
			+ " name.equals(:name) "
	)
})
@DomainObject()
@DomainObjectLayout()
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
public class SimpleObject implements Comparable<SimpleObject> {

    public static SimpleObject withName(String name) {
        val simpleObject = new SimpleObject();
        simpleObject.setName(name);
        return simpleObject;
    }

	/*
	 * These dependencies are automatically injected 
	 * into an entity when persisting it or retrieving 
	 * it from the persistence layer.
	 */
    @Inject RepositoryService repositoryService;
    @Inject TitleService titleService;
    @Inject MessageService messageService;    
    
    private SimpleObject() {
    }

    /*
     * This method is part of the programming model of Apache Isis.
     * The string returned by this method is shown as title on the 
     * default page displaying this entity.
     * 
     * The location of the entity title on the page can be changed
     * by moving the "domainObject" element in the layout.xml file
     * to another location in the grid.
     */
    public String title() {
        return "Object: " + getName();
    }
    
    /****
     * Name
     ****/

    /*
     * This @Name annotation defines certain reusable meta-properties 
     * for the "name" field below. Look at the @Name type to see 
     * what the inherited restrictions are!
     */
    @Name
    /*
     * The getter and setter are generated with Lombok.
     * Each getter will turn into a field displayed in the UI.
     */
    @Getter @Setter 
    private String name;

    /*
     * This action updates the name. Instead of updating the name through 
     * an action one could have also just used the @Property annotation
     * on the name and set editing to "ENABLED".
     * 
     * Try it out!
     */
    @Action(semantics = IDEMPOTENT, associateWith = "name")
    public SimpleObject updateName(@Name final String name) {
        setName(name);
        return this;
    }

    /*
     * This method is also part of the programming model and belongs
     * to the "updateName" method above. It will provide a default 
     * value for the parameter at location "0" for the method.
     */
    public String default0UpdateName() {
        return getName();
    }
    
    /*
     * Notes
     */
    
    @Notes
    @Getter @Setter
    private String notes;
    
    /*
     * Children
     */
    
    /*
     * This is an example of a 1-N mapping with JDO.
     * Each SimpleObject "owns" a collection of ChildObjects.
     * 
     * A collection of an entity will be displayed as table in the UI
     * if being accessible via a public getter method.
     * 
     * The used collection type is SortedSet and the elements of the set
     * will be sorted according to the implementation of the comparator
     * used in the ChildObject type.
     * 
     * The UI will also display the child objects according to this
     * sorting by default.
     */
    @Persistent(mappedBy = "parent")
    @Getter @Setter
    private SortedSet<ChildObject> children;
    
    /*
     * This is an action to create a new child object.
     * 
     * An action will turn into a button in the UI. The position
     * of the button can be changed by moving it to a certain location
     * in the grid defined in the layout.xml.
     * 
     * The panel to input the parameters of the action will 
     * open as a side bar on the right side of the UI, but this 
     * can be changed globally or per action to open as a modal 
     * dialogue instead.
     */
    @Action
    public ChildObject createChildObject(
    		@Parameter(optionality = Optionality.MANDATORY) 
    		String name) {
    	ChildObject child = new ChildObject();
    	child.setName(name);
    	child.setParent(this);
    	repositoryService.persist(child);
        messageService.informUser(String.format("Child '%s' of '%s' created", name, this.getName()));
    	return child;
    }
    
    /*
     * Delete
     */
    
    /*
     * This action will delete the object.
     * 
     * Since there are no parameters no side bar will be shown before execution.
     * 
     * The semantics of NON_IDEMPOTENT_ARE_YOU_SURE will make sure a confirmation
     * dialogue is displayed before executing to action though.
     */
    @Action(semantics = NON_IDEMPOTENT_ARE_YOU_SURE)
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.removeAndFlush(this);
    } 
    
    /*
     * This method belongs to the programming model and 
     * enables or disables the "Delete" button for the "delete"
     * action above.
     * 
     * The string returned by a "disableXxx" function is shown as
     * tool tip on the disabled button.
     * 
     * When a disable function returns null the related action
     * is enabled.
     */
    public String disableDelete() {
    	if(! getChildren().isEmpty()) {
    		return "Can't delete a SimpleObject that has one or more ChildObjects";
    	}
    	return null;
    }
    
    /*
     * Comparable
     */

    private final static Comparator<SimpleObject> comparator =
            Comparator.comparing(SimpleObject::getName);

    @Override
    public int compareTo(final SimpleObject other) {
        return comparator.compare(this, other);
    }

}
