import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    @BeforeTest
    public static void setUp() throws IOException {
        Configuration.timeout = 10000;
    }

    @AfterTest
    public static void tearDown(){
        if(getWebDriver() != null){
            getWebDriver().quit();
        }
    }
}
