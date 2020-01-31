package domainapp.modules.simple;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.teardown.TeardownFixtureAbstract;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixtures;

import domainapp.modules.simple.dom.so.SimpleObject;

@Configuration
@Import({})
@ComponentScan
public class SimpleModule implements ModuleWithFixtures {

    @Override
    public FixtureScript getTeardownFixture() {
        return new TeardownFixtureAbstract() {
            @Override
            protected void execute(ExecutionContext executionContext) {
                deleteFrom(SimpleObject.class);
            }
        };
    }

    public static class PropertyDomainEvent<S,T>
    extends org.apache.isis.applib.events.domain.PropertyDomainEvent<S,T> {}
    
    public static class CollectionDomainEvent<S,T>
    extends org.apache.isis.applib.events.domain.CollectionDomainEvent<S,T> {}
    
    public static class ActionDomainEvent<S> extends
    org.apache.isis.applib.events.domain.ActionDomainEvent<S> {}
    
}
