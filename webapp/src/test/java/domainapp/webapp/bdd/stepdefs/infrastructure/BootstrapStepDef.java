package domainapp.webapp.bdd.stepdefs.infrastructure;

import javax.inject.Inject;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import org.apache.isis.applib.annotation.OrderPrecedence;

import lombok.val;

import domainapp.webapp.integtests.ApplicationIntegTestAbstract;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BootstrapStepDef extends ApplicationIntegTestAbstract {

    @Before(order= OrderPrecedence.FIRST)
    public void bootstrap() {
        // empty
    }

}
