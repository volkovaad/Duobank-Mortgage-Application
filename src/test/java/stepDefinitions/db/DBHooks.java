package stepDefinitions.db;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ConfigReader;
import utilities.DBUtils;
import utilities.Driver;

import java.time.Duration;

public class DBHooks {

    @Before ("@api")
    public void setupAPI(){
        RestAssured.baseURI = ConfigReader.getProperty("api.base.uri");
    }

    @After ("@api")
    public void tearDownApi(Scenario scenario) {
        if (scenario.isFailed()) {
            //TODO
        }
    }

        @Before ("@db_only")
    public void db(){
        DBUtils.createConnection();
    }

    @After ("@db_only")
    public void db2(){
        DBUtils.close();
    }


    @Before ("@ui_to_db")
    public void setupScenario(){
        DBUtils.createConnection();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Driver.getDriver().manage().window().maximize();
    }

    @After ("@ui_to_db")
    public void tearDownScenario(Scenario scenario){
        if(scenario.isFailed()){
            scenario.attach(((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES), "image/png", "failed");
        }

        Driver.quitDriver();
        DBUtils.close();
    }


}
