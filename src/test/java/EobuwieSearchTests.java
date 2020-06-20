import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pop.HomePage;
import pop.ProductPage;
import pop.SearchPage;
import utils.Utils;

import java.util.concurrent.TimeUnit;

public class EobuwieSearchTests {
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
    public void simpleSearchTest(){
        searchPage = homePage.fillSearchInput("sandały");
        utils.takeScreenShot("test_simpleSearch");
        Assert.assertEquals(searchPage.getResultCount(), 100);
    }

    @Test
    @Parameters({"productID"})
    public void specifySearch(String productID){
        searchPage = homePage.fillSearchInput(productID);
        productPage = searchPage.selectProduct(true);
        utils.takeScreenShot("test_specifySearch");
        Assert.assertEquals(productPage.getProductnumber(), Integer.parseInt(productID));


    }
}
