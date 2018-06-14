import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ReportedByMeIssuesPage;

import static utils.PropertiesClass.getPropertyValue;

public class CreateIssueTest extends BaseTest{

    @Test
    public static void createAndDeleteIssueTest(){
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
        MainPage.createNewIssue("summaryText test issue", "descriptionText test issue");
        ReportedByMeIssuesPage.deleteIssueReportedByMe("summaryText test issue");
        MainPage.logout();
    }

    @Test(priority = 3)
    public static void createIssueWithoutSummary(){
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
        MainPage.createIssueWithoutSummary("descriptionText test issue");

    }

}
