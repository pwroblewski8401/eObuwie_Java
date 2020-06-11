package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import pop.HomePage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {
    WebDriver driver;

    public Utils(WebDriver driver){
        this.driver = driver;
    }

    public void takeScreenShot(String filename){
        String fName = String.format("%s_%s", filename, getTimeStamp());
        try {

            Screenshot screenshot = new AShot().takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "png", new File(String.format("./Artifacts/Screenshots/%s.png",fName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTimeStamp(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd_mm_yy__HH_mm_ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public List<String> readTextDataFile(String file){
        List<String> fileContent = new ArrayList<>();
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while(line != null){
                fileContent.add(line);
                line = reader.readLine();
            }
            reader.close();
            return fileContent;
        } catch (IOException ex){
            Reporter.log("Error while reading file: " + ex.getMessage());
            return null;
        }
    }

    public HomePage goToHomePage(){
        driver.get("https://www.eobuwie.com.pl/");
        return new HomePage(driver);
    }
}
