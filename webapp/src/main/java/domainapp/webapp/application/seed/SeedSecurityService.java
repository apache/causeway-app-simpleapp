package domainapp.webapp.application.seed;

import javax.annotation.Priority;
import javax.inject.Inject;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.events.metamodel.MetamodelEvent;
import org.apache.causeway.applib.services.xactn.TransactionService;
import org.apache.causeway.core.config.environment.CausewaySystemEnvironment;
import org.apache.causeway.testing.fixtures.applib.fixturescripts.FixtureScripts;

import lombok.RequiredArgsConstructor;

@Service
@Priority(PriorityPrecedence.MIDPOINT + 10)
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class SeedSecurityService {

    private final FixtureScripts fixtureScripts;
    private final TransactionService transactionService;
    private final CausewaySystemEnvironment causewaySystemEnvironment;

    @EventListener(MetamodelEvent.class)
    public void onMetamodelEvent(final MetamodelEvent event) {
        if (event.isPostMetamodel() && causewaySystemEnvironment.isPrototyping()) {
            runScripts();
            transactionService.flushTransaction();
        }
    }

    private void runScripts() {
        fixtureScripts.run(new CustomRolesAndUsers());
    }
}
