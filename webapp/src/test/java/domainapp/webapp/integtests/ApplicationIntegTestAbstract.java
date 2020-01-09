package domainapp.webapp.integtests;

import domainapp.webapp.application.ApplicationModule;

import org.apache.isis.config.presets.IsisPresets;
import org.apache.isis.extensions.fixtures.IsisModuleExtFixtures;
import org.apache.isis.integtestsupport.IsisIntegrationTestAbstract;
import org.apache.isis.persistence.jdo.datanucleus5.IsisModuleJdoDataNucleus5;
import org.apache.isis.webboot.springboot.IsisModuleSpringBoot;
import org.apache.isis.security.bypass.IsisModuleSecurityBypass;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

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
            IsisModuleSpringBoot.class,
            IsisModuleJdoDataNucleus5.class,
            IsisModuleSecurityBypass.class,
            IsisModuleExtFixtures.class,
            ApplicationModule.class
    })
    public static class AppManifest {
    }

}
