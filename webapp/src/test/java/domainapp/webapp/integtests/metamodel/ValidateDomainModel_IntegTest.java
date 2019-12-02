package domainapp.webapp.integtests.metamodel;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import org.apache.isis.integtestsupport.validate.ValidateDomainModel;
import org.apache.isis.metamodel.specloader.SpecificationLoader;

import domainapp.webapp.integtests.ApplicationIntegTestAbstract;

class ValidateDomainModel_IntegTest extends ApplicationIntegTestAbstract {
    
    @Inject protected SpecificationLoader specificationLoader;

    @Test
    void validate() {
        new ValidateDomainModel(specificationLoader).run();
    }


}