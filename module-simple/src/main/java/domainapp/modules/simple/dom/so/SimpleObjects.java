package domainapp.modules.simple.dom.so;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.types.Name;

@DomainService(
        nature = NatureOfService.VIEW,
        objectType = "simple.SimpleObjects"
        )
@lombok.RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class SimpleObjects {

    private final RepositoryService repositoryService;

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public SimpleObject create(@Name final String name) {
        return repositoryService.persist(SimpleObject.withName(name));
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public List<SimpleObject> findByName(@Name final String name) {
    	Query<SimpleObject> q = Query.named(SimpleObject.class, "findByName")
    		.withParameter("name", name);
    	return repositoryService.allMatches(q);
    }

    @Programmatic
    public Optional<SimpleObject> findByNameExact(final String name) {
    	Query<SimpleObject> q = Query.named(SimpleObject.class, "findByNameExact")
        		.withParameter("name", name);
        	return repositoryService.uniqueMatch(q);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<SimpleObject> listAll() {
        return repositoryService.allInstances(SimpleObject.class);
    }
}
