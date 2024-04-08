package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import pages.EmploymentPage;
import pages.ExpensesPage;
import pages.PersonalInfoPage;

import java.util.Map;

public class MorEligibStepDefs {

    @Given("the user is on the Employment and Income page")
    public void theUserIsOnTheEmploymentAndIncomePage() {

        PersonalInfoPage personalInfo = new PersonalInfoPage();
        personalInfo.open();
        personalInfo.enterBorrowersInfo();
        personalInfo.acceptPrivacyPolicy();
        personalInfo.submitForm();

        ExpensesPage expenses = new ExpensesPage();
        expenses.monthlyRentalPayment();
        expenses.nextElement();
    }

    @Given("The checkbox This is my current job should be unchecked by default")
    public void theCheckboxLabeledShouldBeUncheckedByDefault() {
        SoftAssertions softAssertions = new SoftAssertions();
        EmploymentPage employmentPage = new EmploymentPage();
        softAssertions.assertThat(employmentPage.getCurrentJobCheck().isSelected()).isFalse();
        softAssertions.assertAll();
    }

    @Given("The user enters valid employment information")
    public void theUserEntersValidEmploymentInformation(Map <String,String> map) {
        new EmploymentPage().checkbox();
        new EmploymentPage().fillInEmploymentForm(
                    map.get("employerName"),
                    map.get("position"),
                    map.get("city"),
                    map.get("state"),
                    map.get("start_date"),
                    map.get("end_date")
    );
    }

    @And("The user enters gross monthly income as")
    public void theUserEntersGrossMonthlyIncomeAs(Map <String,String> map) {
        new EmploymentPage().fillInGrossMonthlyForm(
                map.get("gross_income"),
                map.get("overtime"),
                map.get("bonuses"),
                map.get("commissions"),
                map.get("dividends_interest")
        );
    }


    @Then("Borrower Total Monthly Income field should be automatically calculated")
    public void fieldShouldBeAutomaticallyCalculated() {

    }
}
