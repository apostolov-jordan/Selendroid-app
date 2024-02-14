package AppTests.ApiDemosTetst;

import Apps.ApiDemos.Pages.*;
import Core.CoreSetup;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;


public class ApiDemosTests {

    AndroidDriver driver;
    @BeforeClass
    public void setupDriver() throws MalformedURLException {
        CoreSetup.setup();
        this.driver = CoreSetup.getDriver();
    }
    @Test
    public void testWifi(){
        HomePage homePage = new HomePage(driver);
        PreferencesPage preferencesPage = homePage.pressPreferences();
        WifiPage wifiPage = preferencesPage.pressPreferenceDependenciesButton();
        wifiPage.setWifiCheckBox();
        wifiPage.clickWifiSettingsButton();
        wifiPage.sendKeysIntoWifiSettingsInputField("Gosho");
        wifiPage.clickWifiSettingsOkayButton();
        wifiPage.clickWifiSettingsButton();
        Assert.assertEquals(wifiPage.getActualTextOfWifiSettingsInputField(),"Gosho");
        homePage.cleanUp();
    }

    @Test
    public void testDragAndDrop(){
        HomePage homePage = new HomePage(driver);
       ViewsPage viewsPage = homePage.pressViews();
        DragAndDropPage dragAndDropPage = viewsPage.pressDragAndDropButton();
        String actualText = dragAndDropPage.dragCircle();
        Assert.assertEquals(actualText, "Dropped");
        homePage.cleanUp();
    }
//    @AfterClass
//    public void tearDown(){
//      CoreSetup.closeAll();
//    }

}
