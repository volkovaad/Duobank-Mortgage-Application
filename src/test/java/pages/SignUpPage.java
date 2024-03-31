package pages;

import com.github.javafaker.Faker;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
@Data
public class SignUpPage {

    public SignUpPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "inputfirstname4")
    private WebElement firstName;
    @FindBy(id = "inputlastname4")
    private WebElement lastName;
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(id = "exampleInputPassword1")
    private WebElement password;
    @FindBy(name = "register")
    private WebElement registerButton;

    @FindBy(xpath = "//a[@href='index.php']")
    private WebElement signinbutton;

    @FindBy(id = "emailerror")
    private WebElement emailerror;


    public void signUpWithRandomData(){
        Faker faker=  new Faker();
        firstName.sendKeys(faker.name().firstName());
        lastName.sendKeys(faker.name().lastName());
        email.sendKeys(faker.internet().emailAddress());
        password.sendKeys("ANybhj5dgy");

        registerButton.click();
    }

    public void signUp(String first, String last, String eMail, String pwd){

        firstName.sendKeys(first);
        lastName.sendKeys(last);
        email.sendKeys(eMail);
        password.sendKeys(pwd);
        registerButton.click();
    }

}
