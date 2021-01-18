package domainapp.modules.simple.integtests;

import org.springframework.boot.test.context.SpringBootTest;

import org.apache.isis.testing.fixtures.applib.IsisIntegrationTestAbstractWithFixtures;

import domainapp.modules.simple.testing.SimpleModuleTestConfiguration_usingJpa;


@SpringBootTest(
        classes = {
                SimpleModuleTestConfiguration_usingJpa.class
        }
)
public abstract class SimpleModuleIntegTestAbstract extends IsisIntegrationTestAbstractWithFixtures {

}
