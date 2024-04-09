package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EconsentPage;

public class EconsentStepDefs {

    EconsentPage econsentPage;
    @Given("I am on the E-consent page of the mortgage application")
    public void iAmOnTheEconsentPageOfTheMortgageApplication() {

        econsentPage = new EconsentPage();
        econsentPage.getMyConsentPage();

    }

    @When("The user enter their first and last name and email address")
    public void theUserEnterTheirFirstAndLastNameAndEmailAddress() {
        econsentPage.econsentInfo();

    }

    @Then("User should be able to proceed")
    public void userShouldBeAbleToProceed() {
    }
}
