package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.testng.Assert.assertEquals;

public class MainPage {
    private static String xpathForCreateIssueButton = "//a[@id='create_link']";
    private static String idForCreateIssueButtonAtCreateIssueWindow = "create-issue-submit";
    private static String xpathForCancelCreateIssueLinkAtCreateIssueWindow = "//a[@class='cancel']";
    private static String xpathForUserIcon = "//a[@id='header-details-user-fullname']";
    private static String idLogoutLink = "log_out";
    private static String idForSummaryTextBox = "summary";
    private static String idForDescriptionTextBox = "tinymce";
    private static String xpathForIssueCreatedAllert = "//a[@class='issue-created-key issue-link']";
    private static String xpathForMessageYouMustSpecifySummaryOfTheIssue = "//div[@class='error']";
    private static String xpathForDashboardLink = "//a[@id='home_link']";
    private static String xpathForProjectsLink = "//a[@id='browse_link']";
    private static String xpathForIssuesLink = "//a[@id='find_link']";
    private static String xpathForBoardsLink = "//a[@id='greenhopper_menu']";
    private static String xpathForTestsLink = "//a[@id='zephyr_je.topnav.tests']";

    /**
     * Returns the dashboard link
     * @return
     */
    public static SelenideElement findDashboardLink() {
        return $(By.xpath(xpathForDashboardLink));
    }

    /**
     * Returns the projects link
     * @return
     */
    public static SelenideElement findProjectsLink() {
        return $(By.xpath(xpathForProjectsLink));
    }

    /**
     * Returns the issues link
     * @return
     */
    public static SelenideElement findIssuesLink() {
        return $(By.xpath(xpathForIssuesLink));
    }

    /**
     * Returns the boards link
     * @return
     */
    public static SelenideElement findBoardsLink() {
        return $(By.xpath(xpathForBoardsLink));
    }

    /**
     * Returns the tests link
     * @return
     */
    public static SelenideElement findTestsLink() {
        return $(By.xpath(xpathForTestsLink));
    }



    /**
     * Returns the create issue button element from main page
     * @return
     */
    public static SelenideElement findCreateIssueButton() {
        return $(By.xpath(xpathForCreateIssueButton));
    }

    public static void logout(){
        findUserIcon().click();
        findLogoutLink().click();
    }

    public static void createNewIssue(String summaryText, String descriptionText) {
        findCreateIssueButton().click();
        findSummaryTextBox().setValue(summaryText);
        switchTo().frame("mce_0_ifr");
        findDescriptionTextBox().setValue(descriptionText);
        findDescriptionTextBox().sendKeys(Keys.ENTER);
        switchTo().parentFrame();
        findCreateIssueButtonAtCreateIssueWindow().click();

        assertEquals($(By.xpath(xpathForIssueCreatedAllert)).getText().contains(summaryText), true);
    }

    public static void createIssueWithoutSummary(String descriptionText) {
        findCreateIssueButton().click();
        findCreateIssueButtonAtCreateIssueWindow().click();
        assertEquals($(By.xpath(xpathForMessageYouMustSpecifySummaryOfTheIssue)).getText(), "You must specify a summary of the issue.");
        findCancelCreateIssueLink().click();
        switchTo().alert().accept();
    }

    /**
     * Returns the cancel create issue link element from create issue window
     * @return
     */
    private static SelenideElement findCancelCreateIssueLink() {
        return $(By.xpath(xpathForCancelCreateIssueLinkAtCreateIssueWindow));
    }

    /**
     * Returns the create issue button at create issue window element
     * @return
     */
    private static SelenideElement findCreateIssueButtonAtCreateIssueWindow() {
        return $(By.id(idForCreateIssueButtonAtCreateIssueWindow));
    }

    /**
     * Returns the issue description of text box element
     * @return
     */
    private static SelenideElement findDescriptionTextBox() {
        return $(By.id(idForDescriptionTextBox));
    }

    /**
     * Returns the issue summary text box element
     * @return
     */
    private static SelenideElement findSummaryTextBox() {
        return $(By.id(idForSummaryTextBox));
    }

    /**
     * Returns the user icon element
     * @return
     */
    private static SelenideElement findUserIcon() {
        return $(By.xpath(xpathForUserIcon));
    }

    /**
     * Returns the logout link element
     * @return
     */
    private static SelenideElement findLogoutLink() {
        return $(By.id(idLogoutLink));
    }

    public static boolean at(){
        if(MainPage.findCreateIssueButton().isDisplayed()){
            return true;
        } else {
            return false;
        }

    }

}
