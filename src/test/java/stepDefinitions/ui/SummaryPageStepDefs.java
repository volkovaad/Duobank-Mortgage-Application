package stepDefinitions.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.*;
import utilities.Driver;
import utilities.SeleniumUtils;

public class SummaryPageStepDefs {


    SummaryPage summaryPage;


    @Given("the user is on the Summary page")
    public void theUserIsOnTheSummaryPage() {
        summaryPage = new SummaryPage();
        summaryPage.open();

    }

    @Then("I should see a clear indication that it is a review and edit page for the application")
    public void iShouldSeeAClearIndicationThatItIsAReviewAndEditPageForTheApplication() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Edit"));
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Save"));
    }


    @And("each section should have an Edit button")
    public void eachSectionShouldHaveAnEditButton() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Edit"));
    }

    @When("I click the {string} button for the {string} section")
    public void iClickTheButtonForTheSection(String editButton, String sectionName) {
        if (editButton.equals("Edit")) {
            switch (sectionName) {
                case "Pre-approval Inquiry":
                    summaryPage.editPreApproval();
                    break;
                case "Personal Details":
                    summaryPage.editPersonalDetails();
                    break;
                case "Expenses":
                    summaryPage.editExpenses();
                    break;
                case "Employment And Income":
                    summaryPage.editEmploymentAndIncome();
                    break;
                case "Credit Report":
                    summaryPage.editOrderCredit();
                    break;
                case "EConsent":
                    summaryPage.editEConsent();
                    break;
            }
        }
    }

    @Then("I should be taken back to the Pre-approval Inquiry page")
    public void iShouldBeTakenBackToThePreApprovalInquiryPage() throws InterruptedException  {
        summaryPage.editPreApproval();
        Thread.sleep(1000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("PreApproval Inquiry"));
    }

    @When("I make changes to the {string} section")
    public void iMakeChangesToTheSection(String sectionName) {
        switch (sectionName) {
            case "Pre-approval Inquiry":
                summaryPage.editPreApproval();
                PersonalInfoPage personalInfoPage = new PersonalInfoPage();
                personalInfoPage.downPayment.sendKeys("3500", Keys.ENTER);
                break;
            case "Personal Details":
                summaryPage.editPersonalDetails();
                PersonalInfoPage personalInfoPage1 = new PersonalInfoPage();
                personalInfoPage1.middleName.sendKeys("John");
                break;
            case "Expenses":
                summaryPage.editExpenses();
                ExpensesPage expensesPage = new ExpensesPage();
                summaryPage.ownCheckbox();
                expensesPage.monthlyMortgagePayment();
                break;
            case "Employment And Income":
                summaryPage.editEmploymentAndIncome();
                EmploymentPage employmentPage = new EmploymentPage();
                employmentPage.getCity().sendKeys("Los Angeles");
                break;
            case "Credit Report":
                summaryPage.editOrderCredit();
                break;
            case "EConsent":
                summaryPage.editEConsent();
                break;
        }
    }

    @Then("I should be taken back to the Personal Details page")
    public void iShouldBeTakenBackToThePersonalDetailsPage() throws InterruptedException  {
        summaryPage.editPersonalDetails();
        Thread.sleep(1000);
        SeleniumUtils.waitForPageToLoad(10000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Personal Information"));
    }

    @Then("I should be taken back to the Expenses page")
    public void iShouldBeTakenBackToTheExpensesPage() throws InterruptedException {
        summaryPage.editExpenses();
        Thread.sleep(1000);
        SeleniumUtils.waitForPageToLoad(10000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Current Monthly Housing Expenses"));
    }

    @Then("I should be taken back to the Employment And Income page")
    public void iShouldBeTakenBackToTheEmploymentAndIncomePage() throws InterruptedException {
        summaryPage.editEmploymentAndIncome();
        Thread.sleep(1000);
        SeleniumUtils.waitForPageToLoad(10000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Borrower Employment Information"));
    }

    @Then("I should be taken back to the Credit Report page")
    public void iShouldBeTakenBackToTheCreditReportPage()throws InterruptedException  {
        summaryPage.editOrderCredit();
        Thread.sleep(1000);
        SeleniumUtils.waitForPageToLoad(2000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("credit report"));
    }

    @Then("I should be taken back to the EConsent page")
    public void iShouldBeTakenBackToTheEConsentPage() throws InterruptedException {
        summaryPage.editEConsent();
        Thread.sleep(1000);
        SeleniumUtils.waitForPageToLoad(2000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Your Consent To Do Business Electronically (the eDisclosure Agreement)"));
    }

    @And("I return to the Summary page")
    public void iReturnToTheSummaryPage() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Save"));
    }

    @And("I click the Save button")
    public void iClickTheButton() {
        summaryPage.saveApplication();
    }

    @Then("I should be taken back to the start of the mortgage application page")
    public void iShouldBeTakenBackToTheStartOfTheMortgageApplicationPage() {
        SeleniumUtils.waitForPageToLoad(2000);
        Assert.assertEquals("http://qa-duobank.us-east-2.elasticbeanstalk.com/mortgage.php", Driver.getDriver().getCurrentUrl());
    }


}
