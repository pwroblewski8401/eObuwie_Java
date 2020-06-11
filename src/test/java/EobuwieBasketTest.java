import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pop.HomePage;
import pop.ProductPage;
import pop.SearchPage;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EobuwieBasketTest {

    private WebDriver driver;
    public HomePage homePage;
    public SearchPage searchPage;
    public ProductPage productPage;
    public Utils utils;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "./src/test/java/data/chromedriver.exe");
        driver = new ChromeDriver();
        this.utils = new Utils(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.eobuwie.com.pl/");
        homePage = new HomePage(driver);
        homePage.skipAgreement();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    @Test
    @Parameters({"fileName"})
    public void addProductsToBasket(String filename){
        List<String> fileContent = utils.readTextDataFile(filename);
        List<String> productsAddedToBasket = new ArrayList<>();
        for(String product : fileContent){
            searchPage = homePage.fillSearchInput(product);
            productPage = searchPage.selectSpecifyProduct(0);
            if(productPage.checkProductStatus() == true){
                if(productPage.ifSizeSelectorIsPresent() == true){
                    productPage.setSize(1);
                }
                productPage.addToCart();
                productsAddedToBasket.add(productPage.getProductName());
            }
            else {
                Reporter.log(String.format("Product %s is unavailable! Skipping!", productPage.getProductName()), true);
            }

            utils.goToHomePage();
        }


    }
}
