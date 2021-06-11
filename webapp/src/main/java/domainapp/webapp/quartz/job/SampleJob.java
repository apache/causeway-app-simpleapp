package domainapp.webapp.quartz.job;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import org.apache.isis.applib.services.iactnlayer.InteractionContext;
import org.apache.isis.applib.services.iactnlayer.InteractionService;
import org.apache.isis.applib.services.user.UserMemento;
import org.apache.isis.applib.services.xactn.TransactionalProcessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjects;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Inject})
@Log4j2
public class SampleJob implements Job {

    private final InteractionService interactionService;
    private final TransactionalProcessor transactionalProcessor;
    private final SimpleObjects simpleObjects;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        final List<SimpleObject> all = all();
        log.info("{} objects in the database", all.size());
    }

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
