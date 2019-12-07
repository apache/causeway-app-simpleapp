package domainapp.webapp.application;

import org.apache.isis.extensions.fixtures.modules.ModuleWithFixtures;
import org.springframework.context.annotation.Import;

import domainapp.modules.simple.SimpleModule;

@org.apache.isis.applib.annotation.Module
@Import(SimpleModule.class)
public class ApplicationModule implements ModuleWithFixtures {

}
