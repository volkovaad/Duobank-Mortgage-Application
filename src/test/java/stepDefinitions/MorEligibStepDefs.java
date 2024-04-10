package stepDefinitions;

import io.cucumber.java.an.E;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import pages.EmploymentPage;
import pages.ExpensesPage;
import pages.PersonalInfoPage;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import utilities.Driver;
import utilities.SeleniumUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utilities.SeleniumUtils.*;

public class MorEligibStepDefs {

    //Background
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

    //Scenario:   Checkbox
    @Given("The checkbox This is my current job should be unchecked by default")
    public void theCheckboxLabeledShouldBeUncheckedByDefault() {
        SoftAssertions softAssertions = new SoftAssertions();
        EmploymentPage employmentPage = new EmploymentPage();
        softAssertions.assertThat(employmentPage.getCurrentJobCheck().isSelected()).isFalse();
        softAssertions.assertAll();
    }

  //  Scenario: Provide valid Employment and Income Information
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
    @Then("The user should be able to move forward")
    public void theUserShouldBeAbleToMoveForward() {
        new EmploymentPage().moveForward();
    }

    // Scenario:  Required information is valid
    @Given("The user enters his employment information")
    public void the_user_enters_his_employment_information(Map <String,String> map) {
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

    @Given("Employer Name should be required")
    public void employer_name_should_be_required() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",new EmploymentPage().getEmployerName());
        Assert.assertTrue(isRequired);
    }
    @Given("Clear option should be available")
    public void clear_option_should_be_available() {
        EmploymentPage emp =   new EmploymentPage();
        jsClick(emp.getClearSection());
        Assert.assertTrue(emp.getButtonYestoClear().isDisplayed());
        jsClick(emp.getButtonYestoClear());
    }

    @Given("user is able to pick the correct state from")
    public void user_is_able_to_pick_the_correct_state(List<String> expectedStates) {
        expectedStates = new ArrayList<>(expectedStates);
        List<String> actualStates = SeleniumUtils.getElementsText(new EmploymentPage().getAllStates());
//        System.out.println(actualStates.size());
//        System.out.println(actualStates);
//        System.out.println(expectedStates.size());
//        System.out.println(expectedStates);
        Assert.assertEquals(expectedStates, actualStates);
        Assert.assertEquals(expectedStates.size(), actualStates.size());

    }

    @Then("user should be able to add another employer")
    public void user_should_be_able_to_add_another_employer() {
        Assert.assertTrue(new EmploymentPage().getAddEmployer().isEnabled());
    }

