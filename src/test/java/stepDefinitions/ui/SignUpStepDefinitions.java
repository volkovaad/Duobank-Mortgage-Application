package stepDefinitions.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignInPage;
import pages.SignUpPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;
import java.util.Map;

public class SignUpStepDefinitions {

    @Given("the user is on the login page")
  public void theUserIsOnTheHomepage() {
            Driver.getDriver().get(ConfigReader.getProperty("url"));
        }

    @And("the user navigate to the Sign Up page")
    public void theUserClicksOnTheSignUpLink() {
        new SignInPage().getSignUpButton().click();
    }

    @When("The user fills up the fields with valid info")
    public void signup_with_valid_info() {
        new SignUpPage().signUpWithRandomData();
    }
    @Then("the user  should see a {string} message")
    public void messageToSee(String string) {
        Assert.assertTrue(Driver.getDriver().getPageSource().contains(string));
    }
    @Then("the user  should be redirected to the Sign In page")
    public void redirected_to_the_sign_in_page()  {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(ConfigReader.getProperty("url")));
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), (ConfigReader.getProperty("url")));
    }



    @When("The user fills up the fields with the following info as")
    public void theUserFillsUpTheFieldsWithTheFollowingInfoAs(Map<String, String> map) throws InterruptedException {
        Thread.sleep(1000);
        new SignUpPage().signUp(
                map.get("firsName"),
                map.get("lastName"),
                map.get("email"),
                map.get("password")
        );

    }

    @Then("the user should see an error message saying {string}")
    public void theUserShouldSeeAnErrorMessageSaying(String string) {
        Assert.assertTrue(Driver.getDriver().getPageSource().contains(string));

    }

    @When("the user enters an invalid email address")
    public void theUserEntersAnInvalidEmailAddress(Map<String, String> map) {
        new SignUpPage().signUp(
                map.get("firsName"),
                map.get("lastName"),
                map.get("email"),
                map.get("password")
        );
    }

    @Then("the user shouldn't be redirected to the Sign In page")
    public void theUserShouldnTBeRedirectedToTheSignInPage() {
 //Assert.assertTrue(new SignUpPage().getEmailmessage().isDisplayed());
       // Assert.assertTrue(new SignUpPage().getEmailerror().isDisplayed());
        Assert.assertEquals("http://qa-duobank.us-east-2.elasticbeanstalk.com/register.php", Driver.getDriver().getCurrentUrl());
    }


    @When("the user enters an invalid email address without .com")
    public void theUserEntersAnInvalidEmailAddressWithoutCom(Map<String, String> map) {
        new SignUpPage().signUp(
                map.get("firsName"),
                map.get("lastName"),
                map.get("email"),
                map.get("password")
        );
    }

    @Then("the user should see an error message")
    public void theUserShouldSeeAnErrorMessage() {

        Assert.assertTrue(new SignUpPage().getEmailerror().isDisplayed());
    }


    @When("the user enters a weak password")
    public void theUserEntersAWeakPassword(Map<String, String> map) {
        new SignUpPage().signUp(
                map.get("firsName"),
                map.get("lastName"),
                map.get("email"),
                map.get("password")
        );
    }

    @Then("the user should see an error message for password")
    public void theUserShouldSeeAnErrorMessageForPassword() {
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"));
    }

    @When("the user on the Sign Up page he should see a {string} {string} link")
    public void theUserOnTheSignUpPageHeShouldSeeALink(String string, String str) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://qa-duobank.us-east-2.elasticbeanstalk.com/register.php"));

        Assert.assertTrue(Driver.getDriver().getPageSource().contains(string));
        Assert.assertTrue(Driver.getDriver().getPageSource().contains(str));
    }
    @When("the user click on the link")
    public void the_user_click_on_the_link() {
        new SignUpPage().getSigninbutton().click();
    }
    @Then("the user should be redirected to the Sign In page")
    public void the_user_should_be_redirected_to_the_sign_in_page() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(ConfigReader.getProperty("url")));
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), (ConfigReader.getProperty("url")));
    }


    @When("User enters valid info")
    public void userEntersValidInfo(Map<String, String> map) throws InterruptedException {
            Thread.sleep(1000);
            new SignUpPage().signUpOutline(
                    map.get("firsName"),
                    map.get("lastName"),
                    map.get("password")
            );
    }
    @And("the user enters invalid {string}")
    public void theUserEntersInvalid(String arg0) {
        new  SignUpPage().getEmail().sendKeys(arg0);
    }
    @Then("the user should see an error")
    public void theUserShouldSeeAnError() {
        new  SignUpPage().getRegisterButton().click();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://qa-duobank.us-east-2.elasticbeanstalk.com/register.php"));
        Assert.assertEquals("http://qa-duobank.us-east-2.elasticbeanstalk.com/register.php", Driver.getDriver().getCurrentUrl());
    }


}
