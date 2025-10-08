package domainapp.webapp.integtests.metamodel;

import java.io.IOException;

import jakarta.inject.Inject;

import org.apache.causeway.viewer.restfulobjects.viewer.CausewayModuleViewerRestfulObjectsViewer;

import org.junit.jupiter.api.Test;

import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import org.apache.causeway.applib.services.registry.ServiceRegistry;
import org.apache.causeway.applib.services.swagger.Format;
import org.apache.causeway.applib.services.swagger.Visibility;
import org.apache.causeway.testing.integtestsupport.applib.swagger.SwaggerExporter;

import lombok.val;

import domainapp.webapp.integtests.WebAppIntegTestAbstract;

@Import({
        CausewayModuleViewerRestfulObjectsViewer.class,
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
