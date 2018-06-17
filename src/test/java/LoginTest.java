import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import testdata.TestData;
import utils.MyTestListeners;

import static org.testng.Assert.assertEquals;

@Listeners(MyTestListeners.class)
public class LoginTest{

    @Test(dataProvider="correctCredentials", dataProviderClass= TestData.class, groups = {"masterBuild", "loginGroup"})
    public static void loginTest(String login, String password){
        LoginPage.login(login, password);
        assertEquals(MainPage.at(), true);
        MainPage.logout();
    }

    @Test(dataProvider="correctLoginWrongPasswordCredentials", dataProviderClass= TestData.class,
            dependsOnMethods = "loginTest", groups = {"masterBuild", "loginGroup"})
    public static void loginCorrectLoginWrongPasswordTest(String login, String password){
        LoginPage.login(login, password);
        assertEquals(LoginPage.findAuiErrorMessage().isDisplayed(), true);
        assertEquals(LoginPage.at(), true);
    }

    @Test(dataProvider="wrongLoginCorrectPasswordCredentials", dataProviderClass= TestData.class,
            dependsOnMethods = "loginCorrectLoginWrongPasswordTest", groups = {"masterBuild", "loginGroup"})
    public static void loginWrongLoginCorrectPasswordTest(String login, String password){
        LoginPage.login(login, password);
        assertEquals(LoginPage.findAuiErrorMessage().isDisplayed(), true);
        assertEquals(LoginPage.at(), true);
    }

    @Test(dataProvider="wrongLoginWrongPasswordCredentials", dataProviderClass= TestData.class,
            dependsOnMethods = "loginWrongLoginCorrectPasswordTest", groups = {"masterBuild", "loginGroup"})
    public static void loginWrongLoginWrongPasswordTest(String login, String password){
        LoginPage.login(login, password);
        assertEquals(LoginPage.findAuiErrorMessage().isDisplayed(), true);
        assertEquals(LoginPage.at(), true);
    }
}
