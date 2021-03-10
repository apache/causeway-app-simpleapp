package domainapp.webapp.bdd.stepdefs.infrastructure;

import org.apache.isis.applib.annotation.OrderPrecedence;

import domainapp.webapp.integtests.WebAppIntegTestAbstract;

public class BootstrapStepDef extends WebAppIntegTestAbstract {

    @io.cucumber.java.Before(order= OrderPrecedence.FIRST)
    public void bootstrap() {
        // empty
    }

}
