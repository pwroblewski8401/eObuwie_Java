package pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    private WebDriver driver;

    @FindBy(className = "product-view__sku")
    private WebElement productNumber;

    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public int getProductnumber(){
        return Integer.parseInt(productNumber.getText());
    }
}
