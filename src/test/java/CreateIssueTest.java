import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ReportedByMeIssuesPage;
import utils.MyTestListeners;

import static utils.PropertiesClass.getPropertyValue;

@Listeners(MyTestListeners.class)
public class CreateIssueTest {

    @Test(groups = "createIssue")
    public static void createAndDeleteIssueTest(){
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
        MainPage.createNewIssue("summaryText test issue", "descriptionText test issue");
        ReportedByMeIssuesPage.deleteIssueReportedByMe("summaryText test issue");
        MainPage.logout();
    }

    @Test(groups = "createIssue")
    public static void createIssueWithoutSummary(){
        LoginPage.login(getPropertyValue("login"), getPropertyValue("password"));
        MainPage.createIssueWithoutSummary("descriptionText test issue");

    }

}
