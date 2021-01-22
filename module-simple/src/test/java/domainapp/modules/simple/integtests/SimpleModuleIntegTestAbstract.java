package domainapp.modules.simple.integtests;

import org.springframework.boot.test.context.SpringBootTest;

import org.apache.isis.testing.fixtures.applib.IsisIntegrationTestAbstractWithFixtures;


@SpringBootTest(
        classes = SimpleModuleTestConfiguration_usingJdo.class
)
public abstract class SimpleModuleIntegTestAbstract extends IsisIntegrationTestAbstractWithFixtures {

}
