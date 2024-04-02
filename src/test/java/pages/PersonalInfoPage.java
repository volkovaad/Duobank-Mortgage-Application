package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;
import utilities.Driver;


public class PersonalInfoPage{
    public PersonalInfoPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//span[@class='d-block'])[2]")
    private WebElement personalInformation;
    @FindBy(id = "coborrower1")
    private WebElement coBorrowerYesCheckbox;
    @FindBy(id = "coborrower2")
    private WebElement coBorrowerNoCheckbox;
    @FindBy(xpath = "//div[@class='co-borrower']")
    private WebElement coBorrowerSection;
    @FindBy(id = "b_firstName")
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
    @FindBy(id = "select2-b_marital-container")
    private WebElement maritalStatus;
    @FindBy(id = "b_cell")
    private WebElement cellphone;
    @FindBy(id = "b_home")
    private WebElement homePhone;
    @FindBy(id = "privacypolicy")
    private WebElement privacyPolicyCheckbox;

    @FindBy(id = "c_firstName")
    private WebElement coBorrowerFirstName;
    @FindBy(id = "c_middleName")
    private WebElement coBorrowerMiddleName;
    @FindBy(id = "c_lastName")
    private WebElement coBorrowerLastName;
    @FindBy(id = "select2-c_suffix-container")
    private WebElement coBorrowerSuffix;
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
    public void open() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        this.emailLogin.sendKeys("duothechtest@gmail.com", Keys.TAB,"696XR3dfTbf9W", Keys.TAB, Keys.ENTER );
        this.mortgageApplication.click();
        this.personalInformation.click();
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

        this.firstName.sendKeys("Ann");
        this.middleName.sendKeys("John");
        this.lastName.sendKeys("Taylor");

        Select suffixDropdown = new Select(this.suffix);
        suffixDropdown.selectByVisibleText("Jr.");

        this.coBorrowerEmail.sendKeys(ConfigReader.getProperty("username"));
        this.coBorrowerDob.sendKeys("03/30/2000");
        this.coBorrowerSsn.sendKeys("123456789");

        Select maritalStatusDropdown = new Select(this.coBorrowerMaritalStatus);
        maritalStatusDropdown.selectByVisibleText("Married");

        this.coBorrowerCellphone.sendKeys("1234567890");
        this.coBorrowerHomePhone.sendKeys("1234567890");
    }



    public void acceptPrivacyPolicy() {
        privacyPolicyCheckbox.click();
    }

    public void enterCoBorrowersInfo() {
        Faker faker=  new Faker();
        this.coBorrowerFirstName.sendKeys(faker.name().firstName());
        this.coBorrowerMiddleName.sendKeys(faker.name().nameWithMiddle());
        this.coBorrowerLastName.sendKeys(faker.name().lastName());

        Select suffixDropdown = new Select(this.coBorrowerSuffix);
        suffixDropdown.selectByVisibleText("Jr.");

        this.coBorrowerEmail.sendKeys(faker.internet().emailAddress());
        this.coBorrowerDob.sendKeys(faker.date().birthday().toString());
        this.coBorrowerSsn.sendKeys(faker.idNumber().valid());

        Select maritalStatusDropdown = new Select(this.coBorrowerMaritalStatus);
        maritalStatusDropdown.selectByVisibleText("Married");

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


