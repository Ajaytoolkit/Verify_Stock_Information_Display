package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
//import io.github.bonigarcia.wdm.WebDriverManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;

import static base.BrowserFactory.GetDriver;
import static base.DriverThreadManager.*;
import static base.DriverThreadManager.getDriver;


public class BaseTest {

    public WebDriver driver;

    protected ExtentReports extentReports;
    protected ExtentSparkReporter extentSparkReporter;

    @BeforeSuite
    public void ReportSetUp()
    {
        extentReports = new ExtentReports();
        extentSparkReporter= new ExtentSparkReporter("ExtentReports/index.html");
        extentSparkReporter.config().setDocumentTitle("Registration Report");
        extentSparkReporter.config().setReportName("Extent Report");
        extentReports.attachReporter(extentSparkReporter);
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        ExtentTest test = extentReports.createTest("Test Setup","Test executed on");
       // test= extentReports.createTest("Test Execution for Browser: " + browser);

        driver = GetDriver(browser);
        setDriver(driver);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        test.info("Browser is opening.");
        getDriver().get("https://www.nseindia.com/");

        test.info("Url opened.");

    }


    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            unload();
        }
    }


    @AfterSuite
    public void ReportTearDown() throws IOException
    {
        if (extentReports != null) {
            extentReports.flush();
        }
        Desktop.getDesktop().browse(new File("ExtentReports/index.html").toURI());
    }


}
