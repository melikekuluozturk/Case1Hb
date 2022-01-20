package Hepsiburadamelike;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Hello world!
 *
 */
public class App
{
     static AppiumDriver<MobileElement> driver;

    public static void main( String[] args )
    {

        openCalculator();
    }

    public static void openCalculator(){
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName","PPA-LX2");
        cap.setCapability("udid","CNXYD21430215655");
        cap.setCapability("platformName","Android");
        cap.setCapability("platformVersion","10");
        cap.setCapability("appPackage","com.pozitron.hepsiburada");
        cap.setCapability("appActivity","com.hepsiburada.ui.startup.SplashActivity");

        URL url = null;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new AppiumDriver<MobileElement>(url,cap);
    }
}
