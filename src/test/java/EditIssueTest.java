import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ReportedByMeIssuesPage;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static utils.PropertiesClass.getPropertyValue;

public class EditIssueTest extends BaseTest{

    private static String xpathForIssueUpdatedAllert = "//div[@class='aui-message aui-message-success success closeable shadowed aui-will-close']";

    @BeforeClass
    public static void setUp(){
        Configuration.timeout = 15000;
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
        MainPage.createNewIssue("summaryText test issue", "descriptionText test issue");
    }

    @Test
    public static void updateIssueDescriptionTest(){
        ReportedByMeIssuesPage.updateIssueDescription("summaryText test issue", "updated description");
        assertEquals($(By.xpath(xpathForIssueUpdatedAllert)).getText().contains("has been updated"), true);
    }

    @Test(dependsOnMethods = "updateIssueDescriptionTest")
    public static void createCommentTest(){
        ReportedByMeIssuesPage.createComment("summaryText test issue", "Comment text");
    }

    @Test(dependsOnMethods = "createCommentTest")
    public static void readCommentTest(){
        ReportedByMeIssuesPage.readComment();
    }

    @Test(dependsOnMethods = "readCommentTest")
    public static void updateCommentTest(){
        ReportedByMeIssuesPage.updateComment("updated comment text");
    }

    @Test(dependsOnMethods = "updateCommentTest")
    public static void deleteCommentTest(){
        ReportedByMeIssuesPage.deleteComment();
    }

    @AfterClass
    public static void tearDown(){
        ReportedByMeIssuesPage.deleteIssueReportedByMe("summaryText test issue");
        MainPage.logout();
    }

}
