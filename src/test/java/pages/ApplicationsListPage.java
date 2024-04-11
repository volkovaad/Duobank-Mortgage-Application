package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

public class ApplicationsListPage {

    public ApplicationsListPage (){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(partialLinkText = "Application List")
    private WebElement ApplicationList;

    @FindBy(xpath = "//select[@name='DataTables_Table_0_length']")
    private List<WebElement> DropDown;
    //  //select[@name='DataTables_Table_0_length']//option[@value='10']

//    @FindBy(xpath = "//select[@name='DataTables_Table_0_length']")
//    private WebElement DropDown25;
//
//    @FindBy(xpath = "//select[@name='DataTables_Table_0_length']")
//    private WebElement DropDown50;
//
//    @FindBy(xpath = "//select[@name='DataTables_Table_0_length']")
//    private WebElement DropDown100;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;

    @FindBy(partialLinkText = "LOAN ID")
    private WebElement loanID;

    @FindBy(partialLinkText = "BORROWER NAME")
    private WebElement borrowerName;

    @FindBy(partialLinkText = "LOAN AMOUNT")
    private WebElement loanAmount;

    @FindBy(id = "exampleInputEmail1")
    private WebElement emailLogin;

    @FindBy(xpath = "(//a[@class='btn btn-info'])[1]")
    private WebElement viewDetails;



    public void open() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        emailLogin.sendKeys(ConfigReader.getProperty("username"), Keys.TAB, ConfigReader.getProperty("password"), Keys.TAB, Keys.ENTER);
        ApplicationList.click();

    }
//    public void dropdownEntry(int entriesPerPage) {
//
//        Select dropdown = new Select(DropDown);
//        switch (entriesPerPage) {
//            case 10:
//                dropdown.selectByValue("10");
//                break;
//            case 25:
//                dropdown.selectByValue("25");
//                break;
//            case 50:
//                dropdown.selectByValue("50");
//                break;
//            case 100:
//                dropdown.selectByValue("100");
//                break;
//
//        }

public void clickViewDetails (){

        viewDetails.click();


}






}







