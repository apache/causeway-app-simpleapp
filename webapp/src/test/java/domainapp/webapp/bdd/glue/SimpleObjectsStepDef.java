package domainapp.webapp.bdd.glue;

import domainapp.modules.simple.dom.impl.SimpleObject;
import domainapp.modules.simple.dom.impl.SimpleObjects;
import domainapp.modules.simple.fixture.SimpleObject_persona;
import domainapp.webapp.application.fixture.scenarios.DomainAppDemo;
import domainapp.webapp.integtests.ApplicationIntegTestAbstract;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.val;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScripts;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleObjectsStepDef extends ApplicationIntegTestAbstract {

    @Given("^there are.* (\\d+) simple objects$")
    public void there_are_N_simple_objects(int n) throws Throwable {
        final List<SimpleObject> list = wrap(simpleObjects).listAll();
        assertThat(list.size(), is(n));
    }

    @When("^.*create a .*simple object$")
    public void create_a_simple_object() throws Throwable {
        wrap(simpleObjects).create(UUID.randomUUID().toString());
    }

    // -- TRANSACTION ASPECT

    private Runnable afterScenario;

    @Before //TODO is there another way to make scenarios transactional?
    public void beforeScenario(){
        val txTemplate = new TransactionTemplate(txMan);
        val status = txTemplate.getTransactionManager().getTransaction(null);
        afterScenario = () -> {
            txTemplate.getTransactionManager().rollback(status);
        };

		fixtureScripts.runPersonas(
		        SimpleObject_persona.BANG, 
		        SimpleObject_persona.BAR, 
		        SimpleObject_persona.BAZ);

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


    // -- DEPENDENCIES

    @Inject protected SimpleObjects simpleObjects;
    @Inject private FixtureScripts fixtureScripts;
    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Inject private PlatformTransactionManager txMan;

    @Before(value="@DomainAppDemo", order=20000)
    public void runDomainAppDemo() {
        fixtureScripts.runFixtureScript(new DomainAppDemo(), null); // <1>
    }

}
