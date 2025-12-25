package base;

import org.openqa.selenium.WebDriver;

public class DriverThreadManager {

    private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();

    public static WebDriver getDriver(){
      return dr.get();
    }

    public static void setDriver(WebDriver driverRef){
       dr.set(driverRef);
    }

    public static void unload(){
        dr.remove();
    }
}
