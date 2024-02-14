package Apps.ApiDemos.Pages;

import Core.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropPage {

    AndroidDriver driver;
    public DragAndDropPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(id = "io.appium.android.apis:id/drag_dot_1")
    private WebElement dragDot1;

    @AndroidFindBy(id = "io.appium.android.apis:id/drag_result_text")
    private WebElement dragResultText;

    public String dragCircle(){
        AndroidActions.dragAndDropAction(dragDot1,400,325);
        return dragResultText.getText();
    }
}
