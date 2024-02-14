package Apps.ApiDemos.Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ViewsPage {
    private AndroidDriver driver;
    public ViewsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(accessibility = "Drag and Drop")
    private WebElement dragAndDropButton;

    public DragAndDropPage pressDragAndDropButton(){
        DragAndDropPage dragAndDropPage = new DragAndDropPage(driver);
        dragAndDropButton.click();
        return dragAndDropPage;
    }
}
