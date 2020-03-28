package domainapp.webapp.bdd.stepdefs.fixtures;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.isis.applib.annotation.OrderPrecedence;
import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScripts;

import domainapp.webapp.application.fixture.scenarios.DomainAppDemo;
import io.cucumber.java.Before;

public class DomainAppDemoStepDef {

    @Inject private FixtureScripts fixtureScripts;

    @Before(value="@DomainAppDemo", order= OrderPrecedence.MIDPOINT)
    public void runDomainAppDemo() {
        fixtureScripts.runFixtureScript(new DomainAppDemo(), null); // <1>
    }

}
