package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

import static utilities.SeleniumUtils.jsClick;

public class SummaryPage {

    public SummaryPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "eConsent_declarer_FirstName")
    private WebElement firstName;
    @FindBy(id = "eConsentdeclarerEmail")
    private WebElement lastName;
    @FindBy(id = "eConsentdeclarerEmail")
    private WebElement email;
    @FindBy(id = "agree")
    private WebElement agree;
    @FindBy(id = "dontagree")
    private WebElement doNotAgree;
    @FindBy(xpath = "//a[@href='#next']")
    private WebElement next;
    @FindBy(xpath = "//a[@href='#previous']")
    private WebElement previous;
    @FindBy(id = "PreApprovalEdit")
    private WebElement editPreApproval;
    @FindBy(id = "PersonalnformationEdit")
    private WebElement editPersonalDetails;
    @FindBy(id = "ExpenseEdit")
    private WebElement editExpenses;
    @FindBy(id = "EmploymentIncomeEdit")
    private WebElement editEmploymentAndIncome;
    @FindBy(id = "OrderCreditEdit")
    private WebElement editOrderCredit;
    @FindBy(xpath = "(//a[@id='eConsentEdit'])[1]")
    private WebElement editEConsent;
    @FindBy(xpath = "//a[@href='#finish']")
    private WebElement save;
    @FindBy(xpath = "//label[@for='expense2']")
    private WebElement ownCheckbox;


    public void open() {
        PersonalInfoPage personalInfo = new PersonalInfoPage();
        personalInfo.open();
        personalInfo.enterBorrowersInfo();
        personalInfo.acceptPrivacyPolicy();
        personalInfo.submitForm();
        SeleniumUtils.waitForPageToLoad(2000);

        ExpensesPage expenses = new ExpensesPage();
        expenses.monthlyRentalPayment();
        expenses.nextElement();
        SeleniumUtils.waitForPageToLoad(2000);
        EmploymentPage employmentPage = new EmploymentPage();
        employmentPage.checkbox();
        employmentPage.moveForward();

        SeleniumUtils.waitForPageToLoad(2000);
        CreditReportPage creditReportPage = new CreditReportPage();
        creditReportPage.clickNoButton();
        creditReportPage.nextButton();

        SeleniumUtils.waitForPageToLoad(2000);
        firstName.sendKeys("Ann",Keys.TAB,"Taylor",Keys.TAB, ConfigReader.getProperty("username"),Keys.ENTER);
        jsClick(agree);
        next.click();
    }
    public void agreeCheckbox() {
        jsClick(agree);
    }

    public void doNotAgreeCheckbox() {
        jsClick(doNotAgree);
    }

    public void ownCheckbox() {
        ExpensesPage expensesPage = new ExpensesPage();
        jsClick(ownCheckbox);
    }
    public void editPreApproval() {
    }
    public void editPersonalDetails() {
        editPersonalDetails.click();
    }
    public void editExpenses() {
        editExpenses.click();
    }
    public void editEmploymentAndIncome() {
        editEmploymentAndIncome.click();
    }
    public void editOrderCredit() {
        editOrderCredit.click();
    }
    public void editEConsent() {
        editEConsent.click();
    }

    public void saveApplication() {
        save.click();
    }
}
