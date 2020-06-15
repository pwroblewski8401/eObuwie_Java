package pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class BasketPage {
    public WebDriver driver;

    @FindBys({
            @FindBy(className = "cart-item__wrapper")
    })
    private List<WebElement>  productsInBasket;

    @FindBys({
            @FindBy(className = "cart-item__name-second")
    })
    private List<WebElement> productInBasketNames;


    public  BasketPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProductList(){
        return productsInBasket;
    }

    public List<String> getProductNames(){
        List<String> productsNames = new ArrayList<>();
        for(WebElement element: productInBasketNames){
            productsNames.add(element.getText());
        }

        return productsNames;
    }



}
