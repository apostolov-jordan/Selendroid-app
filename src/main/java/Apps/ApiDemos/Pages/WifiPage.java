package Apps.ApiDemos.Pages;

import Core.CoreSetup;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WifiPage {

    private AndroidDriver driver;

    public WifiPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "android:id/checkbox")
    private static WebElement wifiCheckBox;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/title' and @text='WiFi settings']")
    private static WebElement wifiSettingsButton;

    @AndroidFindBy(id = "android:id/edit")
    private static WebElement wifiSettingsInputField;

    @AndroidFindBy(id = "android:id/button1")
    private static WebElement wifiSettingsOKButton;

    public static void setWifiCheckBox(){
        wifiCheckBox.click();
    }

    public static void clickWifiSettingsButton(){
        CoreSetup.getWaiter().until(ExpectedConditions.attributeToBe(wifiSettingsButton,"enabled","true"));
        wifiSettingsButton.click();
    }

    public static void sendKeysIntoWifiSettingsInputField(String sendKeys){
        CoreSetup.getWaiter().until(ExpectedConditions.elementToBeClickable(wifiSettingsOKButton));
        wifiSettingsInputField.sendKeys(sendKeys);
    }

    public static void clickWifiSettingsOkayButton(){
        wifiSettingsOKButton.click();
    }

    public static String getActualTextOfWifiSettingsInputField(){
        String locator = wifiSettingsOKButton.getAttribute("resource-id");
        By byLocator = new By.ById(locator);
        CoreSetup.getWaiter().until(ExpectedConditions.presenceOfElementLocated(byLocator));
        String actualText = wifiSettingsInputField.getText();
        return actualText;
    }
}
