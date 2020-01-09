package domainapp.modules.simple.integtests;

import domainapp.modules.simple.SimpleModule;

import org.apache.isis.config.presets.IsisPresets;
import org.apache.isis.extensions.fixtures.IsisModuleExtFixtures;
import org.apache.isis.extensions.fixtures.IsisIntegrationTestAbstractWithFixtures;
import org.apache.isis.integtestsupport.IsisIntegrationTestAbstract;
import org.apache.isis.persistence.jdo.datanucleus5.IsisModuleJdoDataNucleus5;
import org.apache.isis.webboot.springboot.IsisModuleSpringBoot;
import org.apache.isis.security.bypass.IsisModuleSecurityBypass;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(
        classes = SimpleModuleIntegTestAbstract.AppManifest.class
)
@TestPropertySource({
        IsisPresets.H2InMemory_withUniqueSchema,
        IsisPresets.DataNucleusAutoCreate,
        IsisPresets.UseLog4j2Test,
})
public abstract class SimpleModuleIntegTestAbstract extends IsisIntegrationTestAbstractWithFixtures {

    @Configuration
    @Import({
        IsisModuleSpringBoot.class,
        IsisModuleSecurityBypass.class,
        IsisModuleJdoDataNucleus5.class,
        IsisModuleExtFixtures.class,

        IsisIntegrationTestAbstract.CommandSupport.class,

        SimpleModule.class
    })
    public static class AppManifest {
    }

}

