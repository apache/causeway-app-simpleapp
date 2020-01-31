package domainapp.webapp.bdd.fixtures;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.isis.applib.annotation.OrderPrecedence;
import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScripts;

import lombok.val;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjects;
import domainapp.webapp.application.fixture.scenarios.DomainAppDemo;
import domainapp.webapp.integtests.ApplicationIntegTestAbstract;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class DomainAppDemoStepDef {

    @Inject private FixtureScripts fixtureScripts;

    @Before(value="@DomainAppDemo", order= OrderPrecedence.MIDPOINT)
    public void runDomainAppDemo() {
        fixtureScripts.runFixtureScript(new DomainAppDemo(), null); // <1>
    }

}
