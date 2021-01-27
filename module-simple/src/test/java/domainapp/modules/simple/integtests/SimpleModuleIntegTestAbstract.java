package domainapp.modules.simple.integtests;

import org.springframework.boot.test.context.SpringBootTest;

import org.apache.isis.testing.fixtures.applib.IsisIntegrationTestAbstractWithFixtures;


@SpringBootTest(
        classes = {
                SimpleModuleTestConfiguration_usingJpa.class
        },
        properties = "isis.persistence.jpa.auto-create-schemas=simple"
)
public abstract class SimpleModuleIntegTestAbstract extends IsisIntegrationTestAbstractWithFixtures {

}
