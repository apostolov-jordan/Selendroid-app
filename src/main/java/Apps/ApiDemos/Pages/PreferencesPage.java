package Apps.ApiDemos.Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PreferencesPage {
    AndroidDriver driver;

    public PreferencesPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "3. Preference dependencies")
    private WebElement preferenceDependenciesButton;

    public WifiPage pressPreferenceDependenciesButton(){
        preferenceDependenciesButton.click();
        return new WifiPage(driver);
    }

}
