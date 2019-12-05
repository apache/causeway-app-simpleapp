package domainapp.modules.simple.integtests;

import domainapp.modules.simple.SimpleModule;

import org.apache.isis.config.presets.IsisPresets;
import org.apache.isis.extensions.fixtures.IsisExtFixturesModule;
import org.apache.isis.extensions.fixtures.IsisIntegrationTestAbstractWithFixtures;
import org.apache.isis.persistence.jdo.datanucleus5.IsisBootDataNucleus;
import org.apache.isis.runtime.spring.IsisBoot;
import org.apache.isis.security.bypass.IsisBootSecurityBypass;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@SpringBootTest(
        classes = SimpleModuleIntegTestAbstract.AppManifest.class,
        properties = {"logging.config=log4j2-test.xml" }
)
public abstract class SimpleModuleIntegTestAbstract extends IsisIntegrationTestAbstractWithFixtures {

    @Configuration
    @PropertySources({
        @PropertySource(IsisPresets.H2InMemory_withUniqueSchema),
        @PropertySource(IsisPresets.DataNucleusAutoCreate),
    })
    @Import({
        IsisBoot.class,
        IsisBootSecurityBypass.class,
        IsisBootDataNucleus.class,
        IsisExtFixturesModule.class,
        SimpleModule.class
    })
    public static class AppManifest {
    }

}

