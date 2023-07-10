package domainapp.modules.simple.dom.tree;

import java.util.List;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.NatureOfService;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Parameter;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.PromptStyle;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.persistence.jdo.applib.services.JdoSupportService;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.types.Name;
import lombok.RequiredArgsConstructor;

@Named(SimpleModule.NAMESPACE + ".Books")
@DomainService(nature = NatureOfService.VIEW)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class Books {

    final RepositoryService repositoryService;
    final JdoSupportService jdoSupportService;

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public Book create(
    		@Name final String name, 
    		@Parameter(optionality = Optionality.MANDATORY) Author author) 
    {
        return repositoryService.persist(new Book(name, author));
    }
    
    @Action
    public List<Book> listAllBooks() {
    	return repositoryService.allInstances(Book.class);
    }
 

}
