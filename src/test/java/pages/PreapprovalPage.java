package pages;

import com.github.javafaker.Faker;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

import static utilities.SeleniumUtils.jsClick;

@Data
public class PreapprovalPage {
    public PreapprovalPage() {PageFactory.initElements(Driver.getDriver(), this);}


    @FindBy(id = "exampleInputEmail1")
    private WebElement emailLogin;
    @FindBy(partialLinkText = "Mortgage Application")
    private WebElement mortgageApplication;
    @FindBy(xpath = "//label[@for='realtor1']")
    private WebElement yesRealtor;
    @FindBy(xpath = "//label[@for='realtor2']")
    private WebElement noRealtor;
    @FindBy(name = "realtor_info")
    private WebElement realtorInfo;
    @FindBy(xpath="//label[@for='loanofficer1']")
    private WebElement yesLoanOfficer;
    @FindBy(xpath = "//select[@data-select2-id='1']")
    private WebElement purposeLoan;
    @FindBy(id="estimatedprice")
    private WebElement estimatedPrice;
    @FindBy(id="downpayment")
    private WebElement downPayment;
    @FindBy(name = "add_fund_available")
    private WebElement findAvailable;

    @FindBy(partialLinkText = "Next")
    private WebElement nextButton;


    @FindBy(xpath = "(//span[@class='d-block'])[2]")
    private WebElement personalInformation;
    @FindBy(xpath = "//label[@for='coborrower1']")
    private WebElement coBorrowerYesCheckbox;
    @FindBy(xpath = "//label[@for='coborrower2']")
    private WebElement coBorrowerNoCheckbox;
    @FindBy(xpath = "//div[@class='co-borrower']")
    private WebElement coBorrowerSection;
    @FindBy(name = "b_firstName")
    private WebElement firstName;
    @FindBy(id = "b_middleName")
    private WebElement middleName;
    @FindBy(id = "b_lastName")
    private WebElement lastName;
    @FindBy(id = "select2-b_suffix-container")
    private WebElement suffix;
    @FindBy(id = "b_email")
    private WebElement email;
    @FindBy(id = "b_dob")
    private WebElement Dob;
    @FindBy(id = "b_ssn")
    private WebElement ssn;
    @FindBy(xpath = "//span[@id='select2-b_marital-container']")
    private WebElement maritalStatus;
    @FindBy(id = "b_cell")
    private WebElement cellphone;
    @FindBy(id = "b_home")
    private WebElement homePhone;
    @FindBy(xpath= "//label[@for='privacypolicy']")
    private WebElement privacyPolicyCheckbox;


    @FindBy(id = "monthlyrentalpayment")
    private WebElement monthlyRent;


    @FindBy(id = "employername1")
    private WebElement employerName;
    @FindBy(id = "start_date1")
    private WebElement startDate;
    @FindBy(id = "grossmonthlyincome")
    private WebElement grossMonthlyIncome;

    @FindBy(name = "eConsent_declarer_FirstName")
    private WebElement nameEConsent;
    @FindBy(id = "eConsentdeclarerEmail")
    private WebElement emailConsent;

    @FindBy(linkText = "Save")
    private WebElement saveButton;




    public void preapproval(String realtor, String estimPrice, String downP, String fundAv ) throws InterruptedException{
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        emailLogin.sendKeys(ConfigReader.getProperty("username"), Keys.TAB, ConfigReader.getProperty("password"), Keys.TAB, Keys.ENTER);
        mortgageApplication.click();
        yesRealtor.click();
        realtorInfo.sendKeys(realtor);
        yesLoanOfficer.click();
        estimatedPrice.sendKeys(estimPrice, Keys.ENTER);
        downPayment.sendKeys(downP,Keys.ENTER);
        findAvailable.sendKeys(fundAv);
        nextButton.click();

        jsClick(this.coBorrowerNoCheckbox);
        // this.coBorrowerNoCheckbox.click();
        this.firstName.sendKeys("Ann",Keys.ENTER);
        this.lastName.sendKeys("Taylor",Keys.ENTER);
        this.email.sendKeys(ConfigReader.getProperty("username"));
        this.Dob.sendKeys("03/30/2000");
        this.ssn.sendKeys("123456789");
        this.cellphone.sendKeys("1234567890");
        this.maritalStatus.click();
        WebDriver driver = Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement maritalStatusOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-results']/ul/li[contains(text(),'Married')]")));
        maritalStatusOption.click();
        privacyPolicyCheckbox.click();
        nextButton.click();

        this.monthlyRent.sendKeys("12345");
        nextButton.click();

        this.employerName.sendKeys("abcd");
        this.startDate.sendKeys("20240409");
        this.grossMonthlyIncome.sendKeys("12345");
        nextButton.click();
        Thread.sleep(2000);
        nextButton.click();


    }



    public void econsentInfo(){
        Faker faker = new Faker();
        nameEConsent.sendKeys(faker.name().firstName(), Keys.TAB, faker.name().lastName(), Keys.TAB, faker.internet().emailAddress());
    }




}
