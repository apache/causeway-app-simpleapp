package domainapp.modules.simple;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixtures;
import org.apache.isis.testing.fixtures.applib.teardown.jpa.TeardownFixtureJpaAbstract;

import domainapp.modules.simple.dom.so.SimpleObject;

@Configuration
@ComponentScan
public class SimpleModule implements ModuleWithFixtures {

    public static final String NAMESPACE = "simple";
    public static final String SCHEMA = "simple";

    @Override
    public FixtureScript getTeardownFixture() {
        return new TeardownFixtureJpaAbstract() {
            @Override
            protected void execute(ExecutionContext executionContext) {
                deleteFrom(SimpleObject.class);
            }
        };
    }
}
