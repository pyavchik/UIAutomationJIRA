import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

import static org.testng.Assert.assertEquals;
import static utils.PropertiesClass.getPropertyValue;


public class SmokeTest extends BaseTest{

    @BeforeTest(alwaysRun = true)
    public static void setUp(){
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
    }

    @Test(groups = {"masterBuild", "smoke"})
    public static void dashboardLinkTest(){
        assertEquals(MainPage.findDashboardLink().isDisplayed(), true);
    }

    @Test(groups = "smoke")
    public static void projectsLinkTest(){
        assertEquals(MainPage.findProjectsLink().isDisplayed(), true);
    }

    @Test(groups = "smoke")
    public static void issuesLinkTest(){
        assertEquals(MainPage.findIssuesLink().isDisplayed(), true);
    }

    @Test(groups = "smoke")
    public static void boardsLinkTest(){
        assertEquals(MainPage.findBoardsLink().isDisplayed(), true);
    }

    @Test(groups = "smoke")
    public static void TestsLinkTest(){
        assertEquals(MainPage.findTestsLink().isDisplayed(), true);
    }

    @AfterTest
    public static void tearDown(){
        MainPage.logout();
    }
}
