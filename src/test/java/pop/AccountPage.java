package pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {
    private WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement accountHeader;

    @FindBy(xpath = "//a[@class='header-top-bar__item'][contains(text(),'Wyloguj')]")
    private WebElement bntLogout;

    public AccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(this.driver, 10).until(ExpectedConditions.elementToBeClickable(bntLogout));
    }

    public String getAccountHeaderText(){
        return accountHeader.getText();
    }

    public LogoutPage logout(){
        bntLogout.click();
        return new LogoutPage(driver);
    }

}
