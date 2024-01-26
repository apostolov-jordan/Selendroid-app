import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Basics {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    public void setup() throws MalformedURLException {
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Jordan\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
     //   service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 7 Pro");
        options.setChromedriverExecutable("");
    //    options.setApp("D:\\temp\\Java_Projects\\recall\\selendroid\\src\\test\\java\\Resources\\ApiDemos-debug.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void stopService(){
        service.stop();
    }
}
