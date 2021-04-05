package domainapp.webapp.quartz.job;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.isis.applib.services.user.UserMemento;
import org.apache.isis.applib.services.xactn.TransactionalProcessor;
import org.apache.isis.commons.functional.Result;
import org.apache.isis.core.interaction.session.InteractionFactory;
import org.apache.isis.core.security.authentication.Authentication;
import org.apache.isis.core.security.authentication.standard.SimpleAuthentication;

import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;

import lombok.extern.slf4j.Slf4j;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjects;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Inject})
@Slf4j
public class SampleJob implements Job {

    private final InteractionFactory interactionFactory;
    private final TransactionalProcessor transactionalProcessor;
    private final SimpleObjects simpleObjects;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        final List<SimpleObject> all = all();
        log.info("{} objects in the database", all.size());
    }

    List<SimpleObject> all() {
        return callAuthenticated(newAuthentication(), () -> simpleObjects.listAll())
                .optionalElseFail() // re-throws exception that has occurred, if any
                .orElse(Collections.emptyList()); // handles null case, if required
    }

    private SimpleAuthentication newAuthentication() {
        return SimpleAuthentication.validOf(UserMemento.ofName("sven"));
    }

    private <T> Result<T> callAuthenticated(
            final Authentication authentication,
            final Callable<T> task) {

        return interactionFactory.callAuthenticated(
                authentication,
                () -> transactionalProcessor.callWithinCurrentTransactionElseCreateNew(task));
    }
}
