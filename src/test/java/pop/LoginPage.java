package pop;

import org.openqa.selenium.By;
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

    private WebElement wrongDataInfo;

    public LoginPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillLoginForm(String email, String pass){
        this.email.sendKeys(email);
        this.password.sendKeys(pass);
    }

    public AccountPage submitBtnClick(){
        this.btnSubmit.click();
        return new AccountPage(driver);
    }

    public String submitWrongDataBtnClick(){
        this.btnSubmit.click();
        if(checkWrongDataPopupExist()){
            return wrongDataInfo.getText();
        }
        return null;
    }

    private boolean checkWrongDataPopupExist(){
        try{
            wrongDataInfo = driver.findElement(By.xpath("//span[contains(text(),'ytkownika lub has')]"));
            return true;
        } catch (Exception ex){
            return false;
        }
    }
}
