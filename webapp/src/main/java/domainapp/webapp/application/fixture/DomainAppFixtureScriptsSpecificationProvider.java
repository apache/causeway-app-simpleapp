package domainapp.webapp.application.fixture;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.extensions.fixtures.fixturescripts.FixtureScripts;
import org.apache.isis.extensions.fixtures.fixturespec.FixtureScriptsSpecification;
import org.apache.isis.extensions.fixtures.fixturespec.FixtureScriptsSpecificationProvider;

import domainapp.webapp.application.fixture.scenarios.DomainAppDemo;

/**
 * Specifies where to find fixtures, and other settings.
 */
@DomainService(
        nature = NatureOfService.DOMAIN
        )
public class DomainAppFixtureScriptsSpecificationProvider implements FixtureScriptsSpecificationProvider {
    @Override
    public FixtureScriptsSpecification getSpecification() {
        return FixtureScriptsSpecification
                .builder(DomainAppFixtureScriptsSpecificationProvider.class)
                .with(FixtureScripts.MultipleExecutionStrategy.EXECUTE)
                .withRunScriptDefault(DomainAppDemo.class)
                .withRecreate(DomainAppDemo.class)
                .build();
    }
}
