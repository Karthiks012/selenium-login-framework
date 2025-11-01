package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import utils.ConfigReader;
import utils.ReportManager;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent = ReportManager.getInstance();
    protected static ExtentTest test;

    @BeforeClass
    public void setup() {
        ConfigReader.loadConfig();
        String browser = ConfigReader.get("browser");
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("url"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

    public String takeScreenshot(String testName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = "test-output/screenshots/" + testName + ".png";
        try {
            FileUtils.copyFile(src, new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}
