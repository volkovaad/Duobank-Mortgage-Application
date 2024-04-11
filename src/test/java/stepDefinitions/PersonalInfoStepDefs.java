package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import pages.PersonalInfoPage;

public class PersonalInfoStepDefs {
    PersonalInfoPage personalInformationPage;


    @Given("user is on the Personal Information page of the mortgage application")
    public void iAmOnThePersonalInformationPageOfTheMortgageApplication() {
        personalInformationPage = new PersonalInfoPage();
        personalInformationPage.open();

    }



    @When("I select yes for the co-borrower question")
    public void iSelectYesForTheCoBorrowerQuestion() {
        personalInformationPage.selectCoBorrowerYes();

    }

    @Then("an additional section for co-borrower's information should be displayed")
    public void anAdditionalSectionForCoBorrowerSInformationShouldBeDisplayed() {
        Assert.assertTrue(personalInformationPage.isCoBorrowerSectionDisplayed());
    }

    @When("I select no for the co-borrower question")
    public void iSelectNoForTheCoBorrowerQuestion() {
        personalInformationPage.selectCoBorrowerNo();
    }
    @Then("no additional section for co-borrower's information should be displayed")
    public void noAdditionalSectionForCoBorrowerSInformationShouldBeDisplayed() {
        Assert.assertFalse(personalInformationPage.isCoBorrowerSectionDisplayed());
    }

    @And("I fill out the borrower's information")
    public void iFillOutTheBorrowerSInformation() throws InterruptedException {
        personalInformationPage.enterBorrowersInfo();

    }


    @And("I fill out the co-borrower's information")
    public void iFillOutTheCoBorrowerSInformation() {
        personalInformationPage.enterCoBorrowersInfo();
    }

    @And("the checkbox for accepting the privacy policy should be checked")
    public void theCheckboxForAcceptingThePrivacyPolicyShouldBeChecked() {
        personalInformationPage.acceptPrivacyPolicy();
    }



    @And("I should navigate to the next page")
    public void iShouldNavigateToTheNextPage() {
        personalInformationPage.submitForm();
        Assert.assertTrue(personalInformationPage.isNextPageDisplayed());
    }


}
