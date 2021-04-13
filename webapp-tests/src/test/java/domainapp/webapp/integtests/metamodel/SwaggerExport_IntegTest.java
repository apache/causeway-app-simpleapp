package domainapp.webapp.integtests.metamodel;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.applib.services.swagger.Format;
import org.apache.isis.applib.services.swagger.Visibility;
import org.apache.isis.testing.integtestsupport.applib.swagger.SwaggerExporter;
import org.apache.isis.viewer.restfulobjects.jaxrsresteasy4.IsisModuleViewerRestfulObjectsJaxrsResteasy4;

import lombok.val;

import domainapp.webapp.integtests.WebAppIntegTestAbstract;

@Import({
        IsisModuleViewerRestfulObjectsJaxrsResteasy4.class
})
@DirtiesContext
class SwaggerExport_IntegTest extends WebAppIntegTestAbstract {

    @Inject ServiceRegistry serviceRegistry;

    @Test
    void export() throws IOException {
        val swaggerExporter = new SwaggerExporter(serviceRegistry);
        swaggerExporter.export(Visibility.PRIVATE, Format.JSON);
    }
}
