package domainapp.modules.simple.dom.so;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.persistence.jpa.applib.services.JpaSupportService;

import domainapp.modules.simple.types.Name;

@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "simple.SimpleObjects"
)
@javax.annotation.Priority(PriorityPrecedence.EARLY)
@lombok.RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class SimpleObjects {

    final RepositoryService repositoryService;
    final JpaSupportService jpaSupportService;
    final SimpleObjectRepository simpleObjectRepository;


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public SimpleObject create(
            @Name final String name) {
        return repositoryService.persist(SimpleObject.withName(name));
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public List<SimpleObject> findByNameLike(
            @Name final String name) {
        return repositoryService.allMatches(
                Query.named(SimpleObject.class, SimpleObject.NAMED_QUERY__FIND_BY_NAME_LIKE)
                     .withParameter("name", "%" + name + "%"));
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public List<SimpleObject> findByName(
            @Name final String name
            ) {
        return simpleObjectRepository.findByNameContaining(name);
    }


    @Programmatic
    public SimpleObject findByNameExact(final String name) {
        return simpleObjectRepository.findByName(name);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<SimpleObject> listAll() {
        return simpleObjectRepository.findAll();
    }




    @Programmatic
    public void ping() {
        jpaSupportService.getEntityManager(SimpleObject.class)
            .ifSuccess(entityManager -> {
                final TypedQuery<SimpleObject> q = entityManager.createQuery(
                        "SELECT p FROM SimpleObject p ORDER BY p.name",
                        SimpleObject.class)
                    .setMaxResults(1);
                q.getResultList();
            });
    }


}
