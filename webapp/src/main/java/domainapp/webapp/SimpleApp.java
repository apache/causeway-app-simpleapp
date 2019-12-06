package domainapp.webapp;

import domainapp.webapp.application.ApplicationModule;
import domainapp.webapp.application.fixture.scenarios.DomainAppDemo;

import org.apache.isis.extensions.fixtures.IsisModuleExtFixtures;
import org.apache.isis.extensions.h2console.dom.IsisModuleExtH2Console;
import org.apache.isis.persistence.jdo.datanucleus5.IsisModuleJdoDataNucleus5;
import org.apache.isis.viewer.restfulobjects.viewer.IsisModuleRestfulObjectsViewer;
import org.apache.isis.webboot.springboot.IsisModuleSpringBoot;
import org.apache.isis.security.shiro.IsisModuleSecurityShiro;
import org.apache.isis.viewer.wicket.viewer.IsisModuleWicketViewer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.apache.isis.config.presets.IsisPresets;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@Import({
    SimpleApp.AppManifest.class,
})
public class SimpleApp extends SpringBootServletInitializer {

    @Configuration
    @PropertySources({
            @PropertySource(IsisPresets.NoTranslations),
            @PropertySource(IsisPresets.DataNucleusAutoCreate),
            @PropertySource(IsisPresets.DebugDiscovery),
    })
    @Import({
            IsisModuleSpringBoot.class,
            IsisModuleSecurityShiro.class,
            IsisModuleJdoDataNucleus5.class,
            IsisModuleRestfulObjectsViewer.class,
            IsisModuleWicketViewer.class,

            IsisModuleExtFixtures.class,
            IsisModuleExtH2Console.class,

            ApplicationModule.class,
            DomainAppDemo.class // register this fixture
    })
    public static class AppManifest {
    }

    /**
     * @implNote this is to support the <em>Spring Boot Maven Plugin</em>, which auto-detects an
     * entry point by searching for classes having a {@code main(...)}
     */
    public static void main(String[] args) {
        IsisPresets.prototyping();
        SpringApplication.run(new Class[] { SimpleApp.class }, args);
    }

}
