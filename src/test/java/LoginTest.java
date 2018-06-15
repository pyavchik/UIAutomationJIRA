import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import testdata.TestData;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest{

    @Test(dataProvider="correctCredentials", dataProviderClass= TestData.class)
    public static void loginTest(String login, String password){
        LoginPage.login(login, password);
        assertEquals(MainPage.findCreateIssueButton().isDisplayed(), true);
        MainPage.logout();
    }

    @Test(dataProvider="correctLoginWrongPasswordCredentials", dataProviderClass= TestData.class,
            dependsOnMethods = "loginTest")
    public static void loginCorrectLoginWrongPasswordTest(String login, String password){
        LoginPage.login(login, password);
        assertEquals(LoginPage.findAuiErrorMessage().isDisplayed(), true);
    }

    @Test(dataProvider="wrongLoginCorrectPasswordCredentials", dataProviderClass= TestData.class,
            dependsOnMethods = "loginCorrectLoginWrongPasswordTest")
    public static void loginWrongLoginCorrectPasswordTest(String login, String password){
        LoginPage.login(login, password);
        assertEquals(LoginPage.findAuiErrorMessage().isDisplayed(), true);
    }

    @Test(dataProvider="wrongLoginWrongPasswordCredentials", dataProviderClass= TestData.class,
            dependsOnMethods = "loginWrongLoginCorrectPasswordTest")
    public static void loginWrongLoginWrongPasswordTest(String login, String password){
        LoginPage.login(login, password);
        assertEquals(LoginPage.findAuiErrorMessage().isDisplayed(), true);
    }
}
