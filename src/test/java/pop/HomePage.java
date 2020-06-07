package pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class HomePage {

    private WebDriver driver;

    @FindBy(xpath = "//button[@data-testid='permission-popup-accept']")
    private WebElement btnAccept;
    @FindBy(partialLinkText = "Zarejestr")
    private WebElement btnSignUp;
    @FindBy(partialLinkText = "Zalog")
    private WebElement loginButton;
    @FindBy(xpath = "//div[@class='header-content__search-wrapper']//input[@placeholder='Szukaj']")
    private WebElement searchInput;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignUpPage gotoSignUpPage(){
        btnSignUp.click();
        return new SignUpPage(driver);
    }

    public void skipAgreement(){
        if(btnAccept != null){
            Reporter.log("Agreement window is visible. Skipping!", true);
            btnAccept.click();
        }
    }

    public LoginPage gotoLoginPage(){
        loginButton.click();
        return new LoginPage(driver);
    }

    public SearchPage fillSearchInput(String search){
        searchInput.sendKeys(search);
        searchInput.submit();
        return new SearchPage(driver);

    }
}
