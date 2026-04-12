package domainapp.webapp.metamodel;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import org.apache.causeway.applib.services.registry.ServiceRegistry;
import org.apache.causeway.testing.integtestsupport.applib.validate.DomainModelValidator;

import org.springframework.test.annotation.DirtiesContext;

import domainapp.webapp.WebAppIntegTestAbstract;

class ValidateDomainModel_IntegTest extends WebAppIntegTestAbstract {

    @Inject ServiceRegistry serviceRegistry;

    @Test
    void validate() {
        new DomainModelValidator(serviceRegistry).assertValid();
    }


}
