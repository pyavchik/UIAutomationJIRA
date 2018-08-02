import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ReportedByMeIssuesPage;
import utils.MyTestListeners;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static utils.PropertiesClass.getPropertyValue;

@Listeners(MyTestListeners.class)
public class EditIssueTest {

    private static String xpathForIssueUpdatedAllert = "//div[@class='aui-message aui-message-success success closeable shadowed aui-will-close']";
    private static String xpathForComment = "//div[@class='action-body flooded']";

    @Test(groups = "editIssue")
    public static void updateIssueDescriptionTest(){
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
        MainPage.createNewIssue("summaryText test issue", "descriptionText test issue");
        ReportedByMeIssuesPage.updateIssueDescription("summaryText test issue", "updated description");

        assertEquals($(By.xpath(xpathForIssueUpdatedAllert)).getText().contains("has been updated"), true);

        ReportedByMeIssuesPage.deleteIssueReportedByMe("summaryText test issue");
        MainPage.logout();
    }

    @Test(groups = "editIssue")
    public static void createCommentTest(){
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
        MainPage.createNewIssue("summaryText test issue", "descriptionText test issue");
        ReportedByMeIssuesPage.createComment("summaryText test issue", "Comment text");
        assertEquals(ReportedByMeIssuesPage.at(), true);
        assertEquals($(By.xpath(xpathForComment)).getText().contains("Comment text"), true);

        ReportedByMeIssuesPage.deleteIssueReportedByMe("summaryText test issue");
        MainPage.logout();
    }

    @Test(groups = "editIssue")
    public static void readCommentTest(){
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
        MainPage.createNewIssue("summaryText test issue", "descriptionText test issue");
        ReportedByMeIssuesPage.createComment("summaryText test issue", "Comment text");

        assertEquals(ReportedByMeIssuesPage.readComment().contains("Comment text"), true);

        ReportedByMeIssuesPage.deleteIssueReportedByMe("summaryText test issue");
        MainPage.logout();
    }

    @Test(groups = "editIssue")
    public static void updateCommentTest(){
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
        MainPage.createNewIssue("summaryText test issue", "descriptionText test issue");
        ReportedByMeIssuesPage.createComment("summaryText test issue", "Comment text");
        ReportedByMeIssuesPage.updateComment("updated comment text");

        assertEquals(ReportedByMeIssuesPage.readComment().contains("updated comment text"), true);

        ReportedByMeIssuesPage.deleteIssueReportedByMe("summaryText test issue");
        MainPage.logout();
    }

    @Test(groups = "editIssue")
    public static void deleteCommentTest(){
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
        MainPage.createNewIssue("summaryText test issue", "descriptionText test issue");
        ReportedByMeIssuesPage.createComment("summaryText test issue", "Comment text");
        ReportedByMeIssuesPage.deleteComment();

        assertEquals(ReportedByMeIssuesPage.findDeletedCommentText().getText().contains("There are no comments yet on this issue."), true);

        ReportedByMeIssuesPage.deleteIssueReportedByMe("summaryText test issue");
        MainPage.logout();
    }
}
