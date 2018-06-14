package pages;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
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

    /**
     * Returns the cancel create issue link element from create issue window
     * @return
     */
    public static SelenideElement cancelCreateIssueLink() {
        return $(By.xpath(xpathForCancelCreateIssueLinkAtCreateIssueWindow));
    }

    /**
     * Returns the create issue button element from main page
     * @return
     */
    public static SelenideElement createIssueButton() {
        return $(By.xpath(xpathForCreateIssueButton));
    }

    public static void logout(){
        findUserIcon().click();
        findLogoutLink().click();
    }

    public static void createNewIssue(String summaryText, String descriptionText) {
        createIssueButton().click();
        findSummaryTextBox().setValue(summaryText);
        switchTo().frame("mce_0_ifr");
        findDescriptionTextBox().setValue(descriptionText);
        findDescriptionTextBox().sendKeys(Keys.ENTER);
        switchTo().parentFrame();
        findCreateIssueButtonAtCreateIssueWindow().click();

        assertEquals($(By.xpath(xpathForIssueCreatedAllert)).getText().contains(summaryText), true);
    }

    public static void updateIssueDescription(String updatedDescription) {

    }

    public static void createIssueWithoutSummary(String descriptionText) {
        createIssueButton().click();
        findCreateIssueButtonAtCreateIssueWindow().click();
        assertEquals($(By.xpath(xpathForMessageYouMustSpecifySummaryOfTheIssue)).getText(), "You must specify a summary of the issue.");
        cancelCreateIssueLink().click();
        switchTo().alert().accept();
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
}
