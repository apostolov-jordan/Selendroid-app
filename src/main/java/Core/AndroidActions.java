package Core;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import java.net.MalformedURLException;

public class AndroidActions extends CoreSetup{



    public static void scrollToVisibleTextUsingUIAutomator(String scrollToThisText){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text('" + scrollToThisText + "'))"));
    }

    public static void dragAndDropAction(WebElement element, int endX, int endY){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", endX,
                "endY", endY));
    }

    public static void scrollToWebElement (WebElement webElement){

        boolean canScrollMore;

        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 300, "top", 300, "width", 300, "height", 300,
                    "direction", "down",
                    "percent", 5.0));
            if(webElement.isDisplayed()){
                break;
            }
        }while(canScrollMore);
    }

    public static void  longPress(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),"duration", 2500));
    }

    public static void swipe(int left, int top, int width, int height, String direction, double percentage){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", left, "top", top, "width", width, "height", height,
                "direction", direction,
                "percent", percentage));
    }

    public static void pressBackButton(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }
}
