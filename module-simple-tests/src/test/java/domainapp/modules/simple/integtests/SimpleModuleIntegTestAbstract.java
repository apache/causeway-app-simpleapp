package domainapp.modules.simple.integtests;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.apache.isis.testing.fixtures.applib.IsisIntegrationTestAbstractWithFixtures;


@SpringBootTest(
        classes = SimpleModuleTestConfiguration.class
)
@ActiveProfiles("test")
public abstract class SimpleModuleIntegTestAbstract extends IsisIntegrationTestAbstractWithFixtures {

}
