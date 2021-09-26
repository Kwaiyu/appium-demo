import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidBrowserSaucelabsTest extends BaseTest{
//    public static final String USERNAME = "YOUR_USERNAME";
//    public static final String ACCESS_KEY = "YOUR_ACESS_KEY";
//    public static final String URL = "https://"+USERNAME+":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    public static AndroidDriver<?> mobiledriver;

    /**
     *  No Chromedriver found. 手动下载移动端对应chromedriver配置chromedriverExecutable路径，
     *  或传启动参数自动下载appium --allow-insecure chromedriver_autodownload
     */
    @BeforeTest
    public void beforeTest( ) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "sdk_gphone_x86");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability("newCommandTimeout", 2000);
//        mobiledriver = new AndroidDriver<>(new URL(URL), capabilities);
        mobiledriver = new AndroidDriver<>(getServiceUrl(), capabilities);
    }

    @AfterTest
    public void afterTest( ){
        if (mobiledriver != null){
            mobiledriver.quit();
        }
    }

    @Test
    public static void launchBrowser(){
        mobiledriver.get("http://appium.io/");
        Assert.assertEquals(mobiledriver.getCurrentUrl(), "http://appium.io/", "URL Mismatch");
        Assert.assertEquals(mobiledriver.getTitle(), "Appium: Mobile App Automation Made Awesome.", "Title Mismatch");
    }
}