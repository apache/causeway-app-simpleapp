package domainapp.modules.simple.integtests;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ActiveProfiles;

import org.apache.causeway.core.config.presets.CausewayPresets;
import org.apache.causeway.core.runtimeservices.CausewayModuleCoreRuntimeServices;
import org.apache.causeway.persistence.jpa.eclipselink.CausewayModulePersistenceJpaEclipselink;
import org.apache.causeway.security.bypass.CausewayModuleSecurityBypass;
import org.apache.causeway.testing.fixtures.applib.CausewayIntegrationTestAbstractWithFixtures;
import org.apache.causeway.testing.fixtures.applib.CausewayModuleTestingFixturesApplib;

import domainapp.modules.simple.SimpleModule;


@SpringBootTest(
        classes = SimpleModuleIntegTestAbstract.TestApp.class
)
@ActiveProfiles("test")
public abstract class SimpleModuleIntegTestAbstract extends CausewayIntegrationTestAbstractWithFixtures {

    /**
     * Compared to the production app manifest <code>domainapp.webapp.AppManifest</code>,
     * here we in effect disable security checks, and we exclude any web/UI modules.
     */
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @Import({

            CausewayModuleCoreRuntimeServices.class,
            CausewayModuleSecurityBypass.class,
            CausewayModulePersistenceJpaEclipselink.class,
            CausewayModuleTestingFixturesApplib.class,

            SimpleModule.class
    })
    @PropertySources({
            @PropertySource(CausewayPresets.H2InMemory_withUniqueSchema),
            @PropertySource(CausewayPresets.UseLog4j2Test),
    })
    public static class TestApp {

    }

}
