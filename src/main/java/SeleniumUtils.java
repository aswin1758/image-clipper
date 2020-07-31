import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class SeleniumUtils {

    private static final String BG_REMOVER_SITE = "https://www.remove.bg/";
    private static final String DRIVER_PATH = "src/main/resources/chromedriver/chromedriver";
    private static final String DOWNLOAD_FOLDER = "/home/aswin/Projects/image-clipper/processed";


    public boolean clipImage(String image) {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        Map<String,Object> prefs = new HashMap<>();
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("download.default_directory", DOWNLOAD_FOLDER);
        options.setExperimentalOption("prefs",prefs);
        WebDriver driver = new ChromeDriver(options);
        try {
            // Open the website
            driver.get(BG_REMOVER_SITE);
            driver.findElement(By.xpath("//div[@class=\"card-body text-center\"]")).click();
            imageUploadRobot(image);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"mb-1\"]/a")));
            element.click();
            Thread.sleep(10000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    private void imageUploadRobot(String imagePath) {
        StringSelection ss = new StringSelection(imagePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        try {
            Robot robot = new Robot();
            robot.delay(250);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(50);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException awtException) {
            awtException.printStackTrace();
        }
    }
}
