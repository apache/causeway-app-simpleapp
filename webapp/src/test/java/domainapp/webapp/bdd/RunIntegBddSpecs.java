package domainapp.webapp.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;


/**
 * Runs scenarios in all <tt>.feature</tt> files (this package and any subpackages). 
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/cucumber-html-report"
                ,"json:target/cucumber.json"
        },
        strict = true,
        tags = { "not @backlog", "not @ignore" },
        glue = {
        }
        )
public class RunIntegBddSpecs {
    // intentionally empty 
}
