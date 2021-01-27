package domainapp.webapp.integtests;

import org.springframework.boot.test.context.SpringBootTest;

import org.apache.isis.testing.integtestsupport.applib.IsisIntegrationTestAbstract;

import domainapp.webapp.application.ApplicationModule;
import domainapp.webapp.bdd.stepdefs.BddStepDefsModule;
import domainapp.webapp.integtests.smoke.SimpleWebAppTestConfiguration_usingJpa;

@SpringBootTest(
    classes = {
            // we use a slightly different confuguration compared to the production (AppManifest/webapp)
            SimpleWebAppTestConfiguration_usingJpa.class,
            BddStepDefsModule.class,
            ApplicationModule.class,
    },
    properties = {
            // "logging.level.io.cucumber.core.runner.Runner=DEBUG",
            "isis.persistence.jpa.auto-create-schemas=simple"
    }
)
public abstract class ApplicationIntegTestAbstract extends IsisIntegrationTestAbstract {

}
