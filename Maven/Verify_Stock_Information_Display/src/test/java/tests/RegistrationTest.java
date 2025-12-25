package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static base.DriverThreadManager.getDriver;


public class RegistrationTest extends BaseTest {



    // Method to access the username WebElement
    public WebElement SearchBox() {
        return getDriver().findElement(By.xpath("//input[@role='combobox']"));
    }
    public WebElement FirstSuggestion() {
        return getDriver().findElement(By.xpath("//a[@id='async-navbar-search-item-0']//p//span[@class='rt']"));
    }
    public WebElement StockTitle() {
        return getDriver().findElement(By.xpath("//h1[@id='quoteName']//span[1]"));
    }
    public WebElement StockPrice() {
        return getDriver().findElement(By.xpath("//div[@class='blkbox-whitetxt']//span[@id='quoteLtp']"));
    }
    public WebElement NoRecord() {
        return getDriver().findElement(By.xpath("//a[@id='async-navbar-search-item-0']//div//p[contains(text(),'Click Here for More Results')]"));
    }
    public WebElement HighWeek() {
        return getDriver().findElement(By.xpath("*//i[@id='week52HighDate']"));
    }
    public WebElement HighWeekPrice() {
        return getDriver().findElement(By.xpath("*//span[@id='week52highVal']"));
    }
    public WebElement LowWeek() {
        return getDriver().findElement(By.xpath("//i[@id='week52LowDate']"));
    }
    public WebElement LowWeekPrice() {
        return getDriver().findElement(By.xpath("*//span[@id='week52lowVal']"));
    }
    @Parameters("browser")
    @Test(dataProvider = "stockData")
    public void testRegistrationForm(String stockName) throws Exception {
        ExtentTest test = extentReports.createTest("Registration Test for: "+stockName," This is Registation Test").assignAuthor("Ajay Hajare");
        test.log(Status.INFO,"Test has started");

        test.addScreenCaptureFromBase64String(captureScreenShot(),"Screen Shot of before retriving any stock information, Pass");

        //String stockName ="NONEXISTENTSTOCK";
        //String stockName ="Tata Motors Limited ";
        test.info("Stock is entered for search: "+stockName);
        SearchBox().sendKeys(stockName);

        if(getDriver().findElements(By.xpath("//a[@id='async-navbar-search-item-0']//div//p[contains(text(),'Click Here for More Results')]")).isEmpty())
        {
            FirstSuggestion().click();
            test.info("Selected first Stock: "+stockName);

            Thread.sleep(5000);

            Assert.assertTrue(StockTitle().isDisplayed());
            test.pass("Stock Title is displayed: "+StockTitle().getText());

            Assert.assertEquals(stockName.trim().toUpperCase(), StockTitle().getText());
            test.pass("Entered Stock: "+stockName.trim().toUpperCase()+" Stock Title displaying is same: "+StockTitle().getText());

            Assert.assertTrue(StockPrice().isDisplayed());
            System.err.println("Current Price: " +  StockPrice().getText());
            test.log(Status.INFO,"Current Price: "+StockPrice().getText());

            if(HighWeekPrice().isDisplayed()) {
                Assert.assertTrue(HighWeekPrice().isDisplayed());
                System.err.println("52 Week High Date: " + HighWeek().getText() + " And 52 Week High Price: " + HighWeekPrice().getText());
                test.info("52 Week High Date: " + HighWeek().getText() + " And 52 Week High Price: " + HighWeekPrice().getText());
                test.pass("52 Week High Price is Displayed.");
            }
            else {
                Assert.assertTrue(HighWeek().isDisplayed());
                System.err.println("52 Week High Date: " + HighWeek().getText() + " 52 Week High Price: " + HighWeekPrice().getText());
                test.info("52 Week High Date: " + HighWeek().getText() + " 52 Week High Price: " + HighWeekPrice().getText());
                test.fail("52 Week High Price is not displayed.");
                test.addScreenCaptureFromBase64String(captureScreenShot(),"52 High Week Price is not displayed");
            }
            if(LowWeek().isDisplayed()) {
                Assert.assertTrue(LowWeek().isDisplayed());
                System.err.println("52 Week Low Date: " + LowWeek().getText() + " 52 Week Low Price: " + LowWeekPrice().getText());
                test.info("52 Week Low Date: " + LowWeek().getText() + " 52 Week Low Price: " + LowWeekPrice().getText());
                test.pass("52 Week Low Price is Displayed.");
            }
            else {
                Assert.assertTrue(LowWeek().isDisplayed());
                test.info("52 Week Low Date: " + LowWeek().getText() + " 52 Week Low Price: " + LowWeekPrice().getText());
                test.fail("52 Week Low Price is not displayed.");
                test.addScreenCaptureFromBase64String(captureScreenShot(),"52 Low Week Price is not displayed");
            }
            test.addScreenCaptureFromBase64String(captureScreenShot(),"Stock details, Pass");
        }
        else
        {
            System.err.println("Non exist stock entered! "+stockName );
            test.info("Test is failed due to Stock not exists: "+stockName);
            test.fail("Test is failed due Stock not exists.: "+stockName );
            test.addScreenCaptureFromBase64String(captureScreenShot(),"Failed");
            Assert.assertTrue(driver.findElements(By.xpath("//a[@id='async-navbar-search-item-0']//div//p[contains(text(),'Click Here for More Results')]")).isEmpty());
        }
    }


    public String captureScreenShot()
    {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        String base64Code = ts.getScreenshotAs(OutputType.BASE64);
        System.out.println("ScreenShot takes successfully.");
        return base64Code;

    }

    @DataProvider(parallel = true)
    public String[][] stockData(){
     return new String[][]{
             {"Tata Motors Limited"},

             {"GAIL (India) Limited"}
     };
    }
}
// {"Reliance Industries Limited"},
//             {"Bharat Heavy Electricals Limited"},
//ajayhajare@mml.local