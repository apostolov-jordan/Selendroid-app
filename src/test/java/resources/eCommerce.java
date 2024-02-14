package resources;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class eCommerce extends test{


    public eCommerce() throws MalformedURLException {
    }

    @Test
    public void homePageTest(){
        WebElement selectCountry = driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"));
        selectCountry.click();
        scrollToElementByVisibleText("Bulgaria");
        driver.findElement(By.xpath("//android.widget.TextView[@text='Bulgaria']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Jordan");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String actualText = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
        Assert.assertEquals(actualText, "Products");
    }

    @Test
    public void productsPageTest() {
        
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        scrollToElementByVisibleText("Austria");
        driver.findElement(By.xpath("//android.widget.TextView[@text='Austria']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("SSS");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String converseAllStar = "Converse All Star";
        scrollToElementByVisibleText(converseAllStar);
        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='Converse All Star']")).isDisplayed());
        int size = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for (int i = 0; i < size; i++) {
            String getName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (getName.equalsIgnoreCase("Converse All Star")){
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("com.androidsample.generalstore:id/toolbar_title")));
        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='Converse All Star']")).isDisplayed());

    }

}
