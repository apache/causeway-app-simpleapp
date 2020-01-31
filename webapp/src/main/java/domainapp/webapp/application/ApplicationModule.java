package domainapp.webapp.application;

import java.lang.annotation.Documented;

import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixtures;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import domainapp.modules.simple.SimpleModule;

@Configuration
@Import(SimpleModule.class)
@ComponentScan
public class ApplicationModule {

}
