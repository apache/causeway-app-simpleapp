package domainapp.modules.simple.dom.so;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;

@DomainService(
        nature = NatureOfService.REST,
        objectType = "simple.SimpleObjectRestApi"
        )
@lombok.RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class SimpleObjectRestApi {

    private final SimpleObjects simpleObjects;

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public SimpleObject copy(
            final SimpleObject original
            ) {
        return simpleObjects.create(original.getName() + " (copy)");
    }

}
