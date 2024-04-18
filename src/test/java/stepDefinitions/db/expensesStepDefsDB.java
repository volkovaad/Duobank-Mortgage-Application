package stepDefinitions.db;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.ExpensesPage;
import pages.PreapprovalPage;
import pages.SummaryPage;
import stepDefinitions.SharedData;
import stepDefinitions.ui.ExpensesStepDefs;
import utilities.DBUtils;

import java.util.*;

public class expensesStepDefsDB {


    ExpensesPage expensesPage;
    SummaryPage summaryPage;
    SharedData sharedData;

    public expensesStepDefsDB(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Given("the user fills out the Expenses Details form")
    public void theUserFillsOutTheExpensesDetailsForm() throws InterruptedException{

        summaryPage = new SummaryPage();
        summaryPage.open();
        summaryPage.saveApplication();
//        expensesPage = new ExpensesPage();
//        expensesPage.rentCheckbox();
//        expensesPage.monthlyMortgagePayment();
//        expensesPage.nextElement();

    }
    @Then("the Expenses data should be stored correctly to the following columns in the {string} table in the database")
    public void theExpensesDataShouldBeStoredCorrectlyToTheFollowingColumnsInTheTableInTheDatabase(String tableName, List<String>expectedColumns ) {

        StringBuilder columnNames = new StringBuilder();
        expectedColumns.forEach(s -> columnNames.append(s).append(","));
        columnNames.deleteCharAt(columnNames.length()-1);


        String query = String.format("SELECT %s from %s where rent_own_status='%s'",
                columnNames,
                tableName,
                sharedData.getRentCheckbox(),
                sharedData.getOwnCheckbox()

        );

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);


        System.out.println(query);
        System.out.println(queryResult);

        Map<String, Object> map = queryResult.get(0);
        List<String> actualColumns = new ArrayList<>(map.keySet());

        Assert.assertEquals(expectedColumns, actualColumns);

    }


    @Then("the rent_own_status field is not empty and contains rent checkbox.")
    public void theRent_own_statusFieldIsNotEmptyAndContainsRentCheckbox() {
        expensesPage.rentCheckbox();
        Assert.assertFalse(sharedData.getRentCheckbox().isEmpty());
        Assert.assertTrue(sharedData.getRentCheckbox().contains(sharedData.getRentCheckbox()));
    }

    @Then("the rent_own_status field is not empty and contains own checkbox.")
    public void theRent_own_statusFieldIsNotEmptyAndContainsOwnCheckbox() {
        expensesPage.ownCheckbox();
        Assert.assertFalse(sharedData.getOwnCheckbox().isEmpty());
        Assert.assertTrue(sharedData.getOwnCheckbox().contains(sharedData.getOwnCheckbox()));
    }

    @Then("the monthly_rental_payment field is not empty and contains a payment.")
    public void theMonthly_rental_paymentFieldIsNotEmptyAndContainsAPayment() {
        expensesPage.monthlyRentalPayment();
        Assert.assertFalse(sharedData.getMonthlyRentalPayment().isEmpty());
        Assert.assertTrue(sharedData.getMonthlyRentalPayment().contains(sharedData.getMonthlyRentalPayment()));
    }

    @Then("the first_mortgage_total_payment field is not empty and contains a payment.")
    public void theFirst_mortgage_total_paymentFieldIsNotEmptyAndContainsAPayment() {

        expensesPage.monthlyMortgagePayment();
        Assert.assertFalse(sharedData.getMonthlyMortgagePayment().isEmpty());
        Assert.assertTrue(sharedData.getMonthlyMortgagePayment().contains(sharedData.getMonthlyMortgagePayment()));
    }

    @Then("the Expenses data should be mapped correctly to the following columns in the {string} table in the database")
    public void theExpensesDataShouldBeMappedCorrectlyToTheFollowingColumnsInTheTableInTheDatabase(String tableName, List<String>expectedColumns ) {


        StringBuilder columnNames = new StringBuilder();
        expectedColumns.forEach(s -> columnNames.append(s).append(","));
        columnNames.deleteCharAt(columnNames.length()-1);

        String query = String.format("SELECT %s from %s where rent_own_status='%s'",
                columnNames,
                tableName,
                sharedData.getRentCheckbox(),
                sharedData.getOwnCheckbox()

        );

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);


        System.out.println(query);
        System.out.println(queryResult);

        Map<String, Object> map = queryResult.get(0);
        List<String> actualColumns = new ArrayList<>(map.keySet());

        Assert.assertEquals(expectedColumns, actualColumns);


    }

}
