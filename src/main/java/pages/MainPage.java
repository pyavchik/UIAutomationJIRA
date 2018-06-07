package pages;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class MainPage {
    private static SelenideElement element = null;
    private static String xpathForCreateIssueButton = "//a[@id='create_link']";
    private static String idForCreateIssueButtonAtCreateIssueWindow = "create-issue-submit";
    private static String xpathForCancelCreateIssueLinkAtCreateIssueWindow = "//a[@class='cancel']";
    private static String xpathForUserIcon = "//a[@id='header-details-user-fullname']";
    private static String idLogutLink = "log_out";
    private static String idForSummaryTextBox = "summary";
    private static String idForDescriptionTextBox = "tinymce";
    private static String xpathForIssueCreatedAllert = "//a[@class='issue-created-key issue-link']";
    private static String xpathForMessageYouMustSpecifySummaryOfTheIssue = "//div[@class='error']";

    /**
     * Returns the cancel create issue link element from create issue window
     * @return
     */
    public static SelenideElement cancelCreateIssueLink() {
        element = $(By.xpath(xpathForCancelCreateIssueLinkAtCreateIssueWindow));
        return element;
    }

    /**
     * Returns the create issue button element from main page
     * @return
     */
    public static SelenideElement createIssueButton() {
        element = $(By.xpath(xpathForCreateIssueButton));
        return element;
    }

    public static void logout(){
        userIcon().click();
        logoutLink().click();
    }

    public static void createNewIssue(String summaryText, String descriptionText) {
        createIssueButton().click();
        summaryTextBox().setValue(summaryText);
        switchTo().frame("mce_0_ifr");
        descriptionTextBox().setValue(descriptionText);
        descriptionTextBox().sendKeys(Keys.ENTER);
        switchTo().parentFrame();
        createIssueButtonAtCreateIssueWindow().click();

        assertEquals($(By.xpath(xpathForIssueCreatedAllert)).getText().contains(summaryText), true);
    }

    public static void createIssueWithoutSummary(String descriptionText) {
        createIssueButton().click();
        createIssueButtonAtCreateIssueWindow().click();
        assertEquals($(By.xpath(xpathForMessageYouMustSpecifySummaryOfTheIssue)).getText(), "You must specify a summary of the issue.");
        cancelCreateIssueLink().click();
        switchTo().alert().accept();
    }

    /**
     * Returns the create issue button at create issue window element
     * @return
     */
    private static SelenideElement createIssueButtonAtCreateIssueWindow() {
        element = $(By.id(idForCreateIssueButtonAtCreateIssueWindow));
        return element;
    }

    /**
     * Returns the issue description of text box element
     * @return
     */
    private static SelenideElement descriptionTextBox() {
        element = $(By.id(idForDescriptionTextBox));
        return element;
    }

    /**
     * Returns the issue summary text box element
     * @return
     */
    private static SelenideElement summaryTextBox() {
        element = $(By.id(idForSummaryTextBox));
        return element;
    }

    /**
     * Returns the user icon element
     * @return
     */
    private static SelenideElement userIcon() {
        element = $(By.xpath(xpathForUserIcon));
        return element;
    }

    /**
     * Returns the logout link element
     * @return
     */
    private static SelenideElement logoutLink() {
        element = $(By.id(idLogutLink));
        return element;
    }
}
