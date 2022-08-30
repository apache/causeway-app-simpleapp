package domainapp.modules.simple;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.apache.isis.extensions.fullcalendar.applib.IsisModuleExtFullCalendarApplib;
import org.apache.isis.extensions.pdfjs.applib.IsisModuleExtPdfjsApplibModel;
import org.apache.isis.persistence.jpa.applib.IsisModulePersistenceJpaApplib;
import org.apache.isis.testing.fakedata.applib.IsisModuleTestingFakeDataApplib;
import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixtures;
import org.apache.isis.testing.fixtures.applib.teardown.jpa.TeardownFixtureJpaAbstract;

import domainapp.modules.simple.dom.so.SimpleObject;

@Configuration
@Import({
        IsisModuleExtPdfjsApplibModel.class,
        IsisModuleExtFullCalendarApplib.class,
        IsisModuleTestingFakeDataApplib.class,
        IsisModulePersistenceJpaApplib.class,
})
@ComponentScan
@EnableJpaRepositories
@EntityScan(basePackageClasses = {SimpleModule.class})
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
