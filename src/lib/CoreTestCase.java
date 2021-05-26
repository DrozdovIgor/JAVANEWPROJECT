package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase
{
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL="http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait ()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape ()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp (int seconds)
    {
        driver.runAppInBackground(seconds);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv () throws Exception
    {

        String platform = System.getenv("PLATFORM");
        //System.out.println(platform);
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "Google Nexus");
            capabilities.setCapability("platformVersion", "9");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "C:\\Users\\Test\\Desktop\\JavaAppiumAutomationNew\\apks\\org.wikipedia.apk");
            capabilities.setCapability("orientation", "PORTRAIT");

        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOs");
            capabilities.setCapability("deviceName", "iphone SE");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "C:\\Users\\Test\\Desktop\\JavaAppiumAutomationNew\\apks\\wikipedia.app");
        } else {
        throw new Exception("Cannot get run platform from env variable. Platform value " + platform );
        }

        return capabilities;
    }

}
