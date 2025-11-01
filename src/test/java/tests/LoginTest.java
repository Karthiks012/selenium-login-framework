package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import utils.ExcelReader;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        test = extent.createTest("Login Test");

        String excelPath = "test-data/TestData.xlsx";
        String username = ExcelReader.getCellValue(excelPath, 1, 0);
        String password = ExcelReader.getCellValue(excelPath, 1, 1);

        try {
            driver.findElement(org.openqa.selenium.By.id("username")).sendKeys(username);
            driver.findElement(org.openqa.selenium.By.id("password")).sendKeys(password);
            driver.findElement(org.openqa.selenium.By.id("submit")).click();

            boolean successMsg = driver.getPageSource().contains("Logged In Successfully");
            Assert.assertTrue(successMsg, "Login failed!");

            test.log(Status.PASS, "Login test passed successfully.");
        } catch (Exception e) {
            String screenshotPath = takeScreenshot("LoginTest_Failure");
            test.addScreenCaptureFromPath(screenshotPath);
            test.log(Status.FAIL, "Login test failed: " + e.getMessage());
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }
}
