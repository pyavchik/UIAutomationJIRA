package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ReportedByMeIssuesPage {
    public static String REPORTED_BY_ME_ISSUES = "http://jira.hillel.it:8080/browse/SUR01-15?filter=-2";
    private static SelenideElement element = null;
    private static String xpathReportedByMeIssue = "";
    private static String xpathMoreButton = "//span[@class='dropdown-text'][contains(text(),'More')]";
    private static String xpathDeleteButton = "//span[@class='trigger-label'][contains(text(),'Delete')]";
    private static String idDeleteButtonInDeleteWindow = "delete-issue-submit";

    public static void deleteIssueReportedByMe(String summary) {
        open(REPORTED_BY_ME_ISSUES);
        ReportedByMeIssuesPage.xpathReportedByMeIssue = "//span[@class='issue-link-summary'][contains(text(),'" + summary +"')]";
        reportedByMeIssueLink().click();
        moreButton().click();
        deleteButton().click();
        deleteButtonInDeleteWindow().click();
    }

    /**
     * Returns the button 'Delete" element
     * @return
     */
    private static SelenideElement deleteButtonInDeleteWindow() {
        element = $(By.id(idDeleteButtonInDeleteWindow));
        return element;
    }

    /**
     * Returns the button 'Delete" element
     * @return
     */
    private static SelenideElement deleteButton() {
        element = $(By.xpath(xpathDeleteButton));
        return element;
    }

    /**
     * Returns the button 'More" element
     * @return
     */
    private static SelenideElement moreButton() {
        element = $(By.xpath(xpathMoreButton));
        return element;
    }

    /**
     * Returns the reported by me issue link element
     * @return
     */
    public static SelenideElement reportedByMeIssueLink() {
        element = $(By.xpath(xpathReportedByMeIssue));
        return element;
    }
}
