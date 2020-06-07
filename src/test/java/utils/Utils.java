package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
