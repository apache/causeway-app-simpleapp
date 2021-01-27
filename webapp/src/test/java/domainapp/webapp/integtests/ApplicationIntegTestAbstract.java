package domainapp.webapp.integtests;

import org.springframework.boot.test.context.SpringBootTest;

import org.apache.isis.testing.integtestsupport.applib.IsisIntegrationTestAbstract;

import domainapp.webapp.application.ApplicationModule;
import domainapp.webapp.bdd.stepdefs.BddStepDefsModule;

@SpringBootTest(
    classes = {
            SimpleApplicationTestConfiguration_usingJdo.class,
            BddStepDefsModule.class,
            ApplicationModule.class,
    },
    properties = {
            // "logging.level.io.cucumber.core.runner.Runner=DEBUG"
    }
)
public abstract class ApplicationIntegTestAbstract extends IsisIntegrationTestAbstract {

}
