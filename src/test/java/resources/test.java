package resources;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class test {
    private URL url = new URL("http://127.0.0.1:4723");
    private int port = 4723;
    private String ipAddress = "127.0.0.1";
    private String myApp = "D:\\temp\\Java_Projects\\recall\\selendroid\\src\\test\\java\\Resources\\ApiDemos-debug.apk";
    private String myApp2 = "D:\\temp\\Java_Projects\\recall\\selendroid\\src\\test\\java\\Resources\\General-Store.apk";
    private String myPhoneEmulatorName = "Android Phone";
    private File myAppiumServerPath = new File("C:\\Users\\Jordan\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
    public AndroidDriver driver;
    private UiAutomator2Options options;
    private AppiumDriverLocalService service;
    WebDriverWait waiter;

    public test() throws MalformedURLException {
    }
    @BeforeTest
    public void setup() throws MalformedURLException {
        // Set Appium Server start settings with <path to NODE main.js>, <IP Address> and <port>
        service = new AppiumServiceBuilder().withAppiumJS(myAppiumServerPath).withIPAddress(ipAddress).usingPort(port).build();

        //Start Android server
        service.start();

        // sets UIAutomator2 settings
        options = new UiAutomator2Options();
        options.setDeviceName(myPhoneEmulatorName); // accepts String name of Emulator Device in Android Studio
      //  options.setApp(myApp); // accepts String full path to app location (installs and opens it).
        options.setApp(myApp);

//        options.setChromedriverExecutable("D:\\temp\\chromedriver\\chromedriver.exe");
        //sets driver settings, accepts URL object and UIAutomator2Options object
        driver = new AndroidDriver(url,options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        waiter = new WebDriverWait(driver, Duration.ofSeconds(5));



    }

    @Test
    public void testSettings() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        if (driver.findElement(AppiumBy.id("android:id/checkbox")).isEnabled()){
            if (!driver.findElement(AppiumBy.id("android:id/checkbox")).isSelected()){
                driver.findElement(AppiumBy.id("android:id/checkbox")).click();
            }
        } else {
            System.out.println("The checkbox is not enabled");
        }
        By element = new By.ByXPath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"WiFi settings\"]");
        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
        String expectedText = "WIFI Settingss";
        String actualText = driver.findElement(element).getText();
        Assert.assertEquals(actualText,expectedText);
    }//

    @Test
    public void scrollTestUIAutomator() throws InterruptedException {
        String scrollToThisText = "Visibility";
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + scrollToThisText + "\"))"));
        driver.findElement(AppiumBy.accessibilityId("Visibility")).click();
        boolean viewC = driver.findElement(AppiumBy.accessibilityId("View C")).isDisplayed();
                Assert.assertTrue(viewC);
                Thread.sleep(2000);

    }

    @Test
    public void scrollToElementTest(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        By locator = AppiumBy.accessibilityId("Lists");
        scrollToElement(locator);
        driver.findElement(locator).click();
        waiter.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("01. Array")));
        boolean viewC = driver.findElement(AppiumBy.accessibilityId("01. Array")).isDisplayed();
        Assert.assertTrue(viewC);
    }

    @Test
    public void DragAndDropTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        waiter.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Drag and Drop")));
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement dragCircle = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        DragAndDropAction(dragCircle, 400,325);
        WebElement dragDropText = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text"));
        String actualText = dragDropText.getText();
        Assert.assertEquals(actualText, "Dropped");


    }

        //doesn't work
    @Test
    public void scrollToElementTest2() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        By locator = AppiumBy.accessibilityId("Lists");
        scrollToElement2(locator);
        driver.findElement(locator).click();
        waiter.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("01. Array")));
        boolean viewC = driver.findElement(AppiumBy.accessibilityId("01. Array")).isDisplayed();
        Assert.assertTrue(viewC);
    }

    @Test
    public void scrollToElementByVisibleTextTest(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        scrollToElementByVisibleText("Lists");
        By locator = AppiumBy.accessibilityId("Lists");
        driver.findElement(locator).click();
        waiter.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("01. Array")));
        boolean viewC = driver.findElement(AppiumBy.accessibilityId("01. Array")).isDisplayed();
        Assert.assertTrue(viewC);
    }


    @Test
    public void scrollTestGesture(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        boolean canScrollMore;
        boolean visibility;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 300, "top", 300, "width", 300, "height", 300,
                    "direction", "down",
                    "percent", 5.0
            ));
            if (driver.findElement(AppiumBy.accessibilityId("Lists")).isDisplayed()){
                driver.findElement(AppiumBy.accessibilityId("Lists")).click();
                break;
            }

        }while (canScrollMore);
        //Assert.assertTrue(driver.findElement(AppiumBy.accessibilityId("Visibility")).isDisplayed());
        ;
        boolean viewC = driver.findElement(AppiumBy.accessibilityId("01. Array")).isDisplayed();
        Assert.assertTrue(viewC);


    }

    @Test
    public void clickAndHoldTest(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement elementPeopleNames = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        longPress(elementPeopleNames);
        String actualText = driver.findElement(By.xpath("//android.widget.TextView[@text='Sample menu']")).getText();
        Assert.assertEquals(actualText, "Sample menu");
    }

    public void longPress(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),"duration", 2500
        ));
    }

    public void scrollToElement (By locator){
        boolean canScrollMore;

        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 300, "top", 300, "width", 300, "height", 300,
                    "direction", "down",
                    "percent", 5.0
            ));
            if(driver.findElement(locator).isDisplayed()){
                break;
            }
            }while(canScrollMore);
    }
    //doesn't work
    public void scrollToElement2 (By locator){
        boolean canScrollMore;

        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                   "elementId", ((RemoteWebElement)driver.findElement(locator)).getId(),
                    "direction", "down",
                    "percent", 5.0
            ));

        }while(canScrollMore);
        System.out.println("No such WebElement");

    }



    public void scrollToElementByVisibleText(String text){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"))"));
    }

    public void DragAndDropAction(WebElement element,int endX, int endY){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", endX,
                "endY", endY
        ));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
        service.stop();
    }
}
