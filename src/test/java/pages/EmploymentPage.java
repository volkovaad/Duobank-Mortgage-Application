package pages;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;

import static utilities.SeleniumUtils.jsClick;

@Data
public class EmploymentPage {

    public EmploymentPage(){
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
    @FindBy(xpath = "//div[@class='borrowertotalmonthlyincome']")
    private WebElement borrowerTotalMonthlyIncome;
    @FindBy(xpath = "//a[@href='#next']")
    private WebElement next;
    @FindBy(xpath = "//a[@href='#previous']")
    private WebElement previous;


    public void checkbox(){
        jsClick(currentJobCheck);
    }

public void fillInEmploymentForm(String empName, String postn, String cit, String st, String st_date, String en_date){
    employerName.sendKeys(empName);
    position.sendKeys(postn);
    city.sendKeys(cit);
    Select dropdownCity = new Select(state);
    dropdownCity.selectByVisibleText(st);
    start_date.sendKeys(st_date);
    end_date.sendKeys(en_date);
}

    public void fillInGrossMonthlyForm(String gross,String over, String bon, String com, String div){
        grossMonthlyIncome.sendKeys(gross);
        monthlyOvertime.sendKeys(over);
        monthlyBonuses.sendKeys(bon);
        monthlyCommission.sendKeys(com);
        monthlyDividents.sendKeys(div);
    }
public void moveForward(){
        next.click();
}







}
