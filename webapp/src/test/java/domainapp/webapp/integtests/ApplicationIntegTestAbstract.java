package domainapp.webapp.integtests;

import domainapp.webapp.application.ApplicationModule;

import org.apache.isis.applib.annotation.Property;
import org.apache.isis.config.IsisPresets;
import org.apache.isis.extensions.fixtures.IsisExtFixturesModule;
import org.apache.isis.integtestsupport.IsisIntegrationTestAbstract;
import org.apache.isis.jdo.IsisBootDataNucleus;
import org.apache.isis.runtime.spring.IsisBoot;
import org.apache.isis.security.bypass.IsisBootSecurityBypass;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(
        classes = ApplicationIntegTestAbstract.AppManifest.class,
        properties = {
                "logging.config=log4j2-test.xml"
        }
)
@ContextConfiguration
public abstract class ApplicationIntegTestAbstract extends IsisIntegrationTestAbstract {

    @Configuration
    @PropertySources({
            @PropertySource(IsisPresets.H2InMemory_withUniqueSchema),
            @PropertySource(IsisPresets.DataNucleusAutoCreate),
    })
    @Import({
            IsisBoot.class,
            IsisBootDataNucleus.class,
            IsisBootSecurityBypass.class,
            IsisExtFixturesModule.class,
            ApplicationModule.class
    })
    public static class AppManifest {
    }

}
