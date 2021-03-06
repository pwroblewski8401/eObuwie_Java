package pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductPage {
    private WebDriver driver;

    @FindBy(className = "product-view__sku")
    private WebElement productNumber;

    private WebElement sizeSelector;

    @FindBys({
            @FindBy(xpath = "//ul[@class=\"dropdown-menu\"]/li")
    })
    private List<WebElement> sizes;

    @FindBy(xpath = "//button[@data-testid=\"product-add-to-cart-button\"]")
    private WebElement addToCartButton;

    private WebElement popupCloseButton;

    @FindBy(className = "product-status__label")
    private WebElement productStatus;

    @FindBy(className = "product-left__name-second")
    private WebElement productName;

    @FindBys({
            @FindBy(xpath = "//div[@class='product-details']//ul/li")
    })
    List<WebElement> productInformation;

    private WebElement price;


    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public int getProductnumber(){
        return Integer.parseInt(productNumber.getText());
    }

    public boolean ifSizeSelectorIsPresent(){
        try{
            sizeSelector = driver.findElement(By.className("form-control"));
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public void setSize(int index){
        sizeSelector.click();
        sizes.get(index).click();
    }

    public void addToCart(){
        addToCartButton.click();

        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid=\"product-go-to-cart-close-button\"]")));
        popupCloseButton = driver.findElement(By.xpath("//button[@data-testid=\"product-go-to-cart-close-button\"]"));
        popupCloseButton.click();
    }

    public boolean checkProductStatus(){
        if(productStatus.getText().contains("niedostępny")){
            return false;
        }
        else
            return true;
    }

    public String getProductName(){
        return productName.getText();
    }

    public String getInformationByKey(String key){
        for(WebElement element : productInformation) {
            String foundKey = element.findElement(By.xpath("div[@class='label']")).getText();
            if ((element.findElement(By.xpath("div[@class='label']")).getText()).contains(key) && key.contains("Producent")){
                String data = element.findElement(By.xpath("div[@class='data']/a")).getText();
                return (data);
            }
            else if ((element.findElement(By.xpath("div[@class='label']")).getText()).contains(key)) {
                String data = element.findElement(By.xpath("div[@class='data']")).getText();
                return (data);
            }
        }
        return null;
    }

    public WebElement getPropperPrice(){
        try{
            return(price = driver.findElement(By.xpath("//div[@class='product-right__price-normal']/span")));
        } catch (Exception ex){
            return (driver.findElement(By.xpath("//div[@class='product-right__price-discounted']")));
        }
    }

    public String getPrice(){
        price = getPropperPrice();
        String p = price.getText();
        p = p.substring(0, p.length() - 2);
        p = p.replace(",", "").replace(" ", "");
        return p;
    }
}
