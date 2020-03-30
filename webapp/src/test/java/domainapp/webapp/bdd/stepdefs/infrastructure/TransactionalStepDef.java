package domainapp.webapp.bdd.stepdefs.infrastructure;

import javax.inject.Inject;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import org.apache.isis.applib.annotation.OrderPrecedence;
import org.apache.isis.core.runtime.iactn.IsisInteraction;
import org.apache.isis.core.runtime.iactn.IsisInteractionFactory;
import org.apache.isis.core.runtime.session.init.InitialisationSession;

import lombok.val;

import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * equivalent to the Spring @Transactional attribute
 */
public class TransactionalStepDef {

    private Runnable afterScenario;

    @Before(order = OrderPrecedence.EARLY)
    public void beforeScenario(){
        val isisInteraction = isisInteractionFactory.openSession(new InitialisationSession());
        val txTemplate = new TransactionTemplate(txMan);
        val status = txTemplate.getTransactionManager().getTransaction(null);
        afterScenario = () -> {
            txTemplate.getTransactionManager().rollback(status);
            isisInteractionFactory.closeSessionStack();
        };

        status.flush();
    } 

    @After
    public void afterScenario(){
        if(afterScenario==null) {
            return;
        }
        afterScenario.run();
        afterScenario = null;
    }

    @Inject private IsisInteractionFactory isisInteractionFactory;
    @Inject private PlatformTransactionManager txMan;

}
