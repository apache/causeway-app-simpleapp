package domainapp.webapp.resource;

import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomResourceReference extends ResourceReference {

    public CustomResourceReference() {
        super(CustomResourceReference.class, "custom-resource");
    }

    @Override
    @Bean
    public IResource getResource() {
        return new CustomResource();
    }
}
