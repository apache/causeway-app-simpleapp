package domainapp.webapp.resource;

import domainapp.modules.simple.service.SimpleEchoService;
import org.apache.wicket.request.resource.AbstractResource;

import javax.inject.Inject;
import java.io.IOException;

public class CustomResource extends AbstractResource {

    @Inject private SimpleEchoService simpleEchoService;

    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes) {
        String greeting = attributes.getParameters().get("greeting").toString();

        ResourceResponse resourceResponse = new ResourceResponse();

        resourceResponse.setStatusCode(200);
        resourceResponse.setContentType("text/plain");
        resourceResponse.disableCaching();
        resourceResponse.setWriteCallback(new WriteCallback() {
            @Override
            public void writeData(Attributes attributes) throws IOException {
                attributes.getResponse().write(simpleEchoService.greet(greeting));
            }
        });

        return resourceResponse;
    }
}
