package resources;

import Apps.ApiDemos.Pages.PreferencesPage;
import Apps.ApiDemos.Pages.WifiPage;
import Core.CoreSetup;
import Apps.ApiDemos.Pages.HomePage;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class testPageFactory {

    private AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        CoreSetup.setup();
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
    }

    @AfterTest
    public void tearDown(){
        CoreSetup.closeAll();
    }
}
