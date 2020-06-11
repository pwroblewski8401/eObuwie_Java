package pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {
    private WebDriver driver;

    @FindBys({
            @FindBy(className = "products-list__item-wrapper")
    })
    private List<WebElement> searchList;


    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public int getResultCount(){
        return searchList.size();
    }

    public ProductPage selectProduct(boolean thereIsOnlyOneProduct){
        if(thereIsOnlyOneProduct == true){
            searchList.get(0).click();
            return new ProductPage(driver);
        }else{
            //TODO: add method to search via product name!
            return null;
        }
    }

    public ProductPage selectSpecifyProduct(int index){
        searchList.get(index).click();
        return new ProductPage(driver);
    }
}
