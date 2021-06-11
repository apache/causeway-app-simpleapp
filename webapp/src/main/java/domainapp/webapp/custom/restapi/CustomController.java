package domainapp.webapp.custom.restapi;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.isis.applib.services.iactnlayer.InteractionContext;
import org.apache.isis.applib.services.iactnlayer.InteractionService;
import org.apache.isis.applib.services.user.UserMemento;
import org.apache.isis.applib.services.xactn.TransactionalProcessor;

import lombok.RequiredArgsConstructor;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjects;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Inject})
class CustomController {

    private final InteractionService interactionService;
    private final TransactionalProcessor transactionalProcessor;
    private final SimpleObjects simpleObjects;

    @GetMapping("/custom/simpleObjects")
    List<SimpleObject> all() {
        return call("sven", simpleObjects::listAll)
                .orElse(Collections.<SimpleObject>emptyList());
    }

    private <T> Optional<T> call(
            final String username,
            final Callable<T> callable) {

        return interactionService.call(
                InteractionContext.ofUserWithSystemDefaults(UserMemento.ofName(username)),
                () -> transactionalProcessor.callWithinCurrentTransactionElseCreateNew(callable))
                .optionalElseFail(); // re-throws exception that has occurred, if any
    }

}
