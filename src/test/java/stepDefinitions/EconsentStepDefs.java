package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import pages.EconsentPage;
import utilities.Driver;
import utilities.SeleniumUtils;

public class EconsentStepDefs {

    EconsentPage econsentPage;
    @Given("I am on the E-consent page of the mortgage application")
    public void iAmOnTheEconsentPageOfTheMortgageApplication() throws InterruptedException {

        econsentPage = new EconsentPage();
        econsentPage.getMyConsentPage();

    }

    @When("The user enter their first and last name and email address")
    public void theUserEnterTheirFirstAndLastNameAndEmailAddress() {
        econsentPage.econsentInfo();

    }

    @Then("User should be able to proceed")
    public void userShouldBeAbleToProceed() {
        econsentPage.clickNextButton();
    }

    @When("I fill in the email address field with an invalid email address {string}")
    public void i_fill_in_the_email_address_field_with_an_invalid_email_address(String invalidEmail) {
        econsentPage.econsentInfo();
        econsentPage.getEmailConsent().clear();
        econsentPage.getEmailConsent().sendKeys(invalidEmail);
    }

    @When("I submit the form")
    public void i_submit_the_form() {
        econsentPage.clickNextButton();
    }

    @Then("I should see an error message stating {string}")
    public void i_should_see_an_error_message_stating(String errorMessage) {

        String actualMessage = econsentPage.getEmailConsentError().getText();
        Assert.assertEquals(errorMessage, actualMessage);

    }

    @Then("I see the necessary disclosures displayed clearly and prominently")
    public void i_see_the_necessary_disclosures_displayed_clearly_and_prominently() {
        econsentPage.getDisclosures().isDisplayed();
    }

    @Then("I should see two radio buttons for {string} and {string}")
    public void iShouldSeeTwoRadioButtonsForAnd(String arg0, String arg1) {
        econsentPage.getAgreetButton().isDisplayed();
        econsentPage.getDisAgreetButton().isDisplayed();

    }

    @Then("I see that {string} button selected by default")
    public void iSeeThatButtonSelectedByDefault(String agree) {
        econsentPage.getAgreetButton().isDisplayed();
    }

    @When("the user selects the {string} button")
    public void theUserSelectsTheButton(String button) {

        if(button.equals("Agree")){
            econsentPage.getAgreetButton().click();
        }else
            econsentPage.getDisAgreetButton().click();

    }

    @Then("the user should be redirected back to the main application flow")
    public void theUserShouldBeRedirectedBackToTheMainApplicationFlow() {
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.accept();

        String currentUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrl = "http://qa-duobank.us-east-2.elasticbeanstalk.com/dashboard.php";
        Assert.assertEquals(expectedUrl, currentUrl);
    }

    @Then("the user can proceed with application")
    public void theUserCanProceedWithApplication() {
        econsentPage.clickNextButton();
        Assert.assertEquals("http://qa-duobank.us-east-2.elasticbeanstalk.com/mortgage.php",
                Driver.getDriver().getCurrentUrl());

    }

    @When("the user tries to submit the eConsent agreement without selecting any option")
    public void theUserTriesToSubmitTheEConsentAgreementWithoutSelectingAnyOption() {
        econsentPage.getDisAgreetButton().click();
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.dismiss();


        econsentPage.clickNextButton();
        econsentPage.getDisclosures().isDisplayed();

    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        String actualMessage = econsentPage.getErrorMessage().getText();
        String expectedMessage = "This field is required.";
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}
