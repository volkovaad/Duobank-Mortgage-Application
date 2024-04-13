package stepDefinitions.ui;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.ExpensesPage;

public class ExpensesStepDefs {

    ExpensesPage expensesPage;

    @Given("^I am on the Expenses page of the mortgage application$")
    public void iAmOnExpensesPage() {
        expensesPage = new ExpensesPage();
        expensesPage.open();
    }


    @When("I select Rent")
    public void iSelectRent() {
        expensesPage.rentCheckbox();
    }


    @Then("the Monthly Rental Payment field should be required and must only allow positive numbers")
    public void theMonthlyRentalPaymentFieldShouldBeRequiredAndMustOnlyAllowPositiveNumbers() {
        expensesPage.monthlyRentalPayment();
    }

    @And("I should be able to move forward to the next page only after all required fields have been completed")
    public void iShouldBeAbleToMoveForwardToTheNextPageOnlyAfterAllRequiredFieldsHaveBeenCompleted() {
        expensesPage.nextElement();
        Assert.assertTrue(expensesPage.isNextPageDisplayed());


    }


    @When("I select Own")
    public void iSelectOwn() {
        expensesPage.ownCheckbox();

    }

    @Then("the Monthly Mortgage Payment field should be required and must only allow positive numbers")
    public void theMonthlyMortgagePaymentFieldShouldBeRequiredAndMustOnlyAllowPositiveNumbers() {
        expensesPage.monthlyMortgagePayment();

    }


    @When("I click on Previous button")
    public void iClickOnPreviousButton() {
        expensesPage.previousElement();

    }

    @Then("I should be navigated to the previous page")
    public void iShouldBeNavigatedToThePreviousPage() {
        Assert.assertTrue(expensesPage.isPreviousPageDisplayed());
    }
}
