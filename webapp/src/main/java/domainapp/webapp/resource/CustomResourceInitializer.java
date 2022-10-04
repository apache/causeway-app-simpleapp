package domainapp.webapp.resource;

import org.apache.isis.viewer.wicket.model.isis.WicketApplicationInitializer;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
public class CustomResourceInitializer implements WicketApplicationInitializer {

    @Inject CustomResourceReference customResourceReference;

    @Override
    public void init(WebApplication webApplication) {
        webApplication.mountResource("/custom-resource-greet/${greeting}", customResourceReference);
    }
}
