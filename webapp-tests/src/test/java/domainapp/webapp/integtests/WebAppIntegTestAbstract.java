package domainapp.webapp.integtests;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ActiveProfiles;

import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.persistence.jdo.datanucleus.IsisModulePersistenceJdoDatanucleus;
import org.apache.isis.security.bypass.IsisModuleSecurityBypass;
import org.apache.isis.testing.fixtures.applib.IsisModuleTestingFixturesApplib;
import org.apache.isis.testing.integtestsupport.applib.IsisIntegrationTestAbstract;

import domainapp.modules.simple.SimpleModule;
import domainapp.webapp.application.ApplicationModule;

@SpringBootTest(
    classes = {
            // we use a slightly different configuration compared to the production (AppManifest/webapp)
            WebAppIntegTestAbstract.TestApp.class,
            ApplicationModule.class,
    }
)
@ActiveProfiles("test")
public abstract class WebAppIntegTestAbstract extends IsisIntegrationTestAbstract {

    /**
     * Compared to the production app manifest <code>domainapp.webapp.AppManifest</code>,
     * here we in effect disable security checks, and we exclude any web/UI modules.
     */
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @Import({

        IsisModuleCoreRuntimeServices.class,
        IsisModuleSecurityBypass.class,
        IsisModulePersistenceJdoDatanucleus.class,
        IsisModuleTestingFixturesApplib.class,

        SimpleModule.class
    })
    @PropertySources({
        @PropertySource(IsisPresets.H2InMemory_withUniqueSchema),
        @PropertySource(IsisPresets.DatanucleusAutocreateNoValidate),
        @PropertySource(IsisPresets.UseLog4j2Test),
    })
    public static class TestApp {

    }
}