   // Scenario: Adding another employer
    @Given("The user enters valid  information for employer 1")
    public void the_user_entersvalidinformationforemployer1(Map <String,String> map) {
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
    @Then("user should be able to click on add employer button and fill in with valid information")
    public void userShouldBeAbleToClickOnAddEmployerButtonAndFillIn(Map <String,String> map) {
       EmploymentPage emp = new EmploymentPage();
        emp.getAddEmployer().click();
        jsClick(emp.getCurrentJob2());
        emp.fillInCurrentEmploymentForm(
                map.get("employerName"),
                map.get("position"),
                map.get("city"),
                map.get("state"),
                map.get("start_date")
        );
    }


    @And("Clear option should be available and warning popup should be displayed")
    public void clearOptionShouldBeAvailableAndWarningPopupShouldBeDisplayed() {
        EmploymentPage emp = new EmploymentPage();
        jsClick(emp.getClearSection2());
        Assert.assertTrue(emp.getButtonCanceltoClear().isDisplayed());
        jsClick(emp.getButtonCanceltoClear());
    }

    @And("user should be able to clear section2")
    public void userShouldBeAbleToClearSection2() {
        EmploymentPage emp = new EmploymentPage();
        jsClick(emp.getClearSection2());
        jsClick(emp.getButtonYestoClear());

        Assert.assertTrue(emp.getEmployerName2().getAttribute("value").isEmpty());
        Assert.assertTrue(emp.getPosition2().getAttribute("value").isEmpty());
        Assert.assertTrue(emp.getCity2().getAttribute("value").isEmpty());
        Assert.assertTrue(emp.getState2().getAttribute("value").isEmpty());
        Assert.assertTrue(emp.getStart_date2().getAttribute("value").isEmpty());

        Assert.assertFalse(emp.getEmployerName().getAttribute("value").isEmpty());
        Assert.assertFalse(emp.getPosition().getAttribute("value").isEmpty());
        Assert.assertFalse(emp.getCity().getAttribute("value").isEmpty());
        Assert.assertFalse(emp.getState().getAttribute("value").isEmpty());
        Assert.assertFalse(emp.getStart_date().getAttribute("value").isEmpty());
    }

    @Then("user fill out employer2 again")
    public void userFilloutEmployerAgain(Map <String,String> map) {
            EmploymentPage emp = new EmploymentPage();
//            emp.getAddEmployer().click();
//            jsClick(emp.getCurrentJob2());
            emp.fillInCurrentEmploymentForm(
                    map.get("employerName"),
                    map.get("position"),
                    map.get("city"),
                    map.get("state"),
                    map.get("start_date")
            );
    }

        @And("the user should be able to clear section1")
        public void theUserShouldBeAbleToClearSection1() {
            EmploymentPage emp = new EmploymentPage();
            jsClick(emp.getClearSection());
            jsClick(emp.getButtonYestoClear());

            Assert.assertTrue(emp.getEmployerName().getAttribute("value").isEmpty());
            Assert.assertTrue(emp.getPosition().getAttribute("value").isEmpty());
            Assert.assertTrue(emp.getCity().getAttribute("value").isEmpty());
            Assert.assertTrue(emp.getState().getAttribute("value").isEmpty());
            Assert.assertTrue(emp.getStart_date().getAttribute("value").isEmpty());

            Assert.assertFalse(emp.getEmployerName2().getAttribute("value").isEmpty());
            Assert.assertFalse(emp.getPosition2().getAttribute("value").isEmpty());
            Assert.assertFalse(emp.getCity2().getAttribute("value").isEmpty());
            Assert.assertFalse(emp.getState2().getAttribute("value").isEmpty());
            Assert.assertFalse(emp.getStart_date2().getAttribute("value").isEmpty());
        }

    @Then("Remove option should be available")
    public void removeOptionShouldBeAvailable() {
        Assert.assertTrue(new EmploymentPage().getRemoveSection().isEnabled());
    }

    // unsuccessful Scenario Outline: Provide invalid Employment Information
    @When("The user enters invalid {string}, {string}, {string},{string},{string}")
    public void the_user_enters_invalid(String string, String string2, String string3, String string5, String string6) {
        EmploymentPage emp = new EmploymentPage();
       jsClick(emp.getCurrentJobCheck());
        emp.getEmployerName().sendKeys(string);
        emp.getPosition().sendKeys(string2);
        emp.getCity().sendKeys(string3);
        emp.getStart_date().sendKeys(string5);
        emp.getEnd_date().sendKeys(string6);
        emp.getGrossMonthlyIncome().sendKeys("5677");
    }
    @Then("the user should see an error message or not being able to continue application")
    public void the_user_should_see_an_error_message_or_not_being_able_to_continue_application() throws InterruptedException {
        EmploymentPage emp = new EmploymentPage();
        emp.getNext().click();
        Thread.sleep(2000);
          Assert.assertTrue(Driver.getDriver().getPageSource().contains("This field is required.") || Driver.getDriver().getPageSource().contains("Please enter a value less than or equal to"));
    }

//Scenario Outline: The user enters their gross monthly income

    Double gross;
    Double over;
    Double bonus;
    Double commis;
    Double divid;
    @Given("User enters valid {double}, {double}, {double}, {double} and {double}")
    public void user_enters_valid_and(Double gross, Double over, Double bonus, Double commis, Double divid) {
        EmploymentPage empl = new EmploymentPage();
        empl.getGrossMonthlyIncome().sendKeys(String.valueOf(gross));
        empl.getMonthlyOvertime().sendKeys(String.valueOf(over));
        empl.getMonthlyBonuses().sendKeys(String.valueOf(bonus));
        empl.getMonthlyCommission().sendKeys(String.valueOf(commis));
        empl.getMonthlyDividents().sendKeys(String.valueOf(divid));
        this.gross = gross;
        this.over = over;
        this.bonus = bonus;
        this.commis = commis;
        this.divid = divid;

    }
    @Given("gross income should be required field")
    public void gross_income_should_be_required_field() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",new EmploymentPage().getGrossMonthlyIncome());
        Assert.assertTrue(isRequired);

    }
    Double total;
    @Then("User should have total left")
    public void user_should_have_left() {
        total = this.gross + this.over + this.bonus + this.commis + this.divid;

        EmploymentPage empl = new EmploymentPage();
        Double borrowedDisplayed = Double.valueOf(empl.getBorrowerTotalMonthlyIncome().getText().replaceAll("[^0-9.]", ""));
        Assert.assertEquals(total, borrowedDisplayed, 0.01);

    }

//unsuccessful Scenario: Input validation
    @Given("all fields should accept only numeric input only 12 characters")
    public void allFieldsShouldAcceptOnlyNumericInputOnlyCharacters() {
        String pattern = "^(\\d{0,10}(\\.\\d{0,2})?)?$";
        EmploymentPage empl = new EmploymentPage();
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(empl.getGrossMonthlyIncome().getAttribute("type")).isEqualTo("number");
        softAssertions.assertThat(empl.getMonthlyOvertime().getAttribute("type")).isEqualTo("number");
        softAssertions.assertThat(empl.getMonthlyBonuses().getAttribute("type")).isEqualTo("number");
        softAssertions.assertThat(empl.getMonthlyCommission().getAttribute("type")).isEqualTo("number");
        softAssertions.assertThat(empl.getMonthlyDividents().getAttribute("type")).isEqualTo("number");

        softAssertions.assertThat(empl.getGrossMonthlyIncome().getAttribute("pattern")).isEqualTo(pattern);
        softAssertions.assertThat(empl.getMonthlyOvertime().getAttribute("pattern")).isEqualTo(pattern);
        softAssertions.assertThat(empl.getMonthlyBonuses().getAttribute("pattern")).isEqualTo(pattern);
        softAssertions.assertThat(empl.getMonthlyCommission().getAttribute("pattern")).isEqualTo(pattern);
        softAssertions.assertThat(empl.getMonthlyDividents().getAttribute("pattern")).isEqualTo(pattern);
        softAssertions.assertAll();

    }

