package pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    private WebDriver driver;

    @FindBy(id = "firstname")
    private WebElement name;

    @FindBy(id = "lastname")
    private WebElement lastname;

    @FindBy(xpath = "//input[@id='email_address']")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "confirmation")
    private WebElement passwordConfirment;

    @FindBy(xpath = "//div[@class='one-col-wrapper']//div[7]")
    private WebElement rulesAgreement;

    @FindBy(id = "create-account")
    private WebElement btnCreateAccount;

    public SignUpPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AccountPage fillForm(String name, String lastname, String email, String password){
        this.name.sendKeys(name);
        this.lastname.sendKeys(lastname);
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        this.passwordConfirment.sendKeys(password);
        this.rulesAgreement.click();
        this.btnCreateAccount.click();
        return new AccountPage(driver);
    }
}
