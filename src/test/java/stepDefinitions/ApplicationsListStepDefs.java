package stepDefinitions;
import com.github.javafaker.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.sl.In;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import pages.ApplicationsListPage;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationsListStepDefs {

    ApplicationsListPage applicationsListPage;

    @Given("the user is on the  Application List page")
    public void theUserIsOnTheApplicationListPage() {

        applicationsListPage = new ApplicationsListPage();
        applicationsListPage.open();

    }


    @When("the user enters {string} in the search field")
    public void userEntersSearchTerm(String searchTerm) {



    }

    @Then("the list of applications should display matching entries")
    public void verifyMatchingEntries() throws InterruptedException {

        Thread.sleep(2000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Ann"));

    }

    @Then("each entry should display loan id, borrower name, loan amount, and loan details")
    public void verifyDisplayedData() throws InterruptedException{

        Thread.sleep(2000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("10"));
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Bob"));
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("123000.00"));

    }

    @When("the user selects to view details of a specific application")
    public void userSelectsViewDetails() {

        applicationsListPage.clickViewDetails();

    }


    @Then("the details page should display all information entered for that application")
    public void verifyDetailsPageContent() throws InterruptedException{

        Thread.sleep(2000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("duothechtest@gmail.com"));
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Married"));
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Pasadena"));
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Google"));

    }


    @When("the user selects to show <entriesPerPage> entries per page")
    public void theUserSelectsToShowEntriesPerPageEntriesPerPage() {
    }
}
