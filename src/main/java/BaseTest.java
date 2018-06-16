import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class BaseTest {
    @BeforeTest(alwaysRun = true)
    public static void setUp() throws IOException {
        Configuration.timeout = 60000;
    }

    @AfterTest
    public static void tearDown(){

    }
}
