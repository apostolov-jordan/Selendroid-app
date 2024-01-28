import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class BasicTests {
    @BeforeMethod
    public void initialize() throws MalformedURLException {
        Basics obj = new Basics();
        obj.setup();
    }
    @Test
    public void firstTest(){

    }

//    @AfterMethod
//    public void tearDown(){
//        Basics obj = new Basics();
//        obj.getDriver().quit();
//        obj.getService().stop();
//    }
}
