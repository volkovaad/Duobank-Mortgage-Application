package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions (

        tags = "@regression",
        features = "src/test/resources",
        glue = "stepDefinitions",
        plugin = {
             "html:target/cucumber-report/report.html"
        },
        publish = true

//        ,dryRun = true
)
@RunWith(Cucumber.class)
public class CucumberRunner {
}