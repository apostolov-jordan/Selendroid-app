import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class BasicTests {
    @BeforeMethod
    public void initialize() throws MalformedURLException {
        Basics obj = new Basics();
        obj.setup();
    }
    @Test
    public void firstTest(){

    }

    @AfterMethod
    public void tearDown(){
        Basics obj = new Basics();
        obj.stopService();
    }
}