    // Scenario: Provide Additional Gross Monthly Income

    @Given("The user is able to select  income source from dropdown and able to Add another income")
    public void theUserIsAbleToSelectIncomeSourceFromDropdown() {
        EmploymentPage emp = new EmploymentPage();

        SeleniumUtils.scrollToElement(emp.getIncomeSource1drop());
        SeleniumUtils.waitForClickablility(emp.getIncomeSource1drop(), 2000);
        Select drop1 = new Select(emp.getIncomeSource1drop());
        drop1.selectByVisibleText("Interest and Dividends");
        emp.getAmount1().sendKeys("2000");

        Select drop2 = new Select(emp.getIncomeSource2drop());
        drop2.selectByVisibleText("Royalty Payments");
        emp.getAmount2().sendKeys("200");

        Select drop3 = new Select(emp.getIncomeSource3drop());
        drop3.selectByVisibleText("Alimony/Child Support");
        emp.getAmount3().sendKeys("500");

    }

    @Then("dropdown should contain")
    public void dropdownShouldContain(List<String> expecteddrop) {
        expecteddrop = new ArrayList<>(expecteddrop);
        EmploymentPage emp = new EmploymentPage();
        List<String> actual1 = SeleniumUtils.getElementsText(emp.getIncomeSource1());
        List<String> actual2 = SeleniumUtils.getElementsText(emp.getIncomeSource2());
        List<String> actual3 = SeleniumUtils.getElementsText(emp.getIncomeSource3());

        Assert.assertEquals(expecteddrop, actual1);
        Assert.assertEquals(expecteddrop, actual2);
        Assert.assertEquals(expecteddrop, actual3);
    }

// Scenario: Navigate Using Next Buttons
    @Given("The user filled in all required fields in the previous sections")
    public void the_user_filled_in_all_required_fields_in_the_previous_sections(Map <String,String> map) {
        new EmploymentPage().fillInRequiredEmploymentForm(
                map.get("employerName"),
                map.get("start_date"),
                map.get("gross_income")
        );
}
    @When("The user click on the next button")
    public void the_user_click_on_the_button() {
        new EmploymentPage().getNext().click();
    }
    @Then("The user should navigate to the next section")
    public void the_user_should_navigate_to_the_next_section() throws InterruptedException {
        Thread.sleep(2000);
            Assert.assertTrue(Driver.getDriver().getPageSource().contains("PreApproval Inquiry"));
    }


    //  Scenario: Navigate using Previous Button
    @Given("The user is on employment page")
    public void theUserIsOnEmploymentPage() {
        SeleniumUtils.waitForPageToLoad(2000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Borrower Employment Information"));
    }

    @Then("he should be able to navigate back")
    public void heShouldBeAbleToNavigateBack() {
        new EmploymentPage().getPrevious().click();
        SeleniumUtils.waitForPageToLoad(2000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Current Monthly Housing Expenses"));
    }

   //   Scenario Outline: Unable to navigate using Next Button

    @Given("The user not filled in {string} or {string} or {string}  in the current section")
    public void the_user_not_filled_in_or_or_in_the_current_section(String string, String string2, String string3) {
        EmploymentPage emp = new EmploymentPage();
        emp.getEmployerName().sendKeys(string);
        emp.getStart_date().sendKeys(string2);
        emp.getGrossMonthlyIncome().sendKeys(string3);

    }
    @When("The user click on the next button1")
    public void the_user_click_on_the_button1() {
        new EmploymentPage().getNext().click();
    }

    @Then("The user should see an error message indicating the required field that needs to be filled in")
    public void the_user_should_see_an_error_message_indicating_the_required_field_that_needs_to_be_filled_in() {
        SeleniumUtils.waitForPageToLoad(2000);
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("This field is required"));
    }


}

