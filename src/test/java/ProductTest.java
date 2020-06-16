import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.IReporter;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pop.HomePage;
import pop.ProductPage;
import pop.SearchPage;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ProductTest {

    private WebDriver driver;
    HomePage homePage;
    SearchPage searchPage;
    ProductPage productPage;
    Utils utils;

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
    @Parameters({"CSVFile"})
    public void productCartTest(String CSVFile)
    {
        try {
            CSVReader reader = new CSVReader(new FileReader(CSVFile));
            String[] line;
            while((line = reader.readNext()) != null){
                String id = line[0];
                String price = line[1];
                String producer = line[2];
                String model = line [3];
                String color= line[4];
                checkProduct(id, price, producer, model, color);
                homePage = utils.goToHomePage();
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public void checkProduct(String id, String price, String producer, String model, String color){
        searchPage = homePage.fillSearchInput(id);
        productPage = searchPage.selectProduct(true);
        String pModel = productPage.getInformationByKey("Model");
        String pProducer = productPage.getInformationByKey("Producent");
        String pColor = productPage.getInformationByKey("Kolor");
        String pPrice = productPage.getPrice();
        Assert.assertEquals(producer, pProducer, String.format("Desired producer: %s do not match with producer on page: %s", producer, pProducer));
        Assert.assertEquals(model, pModel, String.format("Desired model: %s do not match with model on page: %s", model, pModel));
        Assert.assertEquals(color, pColor, String.format("Desired color: %s do not match with color on page: %s", color, pColor));
        Assert.assertTrue(price.replace(",","").replace(".","").contains(pPrice), String.format("price forom page: %s, price form CSV: %s", pPrice,price.replace(",","").replace(".","")));
    }
}
