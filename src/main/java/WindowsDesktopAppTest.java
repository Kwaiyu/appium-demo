import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WindowsDesktopAppTest extends BaseTest {

    public static WindowsDriver<?> driver;

    @BeforeTest
    public void setup( ) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformVersion", "10");
        caps.setCapability("platformName", "Windows");
        caps.setCapability("deviceName", "WindowsPC");
        caps.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        caps.setCapability("newCommandTimeout", 2000);
        driver = new WindowsDriver<>(getServiceUrl(), caps);
    }

    @AfterTest
    public void tearDown( ) {
        driver.quit();
    }

    @Test
    public void test() {
        driver.findElementByName("一").click();
        driver.findElementByName("加").click();
        driver.findElementByName("二").click();
        driver.findElementByName("等于").click();
        Assert.assertEquals(driver.findElementByAccessibilityId("CalculatorResults").getText(), "显示为 3");
    }
}