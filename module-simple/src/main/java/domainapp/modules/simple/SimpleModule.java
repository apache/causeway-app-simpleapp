package domainapp.modules.simple;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.apache.isis.extensions.pdfjs.applib.IsisModuleExtPdfjsApplibModel;
import org.apache.isis.persistence.jdo.applib.IsisModulePersistenceJdoApplib;
import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixtures;
import org.apache.isis.testing.fixtures.applib.teardown.jdo.TeardownFixtureJdoAbstract;

import domainapp.modules.simple.dom.so.SimpleObject;

@Configuration
@Import({
        IsisModuleExtPdfjsApplibModel.class,
        IsisModulePersistenceJdoApplib.class
})
@ComponentScan
public class SimpleModule implements ModuleWithFixtures {

    public static final String NAMESPACE = "simple";
    public static final String SCHEMA = "simple";

    @Override
    public FixtureScript getTeardownFixture() {
        return new TeardownFixtureJdoAbstract() {
            @Override
            protected void execute(ExecutionContext executionContext) {
                deleteFrom(SimpleObject.class);
            }
        };
    }
}
