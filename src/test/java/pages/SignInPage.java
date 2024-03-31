package pages;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
@Data
public class SignInPage {

    public SignInPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@href='register.php']")
    private WebElement signUpButton;

    @FindBy(id = "exampleInputEmail1")
    private WebElement email;
    @FindBy(xpath = "//input[@id='exampleInputPassword1']")
    private WebElement password;
    @FindBy(xpath = "//button[@class='btn btn-primary glow w-100 position-relative']")
    private WebElement signInButton;

    public void signIn(String eMail, String pwd){

        email.sendKeys(eMail);
        password.sendKeys(pwd);
    }


    public void signinEmpty(){
        email.sendKeys(" ");
        password.sendKeys(" ");
        signInButton.click();
    }
}
