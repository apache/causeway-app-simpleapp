package domainapp.webapp.application;

import org.apache.isis.extensions.fixtures.modules.Module;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import domainapp.modules.simple.SimpleModule;

@org.apache.isis.applib.annotation.Module
@Import(SimpleModule.class)
public class ApplicationModule implements Module {

}
