package stepDefinitions.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignInPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;
import java.util.Map;

public class LogInStepdefs {

    @Given("the user is on the log in page")
    public void theUserIsOnTheHomepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }
    @Then("the user should see a welcome message")
    public void theUserShouldSeeAWelcomeMessage() {
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Welcome Back!"));
    }


    @When("The user fills up the fields with the following info")
    public void theUserFillsUpTheFieldsWithTheFollowingInfo(Map<String, String> map) {
        new SignInPage().signIn(
                map.get("email"),
                map.get("password")
        );

    }

    @And("password should be masked and required")
    public void passwordShouldBeMasked() {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",new SignInPage().getPasswordRequired());
        Assert.assertTrue(isRequired);

        Assert.assertEquals("password", new SignInPage().getPassword().getAttribute("type"));
        new SignInPage().getSignInButton().click();



    }

    @Then("the user should be redirected to the mortgage account dashboard")
    public void theUserShouldBeRedirectedToTheMortgageAccountDashboard() {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://qa-duobank.us-east-2.elasticbeanstalk.com/dashboard.php"));
        Assert.assertEquals("http://qa-duobank.us-east-2.elasticbeanstalk.com/dashboard.php", Driver.getDriver().getCurrentUrl());
    }

    @When("The user fills up the fields with the  info as")
    public void the_user_fills_up_the_fields_with_the_info_as(Map<String, String> map) {
        new SignInPage().signIn(
                map.get("email"),
                map.get("password")
        );
        new SignInPage().getSignInButton().click();
    }
    @Then("the user should see an error message {string}")
    public void the_user_should_see_an_error_message(String string) {
Assert.assertTrue(Driver.getDriver().getPageSource().contains(string));
    }


    @When("the user enters no username and password")
    public void theUserEntersNoUsernameAndPassword() {
        new SignInPage().signinEmpty();
    }

    @Then("the user should not be directed to the personal dashboard")
    public void theUserShouldNotBeDirectedToThePersonalDashboard() {
        Assert.assertEquals("http://qa-duobank.us-east-2.elasticbeanstalk.com/index.php", Driver.getDriver().getCurrentUrl());
    }



    @Then("The user should see a {string}  {string} link")
    public void the_user_should_see_a_link(String string, String str) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(ConfigReader.getProperty("url")));
     Assert.assertTrue(Driver.getDriver().getPageSource().contains(string));
        Assert.assertTrue(Driver.getDriver().getPageSource().contains(str));
    }

    @When("The user click the link")
    public void the_user_click_the_link() {
        new SignInPage().getSignUpButton().click();
    }
    @Then("The user should be redirected to the sign up page")
    public void the_user_should_be_redirected_to_the_sign_up_page() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://qa-duobank.us-east-2.elasticbeanstalk.com/register.php"));
        Assert.assertEquals("http://qa-duobank.us-east-2.elasticbeanstalk.com/register.php", Driver.getDriver().getCurrentUrl());
    }

}
