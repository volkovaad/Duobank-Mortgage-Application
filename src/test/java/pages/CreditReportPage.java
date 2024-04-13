package pages;

//import jdk.internal.access.JavaIOFileDescriptorAccess;
import lombok.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;


public class CreditReportPage {


    public CreditReportPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//label[@for='creditreport1']")
    private WebElement yesButtonLabel;

    @FindBy(xpath = "//label[@for='creditreport2']")
    private WebElement noButtonLabel;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(id = "thirdPartyWebsiteLink")
    private WebElement thirdPartyWebsiteLink;

    @FindBy(name = "fullname")
    private WebElement fullName;

    @FindBy(name = "birthdate")
    private WebElement birthDate;

    @FindBy(name = "ssn")
    private WebElement SSN;

    @FindBy(name = "address")
    private WebElement currentAddress;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement orderCredit;

    @FindBy(xpath = "//a[contains(text(),'Next')]")
    private WebElement nextButton;



    public void navigateToPreapprovalInquiryPage() {
        WebDriver driver = Driver.getDriver();
        String url = "http://qa-duobank.us-east-2.elasticbeanstalk.com/mortgage.php";
        driver.get(url);

    }

    public void clickYesButton() {
        yesButtonLabel.click();

    }
    public void clickNoButton() {
        noButtonLabel.click();
    }
    public void nextButton() {
        nextButton.click();
    }

    public void submitApplication() {
        submitButton.click();
    }

    public boolean isRedirectedToThirdPartyWebsite() {
        return Driver.getDriver().getCurrentUrl().equals("URL_of_third_party_website");
    }

    public void orderCreditReport() {
        this.fullName.sendKeys("Ann",Keys.TAB);
        this.birthDate.sendKeys("04/01/1990",Keys.TAB);
        this.SSN.sendKeys("123-45-6789",Keys.TAB);
        this.currentAddress.sendKeys("123 Main street",Keys.TAB, Keys.ENTER);
    }
}
