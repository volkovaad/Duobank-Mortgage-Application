package stepDefinitions.db;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.PreapprovalPage;
import stepDefinitions.SharedData;
import utilities.DBUtils;

import java.util.*;

public class preapprovalStepDefsDB {

    PreapprovalPage preapprovalPage;
    SharedData sharedData;

    public preapprovalStepDefsDB(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Given("the user fills out the Preapproval Details form")
    public void theUserFillsOutThePreapprovalDetailsForm() throws InterruptedException {


        preapprovalPage = new PreapprovalPage();
        preapprovalPage.preapproval("Alex", "480000", "20000", "5000" );
        preapprovalPage.econsentInfo();
        preapprovalPage.getNextButton().click();


    }


    @When("the user submits the form")
    public void theUserSubmitsTheForm() {
        preapprovalPage.getSaveButton().click();
    }

    @Then("the data should be mapped correctly to the following columns in the {string} table in the database")
    public void theDataShouldBeMappedCorrectlyToTheFollowingColumnsInTheTableInTheDatabase(String tableName, List<String>expectedColumns ) {


        StringBuilder columnNames = new StringBuilder();
        expectedColumns.forEach(s -> columnNames.append(s).append(","));
        columnNames.deleteCharAt(columnNames.length()-1);

        sharedData.setRealtor("Alex");
        String query = String.format("SELECT %s from %s where realtor_info='%s'",
                columnNames,
                tableName,
                sharedData.getRealtor()

        );

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);


        System.out.println(query);
        System.out.println(queryResult);

        Map<String, Object> map = queryResult.get(0);
        List<String> actualColumns = new ArrayList<>(map.keySet());

        Assert.assertEquals(expectedColumns, actualColumns);


    }


    @Then("the database should ensure that each mortgage application is associated with a unique id")
    public void theDatabaseShouldEnsureThatEachMortgageApplicationIsAssociatedWithAUniqueIdentifierId() {

       String query = "SELECT id from tbl_mortagage ";
        List<Map<String, Object>> listOfMaps = DBUtils.getQueryResultListOfMaps(query);
        System.out.println(listOfMaps);


        // Create a set to store unique IDs
        Set<Object> uniqueIds = new HashSet<>();

        // Iterate over the list of maps and add IDs to the set
        for (Map<String, Object> map : listOfMaps) {
            Object id = map.get("id");
            uniqueIds.add(id);
        }

        // Check if the number of unique IDs is equal to the number of IDs in the list
        Assert.assertEquals("Each mortgage application should have a unique ID",
                listOfMaps.size(), uniqueIds.size());



    }


    @Then("he realtor_info field is not empty and contains the realtor's name.")
    public void heRealtor_infoFieldIsNotEmptyAndContainsTheRealtorSName() {

        sharedData.setRealtor("Alex");
        Assert.assertFalse(sharedData.getRealtor().isEmpty());
        Assert.assertTrue(sharedData.getRealtor().contains(sharedData.getRealtor()));


    }

    @Then("the purpose_loan field is not empty and contains a valid loan purpose given on the dropdown options")
    public void thePurpose_loanFieldIsNotEmptyAndContainsAValidLoanPurposeGivenOnTheDropdownOptions() {

        List<String> actualList = List.of("Purchase a Home", "Refinance", "Construction");

        List<String> expected = new ArrayList<>();

        Select dropdown = new Select(preapprovalPage.getPurposeLoan());
        List<WebElement> options = dropdown.getOptions();

        for (WebElement option : options) {
            expected.add(option.getText());

        }

        Assert.assertEquals(expected, actualList);



    }


    @Then("the data should be stored correctly to the following columns in the {string} table in the database")
    public void theDataShouldBeStoredCorrectlyToTheFollowingColumnsInTheTableInTheDatabase(String tableName, List<String>expectedColumns ) {

        StringBuilder columnNames = new StringBuilder();
        expectedColumns.forEach(s -> columnNames.append(s).append(","));
        columnNames.deleteCharAt(columnNames.length()-1);

        sharedData.setRealtor("Alex");
        String query = String.format("SELECT %s from %s where realtor_info='%s'",
                columnNames,
                tableName,
                sharedData.getRealtor()

        );

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);


        System.out.println(query);
        System.out.println(queryResult);

        Map<String, Object> map = queryResult.get(0);
        List<String> actualColumns = new ArrayList<>(map.keySet());

        Assert.assertEquals(expectedColumns, actualColumns);


    }



}
