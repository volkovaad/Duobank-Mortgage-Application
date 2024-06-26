package pages;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class ExpensesPage {

    public ExpensesPage (){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//label[@for='expense1']")
    private WebElement rentCheckbox;
    @FindBy(xpath = "//label[@for='expense2']")
    private WebElement ownCheckbox;
    @FindBy(name = "monthly_rental_payment")
    private WebElement monthlyRentalPayment;

    @FindBy(name = "first_mortagage_total_payment")
    private WebElement monthlyMortgagePayment;

    @FindBy(partialLinkText = "Next")
    private WebElement nextButton;
    @FindBy(partialLinkText = "Previous")
    private WebElement previousButton;

    //div[@class='checkbox checkbox-danger checkbox-glow']//label['after'][1]

    @FindBy(id = "exampleInputEmail1")
    private WebElement emailLogin;
    @FindBy(partialLinkText = "Mortgage Application")
    private WebElement mortgageApplication;
    @FindBy(xpath = "//label[@for='realtor2']")
    private WebElement noRealtor;
    @FindBy(xpath="//label[@for='loanofficer2']")
    private WebElement noLoanOfficer;
    @FindBy(id="estimatedprice")
    private WebElement estimatedPrice;
    @FindBy(id="downpayment")
    private WebElement downPayment;



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


    public void open() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        emailLogin.sendKeys(ConfigReader.getProperty("username"), Keys.TAB,ConfigReader.getProperty("password"), Keys.TAB, Keys.ENTER );
        mortgageApplication.click();
        noRealtor.click();
        noLoanOfficer.click();
        estimatedPrice.sendKeys("777777", Keys.ENTER);
        downPayment.sendKeys("5000",Keys.ENTER);
        nextButton.click();
        coBorrowerNoCheckbox.click();
        firstName.sendKeys("Ann",Keys.ENTER);
        lastName.sendKeys("Taylor",Keys.ENTER);
        email.sendKeys(ConfigReader.getProperty("username"));
        Dob.sendKeys("03/30/2000");
        ssn.sendKeys("123456789");
        cellphone.sendKeys("1234567890");
        maritalStatus.click();
        WebDriver driver = Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement maritalStatusOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-results']/ul/li[contains(text(),'Married')]")));
        maritalStatusOption.click();
        privacyPolicyCheckbox.click();
        nextButton.click();
    }


    public void nextElement() {
        nextButton.click();
    }

    public void previousElement() {
        previousButton.click();
    }

    public boolean isNextPageDisplayed() {
        return nextButton.isDisplayed();
    }

    public boolean isPreviousPageDisplayed() {
        return previousButton.isDisplayed();
    }

    public void rentCheckbox() {
        rentCheckbox.click();
    }

    public void ownCheckbox() {
        ownCheckbox.click();
    }

    public void monthlyRentalPayment() {
        Faker faker = new Faker();
        double rentalPayment = faker.number().randomDouble(2, 500, 3000);
        this.monthlyRentalPayment.sendKeys(String.valueOf(rentalPayment));
    }

    public void monthlyMortgagePayment() {
        Faker faker = new Faker();
        double mortgagePayment = faker.number().randomDouble(2, 500, 3000);
        this.monthlyMortgagePayment.sendKeys(String.valueOf(mortgagePayment));
    }


}
