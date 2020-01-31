package domainapp.webapp.integtests;

import domainapp.webapp.application.ApplicationModule;

import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.testing.fixtures.applib.IsisModuleTestingFixturesApplib;
import org.apache.isis.testing.integtestsupport.applib.IsisIntegrationTestAbstract;
import org.apache.isis.persistence.jdo.datanucleus5.IsisModuleJdoDataNucleus5;
import org.apache.isis.security.bypass.IsisModuleSecurityBypass;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(
        classes = ApplicationIntegTestAbstract.AppManifest.class
)
@TestPropertySource({
        IsisPresets.H2InMemory_withUniqueSchema,
        IsisPresets.DataNucleusAutoCreate,
        IsisPresets.UseLog4j2Test,
})
@ContextConfiguration
public abstract class ApplicationIntegTestAbstract extends IsisIntegrationTestAbstract {

    @Configuration
    @Import({
        IsisModuleCoreRuntimeServices.class,
        IsisModuleJdoDataNucleus5.class,
        IsisModuleSecurityBypass.class,
        IsisModuleTestingFixturesApplib.class,

        ApplicationModule.class
    })
    public static class AppManifest {
    }

}
