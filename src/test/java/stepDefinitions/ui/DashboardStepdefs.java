package stepDefinitions.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.ConfigReader;
import utilities.Driver;

public class DashboardStepdefs {
    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        Driver.getDriver().findElement(By.name("email")).sendKeys("duothechtest@gmail.com", Keys.TAB);
        Driver.getDriver().findElement(By.name("password")).sendKeys("696XR3dfTbf9W", Keys.ENTER);
    }
    @When("the user navigates to the dashboard page")
    public void the_user_navigates_to_the_dashboard_page() {
        Assert.assertEquals("http://qa-duobank.us-east-2.elasticbeanstalk.com/dashboard.php", Driver.getDriver().getCurrentUrl());
    }
    @Then("the bank's logo should be displayed in the top left corner")
    public void the_bank_s_logo_should_be_displayed_in_the_top_left_corner() {

        Assert.assertFalse("Logo image is not displayed", Driver.getDriver().findElement(By.className("logo")).getAttribute("style").contains("display: none"));
    }


    @When("the user clicks on the Mortgage Application link")
    public void theUserClicksOnTheMortgageApplicationLink() {
        Driver.getDriver().findElement(By.cssSelector("a[href*='mortgage.php']")).click();

    }

    @Then("the user should be taken to a new page for applying for a new mortgage")
    public void theUserShouldBeTakenToANewPageForApplyingForANewMortgage() {
        Assert.assertEquals("http://qa-duobank.us-east-2.elasticbeanstalk.com/mortgage.php", Driver.getDriver().getCurrentUrl());
    }


    @When("the user navigates back to the dashboard page")
    public void theUserNavigatesBackToTheDashboardPage() throws InterruptedException {
        theUserClicksOnTheMortgageApplicationLink();
        theUserShouldBeTakenToANewPageForApplyingForANewMortgage();
        Driver.getDriver().navigate().back();
        Thread.sleep(2000);
        Assert.assertEquals("http://qa-duobank.us-east-2.elasticbeanstalk.com/dashboard.php", Driver.getDriver().getCurrentUrl());
    }


    @Then("the user's account information should be displayed in the top right corner")
    public void theUserSAccountInformationShouldBeDisplayedInTheTopRightCorner() {

        Assert.assertTrue("User's account information is not displayed", Driver.getDriver().findElement(By.className("user-name")).isDisplayed());

    }

    @And("clicking on the user's name or picture should display a dropdown menu")
    public void clickingOnTheUserSNameOrPictureShouldDisplayADropdownMenu() throws InterruptedException {
        Driver.getDriver().findElement(By.className("user-name")).click();
        Thread.sleep(1000);
        Assert.assertTrue("Dropdown menu is not displayed", Driver.getDriver().findElement(By.className("dropdown-item")).isDisplayed());
    }

}

