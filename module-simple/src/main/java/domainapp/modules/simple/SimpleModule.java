package domainapp.modules.simple;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.apache.causeway.extensions.fullcalendar.applib.CausewayModuleExtFullCalendarApplib;
import org.apache.causeway.extensions.pdfjs.applib.CausewayModuleExtPdfjsApplib;
import org.apache.causeway.persistence.jdo.applib.CausewayModulePersistenceJdoApplib;
import org.apache.causeway.testing.fakedata.applib.CausewayModuleTestingFakeDataApplib;
import org.apache.causeway.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.causeway.testing.fixtures.applib.modules.ModuleWithFixtures;
import org.apache.causeway.testing.fixtures.applib.teardown.jdo.TeardownFixtureJdoAbstract;

import domainapp.modules.simple.dom.so.SimpleObject;

@Configuration
@Import({
        CausewayModuleExtPdfjsApplib.class,
        CausewayModuleExtFullCalendarApplib.class,
        CausewayModuleTestingFakeDataApplib.class,
        CausewayModulePersistenceJdoApplib.class,
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
