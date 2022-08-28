package domainapp.webapp.application.seed;

import javax.annotation.Priority;
import javax.inject.Inject;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.events.metamodel.MetamodelEvent;
import org.apache.isis.applib.services.xactn.TransactionService;
import org.apache.isis.core.config.IsisConfiguration;
import org.apache.isis.core.config.environment.IsisSystemEnvironment;
import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScripts;

import lombok.RequiredArgsConstructor;

@Service
@Priority(PriorityPrecedence.MIDPOINT + 10)
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class SeedSecurityService {

    private final FixtureScripts fixtureScripts;
    private final TransactionService transactionService;
    private final IsisSystemEnvironment isisSystemEnvironment;

    @EventListener(MetamodelEvent.class)
    public void onMetamodelEvent(final MetamodelEvent event) {
        if (event.isPostMetamodel() && isisSystemEnvironment.isPrototyping()) {
            runScripts();
            transactionService.flushTransaction();
        }
    }

    private void runScripts() {
        fixtureScripts.run(new CustomRolesAndUsers());
    }
}
