package domainapp.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import org.apache.isis.core.config.presets.IsisPresets;

@SpringBootApplication
@Import({
    AppManifest.class,
})
public class SimpleApp extends SpringBootServletInitializer {

    /**
     * @implNote this is to support the <em>Spring Boot Maven Plugin</em>, which auto-detects an
     * entry point by searching for classes having a {@code main(...)}
     */
    public static void main(String[] args) {
    	/*
    	 * This line is setting an internal environment variable called "PROTOTYPING" to "true"
    	 * that launches the framework in Prototyping mode. A respective warning is shown during
    	 * startup in the logs.
    	 * 
    	 * The prototyping mode enables some features in the UI that make it easier to get started
    	 * with developing a prototype solution. 
    	 * 
    	 * It would for instance activate and show a "Prototyping" menu in the Wicket UI 
    	 * that contains actions to execute a Fixture to create demo objects or launch 
    	 * a built in H2 console to see the contents of the database.
    	 * 
    	 * The same effect can be achieved by passing an actual environment variable 
    	 * of the same name to the JVM.
    	 */
        IsisPresets.prototyping();
        /*
         * This lines launches the Spring Boot application.
         */
        SpringApplication.run(new Class[] { SimpleApp.class }, args);
    }

}
