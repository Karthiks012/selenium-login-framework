package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            ExtentSparkReporter dark = new ExtentSparkReporter("test-output/ExtentReport-Dark.html");
            ExtentSparkReporter light = new ExtentSparkReporter("test-output/ExtentReport-Light.html");

            dark.config().setTheme(Theme.DARK);
            light.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(dark, light);
            extent.setSystemInfo("Browser", ConfigReader.get("browser"));
            extent.setSystemInfo("Environment", ConfigReader.get("environment"));
            extent.setSystemInfo("Author", ConfigReader.get("author"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }
}
