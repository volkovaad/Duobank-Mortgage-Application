package stepDefinitions.db;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.ExpensesPage;
import pages.PersonalInfoPage;
import pages.PreapprovalPage;
import pages.SummaryPage;
import stepDefinitions.SharedData;
import stepDefinitions.ui.PersonalInfoStepDefs;
import utilities.DBUtils;
import utilities.SeleniumUtils;

import java.rmi.dgc.Lease;
import java.sql.*;
import java.util.*;


public class personalInfoStepDefsDB {

    PersonalInfoPage personalInfoPage;
    SummaryPage summaryPage;
    PreapprovalPage preapprovalPage;


    SharedData sharedData;

    public personalInfoStepDefsDB(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Given("the user fills out the Personal Information Details form")
    public void theUserFillsOutThePersonalInformationDetailsForm() throws InterruptedException {



        summaryPage = new SummaryPage();
        summaryPage.open();
        summaryPage.saveApplication();

//        personalInfoPage = new PersonalInfoPage();
//        personalInfoPage.enterBorrowersInfo();
//        personalInfoPage.acceptPrivacyPolicy();
//        personalInfoPage.submitForm();
    }




    @Then("the borrower's data should be stored correctly to the following columns in the {string} table in the database")
    public void theBorrowerSDataShouldBeStoredCorrectlyToTheFollowingColumnsInTheTableInTheDatabase(String tableName, DataTable dataTable) throws SQLException {
        Faker faker=  new Faker();
        sharedData.setFirstName(faker.name().firstName());
        sharedData.setMiddleName(faker.name().nameWithMiddle());
        sharedData.setLastName(faker.name().lastName());
        sharedData.setSuffix("Jr");
        sharedData.setEmail(faker.internet().emailAddress());
        sharedData.setDob(faker.date().birthday().toString());
        sharedData.setSsn(faker.idNumber().valid());
        sharedData.setMarital("Married");
        sharedData.setCell(faker.phoneNumber().cellPhone());
        sharedData.setHome(faker.phoneNumber().phoneNumber());

        List<Map<String, String>> tableData = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : tableData) {
            String firstName = row.get("b_firstName");
            String middleName = row.get("b_middleName");
            String lastName = row.get("b_lastName");
            String suffix = row.get("b_suffix");
            String email = row.get("b_email");
            String dob = row.get("b_dob");
            String ssn = row.get("b_ssn");
            String maritalStatus = row.get("b_marital");
            String cell = row.get("b_cell");
            String home = row.get("b_home");
        }

        String firstName = sharedData.getFirstName();
        String middleName = sharedData.getMiddleName();
        String lastName = sharedData.getLastName();
        String suffix = sharedData.getSuffix();
        String email = sharedData.getEmail();
        String dob = sharedData.getDob();
        String ssn = sharedData.getSsn();
        String maritalStatus = sharedData.getMarital();
        String cell = sharedData.getCell();
        String home = sharedData.getHome();

        String sqlQuery = "INSERT INTO " + tableName + " (b_firstName, b_middleName, b_lastName, b_suffix, b_email, b_dob, b_ssn, b_marital, b_cell, b_home) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DriverManager.getConnection("jdbc:mysql://database-1.cb72canasobc.us-east-2.rds.amazonaws.com/loan", "duotech", "duotech2024");
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, firstName);
        statement.setString(2, middleName);
        statement.setString(3, lastName);
        statement.setString(4, suffix);
        statement.setString(5, email);
        statement.setString(6, dob);
        statement.setString(7, ssn);
        statement.setString(8, maritalStatus);
        statement.setString(9, cell);
        statement.setString(10, home);
        statement.executeUpdate();

