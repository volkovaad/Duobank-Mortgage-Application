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
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;


public class PersonalInfoPage{

    public PersonalInfoPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

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
    public WebElement middleName;
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

    @FindBy(id = "c_firstName")
    private WebElement coBorrowerFirstName;
    @FindBy(id = "c_middleName")
    private WebElement coBorrowerMiddleName;
    @FindBy(id = "c_lastName")
    private WebElement coBorrowerLastName;

    @FindBy(id = "c_email")
    private WebElement coBorrowerEmail;
    @FindBy(id = "c_dob")
    private WebElement coBorrowerDob;
    @FindBy(id = "c_ssn")
    private WebElement coBorrowerSsn;
    @FindBy(id = "select2-c_marital-container")
    private WebElement coBorrowerMaritalStatus;

    @FindBy(id = "c_cell")
    private WebElement coBorrowerCellphone;
    @FindBy(id = "c_home")
    private WebElement coBorrowerHomePhone;
    @FindBy(partialLinkText = "Next")
    private WebElement nextButton;
    @FindBy(partialLinkText = "Previous")
    private WebElement previousButton;

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
    public WebElement downPayment;


    public void open() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        emailLogin.sendKeys(ConfigReader.getProperty("username"), Keys.TAB,ConfigReader.getProperty("password"), Keys.TAB, Keys.ENTER );
        mortgageApplication.click();
        noRealtor.click();
        noLoanOfficer.click();
        estimatedPrice.sendKeys("777777", Keys.ENTER);
        downPayment.sendKeys("5000",Keys.ENTER);
        nextButton.click();
    }



    public void selectCoBorrowerYes() {
        coBorrowerYesCheckbox.click();

    }

    public void selectCoBorrowerNo() {
        coBorrowerNoCheckbox.click();
    }

    public boolean isCoBorrowerSectionDisplayed() {
        return coBorrowerSection.isDisplayed();
    }
    public void enterBorrowersInfo() {
        Faker faker=  new Faker();
        firstName.sendKeys(faker.name().firstName());
        middleName.sendKeys(faker.name().nameWithMiddle());
        lastName.sendKeys(faker.name().lastName());

        email.sendKeys(faker.internet().emailAddress());
        Dob.sendKeys(faker.date().birthday().toString());
        ssn.sendKeys(faker.idNumber().valid());
        maritalStatus.click();

        WebDriver driver = Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement maritalStatusOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-results']/ul/li[contains(text(),'Married')]")));
        maritalStatusOption.click();


        cellphone.sendKeys(faker.phoneNumber().cellPhone());
        homePhone.sendKeys(faker.phoneNumber().phoneNumber());
    }




    public void acceptPrivacyPolicy() {
        privacyPolicyCheckbox.click();
    }

    public void enterCoBorrowersInfo() {
        Faker faker=  new Faker();
        coBorrowerFirstName.sendKeys(faker.name().firstName());
        coBorrowerMiddleName.sendKeys(faker.name().nameWithMiddle());
        coBorrowerLastName.sendKeys(faker.name().lastName());

        coBorrowerEmail.sendKeys(faker.internet().emailAddress());
        coBorrowerDob.sendKeys(faker.date().birthday().toString());
        coBorrowerSsn.sendKeys(faker.idNumber().valid());
        coBorrowerMaritalStatus.click();
        WebDriver driver = Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement maritalStatusOption2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-results']/ul/li[contains(text(),'Single')]")));
        maritalStatusOption2.click();

        this.coBorrowerCellphone.sendKeys(faker.phoneNumber().cellPhone());
        this.coBorrowerHomePhone.sendKeys(faker.phoneNumber().phoneNumber());
    }

    public void submitForm() {
        nextButton.click();
    }

    public boolean isNextPageDisplayed() {

        return nextButton.isDisplayed();
    }



}


