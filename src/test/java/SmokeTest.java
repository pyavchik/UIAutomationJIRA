import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import utils.MyTestListeners;

import static org.testng.Assert.assertEquals;
import static utils.PropertiesClass.getPropertyValue;

@Listeners(MyTestListeners.class)
public class SmokeTest {

    @BeforeClass(alwaysRun = true)
    public static void setUp(){
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
    }

    @Test(dependsOnGroups = "loginGroup", groups = {"masterBuild", "smoke"})
    public static void dashboardLinkTest(){
        assertEquals(MainPage.findDashboardLink().isDisplayed(), true);
    }

    @Test(dependsOnGroups = "loginGroup", groups = {"masterBuild", "smoke"})
    public static void projectsLinkTest(){
        assertEquals(MainPage.findProjectsLink().isDisplayed(), true);
    }

    @Test(dependsOnGroups = "loginGroup", groups = {"masterBuild", "smoke"})
    public static void issuesLinkTest(){
        assertEquals(MainPage.findIssuesLink().isDisplayed(), true);
    }

    @Test(dependsOnGroups = "loginGroup", groups = {"masterBuild", "smoke"})
    public static void boardsLinkTest(){
        assertEquals(MainPage.findBoardsLink().isDisplayed(), true);
    }

    @Test(dependsOnGroups = "loginGroup", groups = {"masterBuild", "smoke"})
    public static void TestsLinkTest(){
        assertEquals(MainPage.findTestsLink().isDisplayed(), true);
    }

    @AfterClass
    public static void tearDown(){
        MainPage.logout();
    }
}
