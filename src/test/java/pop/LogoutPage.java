package pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
    private WebDriver driver;

    @FindBy(className = "logout__title")
    private WebElement logoutHeaderInfo;

    public LogoutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getLogoutInfo(){
        return logoutHeaderInfo.getText();
    }
}
