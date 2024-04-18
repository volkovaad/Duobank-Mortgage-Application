package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions (

        tags = "@db_only or @ui_to_db",
        features = "src/test/resources",
        glue = "stepDefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-report/report.html"
        },
        publish = true,
        stepNotifications = true

//        ,dryRun = true
)
@RunWith(Cucumber.class)
public class CucumberRunner {
}
