package pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "login[username]")
    private WebElement email;

    @FindBy(id = "login[password]")
    private WebElement password;

    @FindBy(name = "send")
    private WebElement btnSubmit;

    public LoginPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(this.driver, this);
    }

    public AccountPage fillLoginForm(String email, String pass){
        this.email.sendKeys(email);
        this.password.sendKeys(pass);
        this.btnSubmit.click();
        return new AccountPage(driver);
    }
}