        String sqlQuery1 = "SELECT b_firstName, b_middleName, b_lastName, b_suffix, b_email, b_dob, b_ssn, b_marital, b_cell, b_home FROM " + tableName;
      // DBUtils.executeQuery(sqlQuery1);
       DBUtils.getQueryResultAsListOfLists(sqlQuery1);


    }

    @And("the user apply with a co-borrower")
    public void theUserApplyWithACoBorrower() {
        personalInfoPage.selectCoBorrowerYes();
        personalInfoPage.enterCoBorrowersInfo();
    }

    @Then("the co-borrower's data should be stored correctly to the following columns in the {string} table in the database")
    public void theCoBorrowerSDataShouldBeStoredCorrectlyToTheFollowingColumnsInTheTableInTheDatabase(String tableName, DataTable dataTable)throws SQLException {

        Faker faker=  new Faker();
        sharedData.setCoBorrowersFirstName(faker.name().firstName());
        sharedData.setCoBorrowersMiddleName(faker.name().nameWithMiddle());
        sharedData.setCoBorrowersLastName(faker.name().lastName());
        sharedData.setCoBorrowersSuffix("Jr");
        sharedData.setCoBorrowersEmail(faker.internet().emailAddress());
        sharedData.setCoBorrowersDob(faker.date().birthday().toString());
        sharedData.setCoBorrowersSsn(faker.idNumber().valid());
        sharedData.setCoBorrowersMarital("Married");
        sharedData.setCoBorrowersCell(faker.phoneNumber().cellPhone());
        sharedData.setCoBorrowersHome(faker.phoneNumber().phoneNumber());

        List<Map<String, String>> tableData = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : tableData) {
            String coBorrowersFirstName = row.get("c_firstName");
            String coBorrowersMiddleName = row.get("c_middleName");
            String coBorrowersLastName = row.get("c_lastName");
            String coBorrowersSuffix = row.get("c_suffix");
            String coBorrowersEmail = row.get("c_email");
            String coBorrowersDob = row.get("c_dob");
            String coBorrowersSsn = row.get("c_ssn");
            String coBorrowersMaritalStatus = row.get("c_marital");
            String coBorrowersCell = row.get("c_cell");
            String coBorrowersHome = row.get("c_home");
        }
        String coBorrowersFirstName = sharedData.getCoBorrowersFirstName();
        String coBorrowersMiddleName = sharedData.getCoBorrowersMiddleName();
        String coBorrowersLastName = sharedData.getCoBorrowersLastName();
        String coBorrowersSuffix = sharedData.getCoBorrowersSuffix();
        String coBorrowersEmail = sharedData.getCoBorrowersEmail();
        String coBorrowersDob = sharedData.getCoBorrowersDob();
        String coBorrowersSsn = sharedData.getCoBorrowersSsn();
        String coBorrowersMaritalStatus = sharedData.getCoBorrowersMarital();
        String coBorrowersCell = sharedData.getCoBorrowersCell();
        String coBorrowersHome = sharedData.getCoBorrowersHome();

        String sqlQuery = "INSERT INTO " + tableName + " (c_firstName, c_middleName, c_lastName, c_suffix, c_email, c_dob, c_ssn, c_marital, c_cell, c_home) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DriverManager.getConnection("jdbc:mysql://database-1.cb72canasobc.us-east-2.rds.amazonaws.com/loan", "duotech", "duotech2024");
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, coBorrowersFirstName);
        statement.setString(2, coBorrowersMiddleName);
        statement.setString(3, coBorrowersLastName);
        statement.setString(4, coBorrowersSuffix);
        statement.setString(5, coBorrowersEmail);
        statement.setString(6, coBorrowersDob);
        statement.setString(7, coBorrowersSsn);
        statement.setString(8, coBorrowersMaritalStatus);
        statement.setString(9, coBorrowersCell);
        statement.setString(10, coBorrowersHome);
        statement.executeUpdate();

        String sqlQuery1 = "SELECT c_firstName, c_middleName, c_lastName, c_suffix, c_email, c_dob, c_ssn, c_marital, c_cell, c_home FROM " + tableName;
        DBUtils.executeQuery(sqlQuery1);
    }

    @Then("the b_firstName field is not empty and contains the borrower's first name.")
    public void theB_firstNameFieldIsNotEmptyAndContainsTheBorrowerSFirstName() {
        Faker faker=  new Faker();
        sharedData.setFirstName(faker.name().firstName());
        Assert.assertFalse(sharedData.getFirstName().isEmpty());
        Assert.assertTrue(sharedData.getFirstName().contains(sharedData.getFirstName()));
    }

    @Then("the b_lastName field is not empty and contains  contains the borrower's last name.")
    public void theB_lastNameFieldIsNotEmptyAndContainsContainsTheBorrowerSLastName() {
        Faker faker=  new Faker();
        sharedData.setLastName((faker.name().lastName()));
        Assert.assertFalse(sharedData.getLastName().isEmpty());
        Assert.assertTrue(sharedData.getLastName().contains(sharedData.getLastName()));
    }

    @Then("the b_email field is not empty and contains the borrower's email.")
    public void theB_emailFieldIsNotEmptyAndContainsTheBorrowerSEmail() {
        Faker faker=  new Faker();
        sharedData.setEmail(faker.internet().emailAddress());
        Assert.assertFalse(sharedData.getEmail().isEmpty());
        Assert.assertTrue(sharedData.getEmail().contains(sharedData.getEmail()));
    }

    @Then("the b_dob field is not empty and contains the borrower's date of birth.")
    public void theB_dobFieldIsNotEmptyAndContainsTheBorrowerSDateOfBirth() {
        Faker faker=  new Faker();
        sharedData.setDob(faker.date().birthday().toString());
        Assert.assertFalse(sharedData.getDob().isEmpty());
        Assert.assertTrue(sharedData.getDob().contains(sharedData.getDob()));
    }

    @Then("the b_ssn field is not empty and contains the borrower's social security.")
    public void theB_ssnFieldIsNotEmptyAndContainsTheBorrowerSSocialSecurity() {
        Faker faker=  new Faker();
        sharedData.setSsn(faker.idNumber().valid());
        Assert.assertFalse(sharedData.getSsn().isEmpty());
        Assert.assertTrue(sharedData.getSsn().contains(sharedData.getSsn()));
    }

    @Then("the b_marital field is not empty and contains the borrower's marital status given on the dropdown options.")
    public void theB_maritalFieldIsNotEmptyAndContainsTheBorrowerSMaritalStatusGivenOnTheDropdownOptions() {
        personalInfoPage.suffixDropdown();
        Assert.assertFalse(sharedData.getSuffix().isEmpty());
        Assert.assertTrue(sharedData.getSuffix().contains(sharedData.getSuffix()));

    }

    @Then("the b_cell field is not empty and contains the borrower's cellphone.")
    public void theB_cellFieldIsNotEmptyAndContainsTheBorrowerSCellphone() {
        Faker faker=  new Faker();
        sharedData.setCell(faker.phoneNumber().cellPhone());
        Assert.assertFalse(sharedData.getCell().isEmpty());
        Assert.assertTrue(sharedData.getCell().contains(sharedData.getCell()));
    }

    @Then("the c_firstName field is not empty and contains the co-borrower's first name.")
    public void theC_firstNameFieldIsNotEmptyAndContainsTheCoBorrowerSFirstName() {
        Faker faker=  new Faker();
        sharedData.setCoBorrowersFirstName(faker.name().firstName());
        Assert.assertFalse(sharedData.getCoBorrowersFirstName().isEmpty());
        Assert.assertTrue(sharedData.getCoBorrowersFirstName().contains(sharedData.getCoBorrowersFirstName()));
    }

    @Then("the c_lastName field is not empty and contains the co-borrower's last name.")
    public void theC_lastNameFieldIsNotEmptyAndContainsTheCoBorrowerSLastName() {
        Faker faker=  new Faker();
        sharedData.setCoBorrowersLastName((faker.name().lastName()));
        Assert.assertFalse(sharedData.getCoBorrowersLastName().isEmpty());
        Assert.assertTrue(sharedData.getCoBorrowersLastName().contains(sharedData.getCoBorrowersLastName()));
    }

    @Then("the c_email field is not empty and contains the co-borrower's email.")
    public void theC_emailFieldIsNotEmptyAndContainsTheCoBorrowerSEmail() {
        Faker faker=  new Faker();
        sharedData.setCoBorrowersEmail(faker.internet().emailAddress());
        Assert.assertFalse(sharedData.getCoBorrowersEmail().isEmpty());
        Assert.assertTrue(sharedData.getCoBorrowersEmail().contains(sharedData.getCoBorrowersEmail()));
    }

    @Then("the c_dob field is not empty and contains the co-borrower's date of birth.")
    public void theC_dobFieldIsNotEmptyAndContainsTheCoBorrowerSDateOfBirth() {
        Faker faker=  new Faker();
        sharedData.setCoBorrowersDob(faker.date().birthday().toString());
        Assert.assertFalse(sharedData.getCoBorrowersDob().isEmpty());
        Assert.assertTrue(sharedData.getCoBorrowersDob().contains(sharedData.getCoBorrowersDob()));
    }

    @Then("the c_ssn field is not empty and contains the co-borrower's social security.")
    public void theC_ssnFieldIsNotEmptyAndContainsTheCoBorrowerSSocialSecurity() {
        Faker faker=  new Faker();
        sharedData.setCoBorrowersSsn(faker.idNumber().valid());
        Assert.assertFalse(sharedData.getCoBorrowersSsn().isEmpty());
        Assert.assertTrue(sharedData.getCoBorrowersSsn().contains(sharedData.getCoBorrowersSsn()));
    }

    @Then("the c_marital field is not empty and contains the co-borrower's marital status given on the dropdown options.")
    public void theC_maritalFieldIsNotEmptyAndContainsTheCoBorrowerSMaritalStatusGivenOnTheDropdownOptions() {
        personalInfoPage.CoBorrowerSuffixDropdown();
        Assert.assertFalse(sharedData.getCoBorrowersSuffix().isEmpty());
        Assert.assertTrue(sharedData.getCoBorrowersSuffix().contains(sharedData.getCoBorrowersSuffix()));
    }

    @Then("the c_cell field is not empty and contains the co-borrower's cellphone.")
    public void theC_cellFieldIsNotEmptyAndContainsTheCoBorrowerSCellphone() {
        Faker faker=  new Faker();
        sharedData.setCoBorrowersCell(faker.phoneNumber().cellPhone());
        Assert.assertFalse(sharedData.getCoBorrowersCell().isEmpty());
        Assert.assertTrue(sharedData.getCoBorrowersCell().contains(sharedData.getCoBorrowersCell()));
    }

    @Then("the borrower's data should be mapped correctly to the following columns in the {string} table in the database")
    public void theBorrowerSDataShouldBeMappedCorrectlyToTheFollowingColumnsInTheTableInTheDatabase(String tableName, List<String>expectedColumns ) {
        StringBuilder columnNames = new StringBuilder();
        expectedColumns.forEach(s -> columnNames.append(s).append(","));
        columnNames.deleteCharAt(columnNames.length()-1);

        sharedData.setRealtor("Alex");
        String query = String.format("SELECT %s from %s where b_firstName='%s'",
                columnNames,
                tableName,
                sharedData.getFirstName()

        );

        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);


        System.out.println(query);
        System.out.println(queryResult);

        Map<String, Object> map = queryResult.get(0);
        List<String> actualColumns = new ArrayList<>(map.keySet());

        Assert.assertEquals(expectedColumns, actualColumns);
    }

    @Then("the co-borrower's data should be mapped correctly to the following columns in the {string} table in the database")
    public void theCoBorrowerSDataShouldBeMappedCorrectlyToTheFollowingColumnsInTheTableInTheDatabase(String tableName, List<String>expectedColumns ) throws InterruptedException {
        StringBuilder columnNames = new StringBuilder();
        expectedColumns.forEach(s -> columnNames.append(s).append(","));
        columnNames.deleteCharAt(columnNames.length()-1);



        String query = String.format("SELECT %s from %s where c_firstName='%s'",
                columnNames,
                tableName,
                sharedData.getCoBorrowersFirstName()
        );


        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);


        System.out.println(query);
        System.out.println(queryResult);

        Map<String, Object> map = queryResult.get(0);
        List<String> actualColumns = new ArrayList<>(map.keySet());

        Assert.assertEquals(expectedColumns, actualColumns);
    }
}
