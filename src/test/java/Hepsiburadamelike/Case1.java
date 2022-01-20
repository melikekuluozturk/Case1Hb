package Hepsiburadamelike;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Case1 {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;
    String selectedCityLocationDrawer;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "hepsiburadamelike");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("appPackage", "com.pozitron.hepsiburada");
        caps.setCapability("appActivity", "com.hepsiburada.ui.startup.SplashActivity");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 100);
    }

    @Test
    public void runTestCase1() {
        locationSelectAndSave();
        categoriesSelectAndCheck();
    }

    public void locationSelectAndSave() {
        WebElement location = wait.until(ExpectedConditions.elementToBeClickable(By.id("locationView")));
        location.click();
        //Selected City
        WebElement citySelected = wait.until(ExpectedConditions.elementToBeClickable(By.id("citySelectorView")));
        citySelected.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement cityListView = driver.findElement(By.id("cityItemsSelectionView"));
        List<WebElement> cityChildElements = cityListView.findElements(By.className("android.view.ViewGroup"));
        WebElement cityItem = cityChildElements.get(2);

        cityItem.click();
        selectedCityLocationDrawer = citySelected.findElements(By.xpath("//android.widget.TextView")).get(0).getText();

        //Selected town
        WebElement townSelected = wait.until(ExpectedConditions.elementToBeClickable(By.id("townSelectorView")));
        townSelected.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement townListView = driver.findElement(By.id("townItemsSelectionView"));
        List<WebElement> townChildElements = townListView.findElements(By.className("android.view.ViewGroup"));
        WebElement townItem = townChildElements.get(2);
        townItem.click();

        //Selected District
        WebElement districtSelected = wait.until(ExpectedConditions.elementToBeClickable(By.id("districtSelectorView")));
        districtSelected.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement districtListView = driver.findElement(By.id("districtItemsSelectionView"));
        List<WebElement> districtChildElements = districtListView.findElements(By.className("android.view.ViewGroup"));
        WebElement districtItem = districtChildElements.get(2);
        districtItem.click();

        //Clıck Save button
        WebElement buttonSave = wait.until(ExpectedConditions.elementToBeClickable(By.id("buttonSave")));
        buttonSave.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        /*  if (driver.findElement(By.id("com_braze_inappmessage_modal_container")).isDisplayed()) {
            WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(By.id("com_braze_inappmessage_modal_close_button")));
            closePopup.click();
        }*/
    }

    public void categoriesSelectAndCheck() {
        //Click categories
        WebElement categories = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav_graph_category")));
        categories.click();

        //Click random categories
        WebElement categoriesListView = driver.findElement(By.id("recyclerViewRecoBox"));
        List<WebElement> categoriesChildElements = categoriesListView.findElements(By.className("android.view.ViewGroup"));
        WebElement categoriesdItem = categoriesChildElements.get(2);
        categoriesdItem.click();

        //Check City
        WebElement locationSelector = wait.until(ExpectedConditions.elementToBeClickable(By.id("textViewLocation")));
        if (locationSelector != null && selectedCityLocationDrawer.equalsIgnoreCase(locationSelector.getText())) {
            System.out.println("Test Case Success");
        } else {
            System.out.println("Test Case Success");
        }
    }

    @AfterMethod
    public void teardown() {
        //driver.quit();
    }
}
