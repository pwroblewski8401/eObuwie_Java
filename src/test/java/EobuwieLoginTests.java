import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pop.*;
import utils.Utils;

import java.util.concurrent.TimeUnit;

public class EobuwieLoginTests {

    public WebDriver driver;
    public HomePage homePage;
    public SignUpPage signUpPage;
    public AccountPage accountPage;
    public LoginPage loginPage;
    public LogoutPage logoutPage;

    private Utils utils;

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

    @Test(enabled = true)
    public void validSignUpTest() {
        signUpPage = homePage.gotoSignUpPage();
        accountPage = signUpPage.fillForm("John","Doe","Johnnn.doeee@wp.pl", "Password!");
        utils.takeScreenShot("test_singUpValidData");
        Assert.assertTrue(accountPage.getAccountHeaderText().contains("John"));
    }

    @Test
    @Parameters({"email", "pass"})
    public void validLoginTest(String email, String pass){
        loginPage = homePage.gotoLoginPage();
        accountPage = loginPage.fillLoginForm(email, pass);
        utils.takeScreenShot("test_singInValidData");
        Assert.assertTrue(accountPage.getAccountHeaderText().contains("John"));
    }

    @Test(dependsOnMethods = "validLoginTest")
    public void logoutTest(){
        logoutPage = accountPage.logout();
        utils.takeScreenShot("test_logout");
        Reporter.log(String.format("Info form page: %s. Expected: Wylogowywanie zakończone", logoutPage.getLogoutInfo()), true);
        Assert.assertTrue(logoutPage.getLogoutInfo().contains("Wylogowywanie zakończone"));

    }
}
