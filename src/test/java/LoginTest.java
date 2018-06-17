import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import testdata.TestData;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest{

    @Test(dataProvider="correctCredentials", dataProviderClass= TestData.class, groups = {"masterBuild", "loginGroup"})
    public static void loginTest(String login, String password){
        LoginPage.login(login, password);
        MainPage.findCreateIssueButton().waitUntil(Condition.appear, 60000);
        assertEquals(MainPage.findCreateIssueButton().isDisplayed(), true);
        MainPage.logout();
    }

    @Test(dataProvider="correctLoginWrongPasswordCredentials", dataProviderClass= TestData.class,
            dependsOnMethods = "loginTest", groups = {"masterBuild", "loginGroup"})
    public static void loginCorrectLoginWrongPasswordTest(String login, String password){
        LoginPage.login(login, password);
        assertEquals(LoginPage.findAuiErrorMessage().isDisplayed(), true);
    }

    @Test(dataProvider="wrongLoginCorrectPasswordCredentials", dataProviderClass= TestData.class,
            dependsOnMethods = "loginCorrectLoginWrongPasswordTest", groups = {"masterBuild", "loginGroup"})
    public static void loginWrongLoginCorrectPasswordTest(String login, String password){
        LoginPage.login(login, password);
        assertEquals(LoginPage.findAuiErrorMessage().isDisplayed(), true);
    }

    @Test(dataProvider="wrongLoginWrongPasswordCredentials", dataProviderClass= TestData.class,
            dependsOnMethods = "loginWrongLoginCorrectPasswordTest", groups = {"masterBuild", "loginGroup"})
    public static void loginWrongLoginWrongPasswordTest(String login, String password){
        LoginPage.login(login, password);
        assertEquals(LoginPage.findAuiErrorMessage().isDisplayed(), true);
    }
}
