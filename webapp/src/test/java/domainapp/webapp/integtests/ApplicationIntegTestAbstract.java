package domainapp.webapp.integtests;

import org.springframework.boot.test.context.SpringBootTest;

import org.apache.isis.testing.integtestsupport.applib.IsisIntegrationTestAbstract;

import domainapp.modules.simple.testing.SimpleModuleTestConfiguration_usingJpa;
import domainapp.webapp.application.ApplicationModule;
import domainapp.webapp.bdd.stepdefs.BddStepDefsModule;

@SpringBootTest(
    classes = {
            // we use a slightly different confuguration compared to the production (AppManifest/webapp)
            SimpleModuleTestConfiguration_usingJpa.class,
            BddStepDefsModule.class,
            ApplicationModule.class,
    },
    properties = {
            // "logging.level.io.cucumber.core.runner.Runner=DEBUG"
    }
)
public abstract class ApplicationIntegTestAbstract extends IsisIntegrationTestAbstract {

}
