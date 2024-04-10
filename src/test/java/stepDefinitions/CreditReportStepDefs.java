package stepDefinitions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import pages.CreditReportPage;
import pages.EmploymentPage;
import pages.ExpensesPage;
import pages.PersonalInfoPage;

import java.util.Map;

public class CreditReportStepDefs {

    CreditReportPage creditReportPage = new CreditReportPage();
    EmploymentPage employmentPage;
    @Given("a user is on the Preapproval Inquiry page")
    public void userIsOnPreapprovalInquiryPage() {
        creditReportPage.navigateToPreapprovalInquiryPage();

        PersonalInfoPage personalInfo = new PersonalInfoPage();
        personalInfo.open();
        personalInfo.enterBorrowersInfo();
        personalInfo.acceptPrivacyPolicy();
        personalInfo.submitForm();

        ExpensesPage expenses = new ExpensesPage();
        expenses.monthlyRentalPayment();
        expenses.nextElement();
        EmploymentPage employmentPage = new EmploymentPage();
        employmentPage.checkbox();
        employmentPage.moveForward();

    }


    @When("the user selects {string} to order a credit report")
   public void theUserSelectsToOrderACreditReport(String arg0) {
        creditReportPage.clickYesButton();
   }


    @And("the user will fill the credit report order form")
    public void theUserWillFillTheCreditReportOrderForm()  {
        creditReportPage.orderCreditReport();

    }
    @Then("the user should be redirected to a secure third-party website")
    public void theUserShouldBeRedirectedToASecureThirdPartyWebsite() {
        creditReportPage.isRedirectedToThirdPartyWebsite();
    }

    @When("the user selects {string} to not order a credit report")
    public void theUserSelectsToNotOrderACreditReport(String option) {
        creditReportPage.clickNoButton();

    }


    @Then("the user will click {string} button")
    public void theUserWillClickButton(String arg0) {
        creditReportPage.nextButton();
    }
}