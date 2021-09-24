import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AndroidSelectorsTest extends BaseTest{
//    private AppiumDriver driver;
    private AndroidDriver<WebElement> driver;
    public static AppiumDriverLocalService service=null;

    @BeforeSuite
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../appium-demo/apps");
        File app = new File(appDir.getCanonicalPath(), "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "11");
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "io.appium.android.apis");
        capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
//        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver = new AndroidDriver<WebElement>(getServiceUrl(), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterSuite
    public void tearDown() {
//        service.stop();
        driver.quit();
    }

    @Test
    public void testFindElementsByAccessibilityId() {
        List<WebElement> searchParametersElement = (List<WebElement>)
                driver.findElementsByAccessibilityId("Content");
        Assert.assertEquals(searchParametersElement.size(),1);
    }

    @Test
    public void testFindElementsById () {
        // Look for element by ID. In Android this is the "resource-id"
        List<WebElement> actionBarContainerElements = (List<WebElement>) driver.findElementsById("android:id/action_bar_container");
        Assert.assertEquals(actionBarContainerElements.size(), 1);
    }

    @Test
    public void testFindElementsByClassName () {
        // Look for elements by the class name. In Android this is the Java Class Name of the view.
        List<WebElement> linearLayoutElements = (List<WebElement>) driver.findElementsByClassName("android.widget.FrameLayout");
        Assert.assertTrue(linearLayoutElements.size() > 1);
    };

    @Test
    public void testFindElementsByXPath () {
        // Find elements by XPath
        List<WebElement> linearLayoutElements = (List<WebElement>) driver.findElementsByXPath("//*[@class=\"android.widget.FrameLayout\"]");
        Assert.assertTrue(linearLayoutElements.size() > 1);
    };
}
