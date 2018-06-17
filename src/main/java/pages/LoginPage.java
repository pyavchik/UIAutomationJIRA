package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.Captcha;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private static String baseUrl = "http://jira.hillel.it:8080/login.jsp";

    private static String xpathAuiMessageError = "//div[@class='aui-message error']";

    public static void login(String login, String password) {
        open(baseUrl);
        setUpFieldsForLogin(login, password);
        findLoginButton().click();

        for (int i = 0; i < 5; i++) {
            if($$(By.xpath(xpathAuiMessageError)).size() > 0){
                if(isErrorMessagePresent() && (findAuiErrorMessage().getText().equals("Sorry, your userid is required to answer a CAPTCHA question correctly."))){
                    loginWithCaptcha(login, password);
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }


    }

    public static void loginWithCaptcha(String login, String password) {
        setUpFieldsForLogin(login, password);
        if(findCaptchaTextBox().isDisplayed()){
            findCaptchaTextBox().setValue(Captcha.getCaptcha());
        }
        findLoginButton().click();
    }

    private static void setUpFieldsForLogin(String login, String password) {
        findUsernameTextBox().setValue(login);
        findPasswordTextBox().setValue(password);
        findCheckBoxRememberMe().click();
    }

    private static boolean isErrorMessagePresent() {
        SelenideElement element = findAuiErrorMessage();
        if((element != null) ){
            element.isDisplayed();
            return true;
        } else {
            return false;

        }
    }

    /**
     * Returns the captcha text box element
     * @return
     */
    private static SelenideElement findCaptchaTextBox() {
        return $(By.id("login-form-os-captcha"));
    }

    /**
     * Returns the aui-message error element
     * @return
     */
    public static SelenideElement findAuiErrorMessage() {
        return $(By.xpath(xpathAuiMessageError));
    }

    /**
     * Returns the login text box element
     * @return
     */
    private static SelenideElement findUsernameTextBox() {
        return $(By.id("login-form-username"));
    }

    /***
     * Returns the password text box element
     * @return
     */
    private static SelenideElement findPasswordTextBox() {
        return $(By.id("login-form-password"));
    }

    /***
     * Returns the check box element
     * @return
     */
    private static SelenideElement findCheckBoxRememberMe(){
        return $(By.id("login-form-remember-me"));
    }

    /***
     * Returns the log in button element
     * @return
     */
    private static SelenideElement findLoginButton(){
        return $(By.id("login-form-submit"));
    }
}