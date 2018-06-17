package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class ReportedByMeIssuesPage {
    public static String REPORTED_BY_ME_ISSUES = "http://jira.hillel.it:8080/browse/SUR01-15?filter=-2";
    private static String xpathReportedByMeIssue = "";
    private static String xpathMoreButton = "//span[@class='dropdown-text'][contains(text(),'More')]";
    private static String xpathDeleteButton = "//span[@class='trigger-label'][contains(text(),'Delete')]";
    private static String idDeleteButtonInDeleteWindow = "delete-issue-submit";
    private static String xpathForPopUpAllert = "//div[@class='aui-message aui-message-success success closeable shadowed aui-will-close']";
    private static String xpathForEditButton = "//span[@class='trigger-label']";
    private static String idForDescriptionTextBox = "tinymce";
    private static String idEditButtonInEditIssueWindow = "edit-issue-submit";
    private static String xpathCommentLink = "//span[@class='trigger-label'][contains(text(),'Comment')]";
    private static String idAddCommentButton = "issue-comment-add-submit";
    private static String xpathForComment = "//div[@class='action-body flooded']";
    private static String xpathForDeletedComment = "//div[@class='message-container']";
    private static String xpathForEdinCommentButton = "//span[@class='icon-default aui-icon aui-icon-small aui-iconfont-edit']";
    private static String xpathForDeleteCommentButton = "//span[@class='icon-default aui-icon aui-icon-small aui-iconfont-delete']";
    private static String idForSaveButtonInEditCommentWindow = "comment-edit-submit";
    private static String idForDeleteButtonInDeleteCommentWindow = "comment-delete-submit";


    public static void createComment(String summary, String commentText) {
        open(REPORTED_BY_ME_ISSUES);
        ReportedByMeIssuesPage.xpathReportedByMeIssue = String.format("//span[@class='issue-link-summary'][contains(text(),'%s')]", summary);
        findReportedByMeIssueLink().click();

        findCommentLink().click();
        switchTo().frame("mce_0_ifr");
        findDescriptionTextBox().setValue(commentText);
        switchTo().parentFrame();
        findAddCommentButton().click();
        refresh();
        findCommentText().shouldBe(Condition.exist);
    }

    public static String readComment() {
        String commentText = findCommentText().getText();
        return commentText;

    }

    public static void updateComment(String updatedCommentText) {
        findCommentText().hover();
        findEditCommentButton().click();
        switchTo().frame("mce_6_ifr");
        findDescriptionTextBox().clear();
        findDescriptionTextBox().setValue(updatedCommentText);
        findDescriptionTextBox().sendKeys(Keys.ENTER);
        switchTo().parentFrame();
        findSaveButtonInEditCommentWindow().click();
        refresh();
        findCommentText().shouldBe(Condition.exist);

    }

    public static void deleteComment() {
        findCommentText().hover();
        findDeleteCommentButton().click();
        findDeleteCommentButtonInDeleteCommentWindow().click();
    }

    public static void deleteIssueReportedByMe(String summary) {
        open(REPORTED_BY_ME_ISSUES);
        ReportedByMeIssuesPage.xpathReportedByMeIssue = String.format("//span[@class='issue-link-summary'][contains(text(),'%s')]", summary);
        findReportedByMeIssueLink().click();
        findMoreButton().click();
        findDeleteButton().click();
        findDeleteButtonInDeleteWindow().click();

        assertEquals($(By.xpath(xpathForPopUpAllert)).getText().contains("has been deleted"), true);

    }

    public static void updateIssueDescription(String summary, String updatedDescription) {
        open(REPORTED_BY_ME_ISSUES);
        ReportedByMeIssuesPage.xpathReportedByMeIssue = String.format("//span[@class='issue-link-summary'][contains(text(),'%s')]", summary);
        findReportedByMeIssueLink().click();
        findEditButton().click();
        switchTo().frame("mce_0_ifr");
        findDescriptionTextBox().click();
        findDescriptionTextBox().clear();
        findDescriptionTextBox().setValue(updatedDescription);
        findDescriptionTextBox().sendKeys(Keys.ENTER);
        switchTo().parentFrame();
        findEditButtonInUpdateIssueWindow().click();
        $(By.xpath(xpathForPopUpAllert)).shouldBe(Condition.exist);

    }

    /**
     * Returns the deleted comment textbox element
     *
     * @return
     */
    public static SelenideElement findDeletedCommentText() {
        return $(By.xpath(xpathForDeletedComment));
    }

    /**
     * Returns the button 'Edit" element in update issue window
     *
     * @return
     */
    private static SelenideElement findEditButtonInUpdateIssueWindow() {
        return $(By.id(idEditButtonInEditIssueWindow));
    }

    /**
     * Returns the description text box element
     *
     * @return
     */
    private static SelenideElement findDescriptionTextBox() {
        return $(By.id(idForDescriptionTextBox));
    }

    /**
     * Returns the button 'Edit" element
     *
     * @return
     */
    private static SelenideElement findEditButton() {
        return $(By.xpath(xpathForEditButton));
    }

    /**
     * Returns the button 'Delete" element in delete window
     *
     * @return
     */
    private static SelenideElement findDeleteButtonInDeleteWindow() {
        return $(By.id(idDeleteButtonInDeleteWindow));
    }

    /**
     * Returns the button 'Delete" element
     *
     * @return
     */
    private static SelenideElement findDeleteButton() {
        return $(By.xpath(xpathDeleteButton));
    }

    /**
     * Returns the button 'More" element
     *
     * @return
     */
    private static SelenideElement findMoreButton() {
        return $(By.xpath(xpathMoreButton));
    }

    /**
     * Returns the reported by me issue link element
     *
     * @return
     */
    private static SelenideElement findReportedByMeIssueLink() {
        return $(By.xpath(xpathReportedByMeIssue));
    }

    /**
     * Returns the comment button element
     *
     * @return
     */
    private static SelenideElement findCommentLink() {
        return $(By.xpath(xpathCommentLink));
    }

    /**
     * Returns the add comment button element
     *
     * @return
     */
    private static SelenideElement findAddCommentButton() {
        return $(By.id(idAddCommentButton));
    }

    /**
     * Returns the comment textbox element
     *
     * @return
     */
    private static SelenideElement findCommentText() {
        return $(By.xpath(xpathForComment));
    }


    /**
     * Returns the edit comment button element
     *
     * @return
     */
    private static SelenideElement findEditCommentButton() {
        return $(By.xpath(xpathForEdinCommentButton));
    }

    /**
     * Returns the delete comment button element
     *
     * @return
     */
    private static SelenideElement findDeleteCommentButton() {
        return $(By.xpath(xpathForDeleteCommentButton));
    }

    /**
     * Returns the save button element in edit comment window
     *
     * @return
     */
    private static SelenideElement findSaveButtonInEditCommentWindow() {
        return $(By.id(idForSaveButtonInEditCommentWindow));
    }

    /**
     * Returns the delete button element in delete comment window
     *
     * @return
     */
    private static SelenideElement findDeleteCommentButtonInDeleteCommentWindow() {
        return $(By.id(idForDeleteButtonInDeleteCommentWindow));
    }

    public static boolean at() {
        if(findEditButton().isDisplayed()){
            return true;
        } else {
            return false;
        }

    }
}
