package Core;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CoreSetup {
    private static URL url;

    static {
        try {
            url = new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int port = 4723;
    private static String ipAddress = "127.0.0.1";
    private static String myApp = "D:\\temp\\Java_Projects\\recall\\selendroid\\src\\test\\java\\Resources\\ApiDemos-debug.apk";
    private static String myApp2 = "D:\\temp\\Java_Projects\\recall\\selendroid\\src\\test\\java\\Resources\\General-Store.apk";
    private static String myPhoneName = "Android Device"; //physical device.
    private static File myAppiumServerPath = new File("C:\\Users\\Jordan\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
    public static AndroidDriver driver;
    private static UiAutomator2Options options;
    private static AppiumDriverLocalService service;
    private static WebDriverWait waiter;

    public static void setup() throws MalformedURLException {
        // Set Appium Server start settings with <path to NODE main.js>, <IP Address> and <port>
        service = new AppiumServiceBuilder().withAppiumJS(myAppiumServerPath).withIPAddress(ipAddress).usingPort(port).build();

        //Start Android server
        service.start();

        // sets UIAutomator2 settings
        options = new UiAutomator2Options();
        options.setDeviceName(myPhoneName); // accepts String name of Emulator Device in Android Studio
        //  options.setApp(myApp); // accepts String full path to app location (installs and opens it).
        options.setApp(myApp);

        options.setChromedriverExecutable("D:\\temp\\chromedriver\\chromedriver.exe");
        //sets driver settings, accepts URL object and UIAutomator2Options object
        driver = new AndroidDriver(url,options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        waiter = new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    public static void closeAll(){
        driver.quit();
        service.close();
    }

    public static WebDriverWait getWaiter(){
        return waiter;
    }

    public static AndroidDriver getDriver() {
        return driver;
    }
    public static void closeApp(){
        driver.close();
    }
}
