import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ReportedByMeIssuesPage;

public class CreateIssueTest extends BaseTest{

    @Test
    public static void createIssueTest(){
        LoginPage.login("webinar5", "webinar5");
        MainPage.createNewIssue("summaryText test issue", "descriptionText test issue");
        MainPage.logout();
    }

    @Test(dependsOnMethods = "createIssueTest")
    public static void deleteIssueTest(){
        LoginPage.login("webinar5", "webinar5");
        ReportedByMeIssuesPage.deleteIssueReportedByMe("summaryText test issue");
        MainPage.logout();
    }

    @Test(priority = 3)
    public static void createIssueWithoutSummary(){
        LoginPage.login("webinar5", "webinar5");
        MainPage.createIssueWithoutSummary("descriptionText test issue");

    }

}
