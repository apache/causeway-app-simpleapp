package domainapp.webapp.bdd.stepdefs;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjects;
import domainapp.modules.simple.fixture.SimpleObject_persona;
import domainapp.webapp.application.fixture.scenarios.DomainAppDemo;
import domainapp.webapp.integtests.ApplicationIntegTestAbstract;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.val;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.isis.applib.services.wrapper.WrapperFactory;
import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScripts;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleObjectsStepDef {

    @Given("^there (?:is|are).* (\\d+) simple object[s]?$")
    public void there_are_N_simple_objects(int n) {
        final List<SimpleObject> list = wrap(simpleObjects).listAll();
        assertThat(list.size(), is(n));
    }

    @When("^.*create (?:a|another) .*simple object$")
    public void create_a_simple_object() {
        wrap(simpleObjects).create(UUID.randomUUID().toString());
    }

    <T> T wrap(T domainObject) {
        return wrapperFactory.wrap(domainObject);
    }

    @Inject protected SimpleObjects simpleObjects;
    @Inject protected WrapperFactory wrapperFactory;

}
