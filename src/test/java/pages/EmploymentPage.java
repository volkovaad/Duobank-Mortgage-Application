package pages;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.util.ArrayList;
import java.util.List;

import static utilities.SeleniumUtils.jsClick;

@Data
public class EmploymentPage {

    public EmploymentPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "currentjob1")
    private WebElement currentJobCheck;
    @FindBy(id = "employername1")
    private WebElement employerName;
    @FindBy(id = "position1")
    private WebElement position;
    @FindBy(id = "city")
    private WebElement city;
    @FindBy(id = "state1")
    private WebElement state;
    @FindBy(id = "start_date1")
    private WebElement start_date;
    @FindBy(id = "end_date1")
    private WebElement end_date;
    @FindBy(id = "currentjob2")
    private WebElement currentJob2;
    @FindBy(id = "employername2")
    private WebElement employerName2;
    @FindBy(id = "position2")
    private WebElement position2;
    @FindBy(id = "city2")
    private WebElement city2;
    @FindBy(id = "state2")
    private WebElement state2;
    @FindBy(id = "start_date2")
    private WebElement start_date2;
    @FindBy(id = "clear2")
    private WebElement clearSection2;
    @FindBy(id = "clear1")
    private WebElement clearSection;
    @FindBy(id = "addemployer")
    private WebElement addEmployer;
    @FindBy(id = "remove2")
    private WebElement removeSection;
    @FindBy(id = "grossmonthlyincome")
    private WebElement grossMonthlyIncome;
    @FindBy(id = "monthlyovertime")
    private WebElement monthlyOvertime;
    @FindBy(id = "monthlybonuses")
    private WebElement monthlyBonuses;
    @FindBy(id = "monthlycommission")
    private WebElement monthlyCommission;
    @FindBy(id = "monthlydividents")
    private WebElement monthlyDividents;
    @FindBy(id = "incomesource1")
    private WebElement incomeSource1drop;
    @FindBy(id = "amount1")
    private WebElement amount1;
    @FindBy(id = "incomesource2")
    private WebElement incomeSource2drop;
    @FindBy(id = "amount2")
    private WebElement amount2;
    @FindBy(id = "incomesource3")
    private WebElement incomeSource3drop;
    @FindBy(id = "amount3")
    private WebElement amount3;

    @FindBy(xpath = "//button[@class='swal2-confirm btn btn-warning']")
    private WebElement buttonYestoClear;
    @FindBy(xpath = "//button[@class='swal2-cancel btn btn-danger ml-1']")
    private WebElement buttonCanceltoClear;
    @FindBy(xpath = "//div[@class='borrowertotalmonthlyincome']")
    private WebElement borrowerTotalMonthlyIncome;
    @FindBy(xpath = "//a[@href='#next']")
    private WebElement next;
    @FindBy(xpath = "//a[@href='#previous']")
    private WebElement previous;

    @FindBy(xpath = "//select[@name='state[]']//option")
    private List<WebElement> allStates;

    @FindBy(xpath = "//select[@name='income_source[]']//option")
    private List<WebElement> incomeSource1;
    @FindBy(xpath = "//select[@name='income_source[]2']//option")
    private List<WebElement> incomeSource2;
    @FindBy(xpath = "//select[@name='income_source[]3']//option")
    private List<WebElement> incomeSource3;

    public void checkbox() {
        jsClick(currentJobCheck);
    }

    public void fillInEmploymentForm(String empName, String postn, String cit, String st, String st_date, String en_date) {
        employerName.sendKeys(empName);
        position.sendKeys(postn);
        city.sendKeys(cit);
        Select dropdownCity = new Select(state);
        dropdownCity.selectByVisibleText(st);
        start_date.sendKeys(st_date);
        end_date.sendKeys(en_date);
    }

    public void fillInGrossMonthlyForm(String gross, String over, String bon, String com, String div) {
        grossMonthlyIncome.sendKeys(gross);
        monthlyOvertime.sendKeys(over);
        monthlyBonuses.sendKeys(bon);
        monthlyCommission.sendKeys(com);
        monthlyDividents.sendKeys(div);
    }

    public void moveForward() {
        next.click();
    }

    public void fillInCurrentEmploymentForm(String empName, String postn, String cit, String st, String st_date) {
        employerName2.sendKeys(empName);
        position2.sendKeys(postn);
        city2.sendKeys(cit);
        Select dropdownCity = new Select(state2);
        dropdownCity.selectByVisibleText(st);
        start_date2.sendKeys(st_date);

    }

    public void fillInRequiredEmploymentForm(String empName, String st_date, String gross) {
        employerName.sendKeys(empName);
        start_date.sendKeys(st_date);
        grossMonthlyIncome.sendKeys(gross);

    }
}