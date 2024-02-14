package Apps.ApiDemos.Pages;

import Core.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    AndroidDriver driver;
    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}

    @AndroidFindBy(accessibility = "Preference")
    private static WebElement preferenceButton;

    @AndroidFindBy(accessibility = "Views")
    private static WebElement viewsButton;

    @AndroidFindBy(className = "android.widget.TextView")
    private static List<WebElement> actionBarText;




    public PreferencesPage pressPreferences(){
        preferenceButton.click();
        return new PreferencesPage(driver);
    }

    public ViewsPage pressViews(){
        viewsButton.click();
        return new ViewsPage(driver);
    }

    public void cleanUp(){
        String upperText = actionBarText.get(0).getText();
        while(upperText.equalsIgnoreCase("API Demos")){
            AndroidActions.pressBackButton();
            upperText = actionBarText.get(0).getText();
        }
    }
}
