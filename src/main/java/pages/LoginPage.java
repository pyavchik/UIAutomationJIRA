package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.Captcha;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private static String baseUrl = "http://jira.hillel.it:8080/login.jsp";
    private static SelenideElement element = null;
    private static String xpathAuiMessageError = "//div[@class='aui-message error']";

    public static void login(String login, String password) {
        open(baseUrl);
        setUpFieldsForLogin(login, password);
        loginButton().click();

        boolean whatchDog = true;
        while (whatchDog){
            if($$(By.xpath(xpathAuiMessageError)).size() > 0){
                if(isErrorMessagePresent() && (auiErrorMessage().getText().equals("Sorry, your userid is required to answer a CAPTCHA question correctly."))){
                    loginWithCaptcha(login, password);
                } else {
                    whatchDog = false;
                }
            } else {
                whatchDog = false;
            }
        }
    }

    public static void loginWithCaptcha(String login, String password) {
        setUpFieldsForLogin(login, password);
        captchaTextBox().setValue(Captcha.getCaptcha());
        loginButton().click();
    }

    private static void setUpFieldsForLogin(String login, String password) {
        usernameTextBox().setValue(login);
        passwordTextBox().setValue(password);
        checkBoxRememberMe().click();
    }

    private static boolean isErrorMessagePresent() {
        if((auiErrorMessage() != null) ){
            return true;
        } else {
            return false;

        }
    }

    /**
     * Returns the captcha text box element
     * @return
     */
    private static SelenideElement captchaTextBox() {
        element = $(By.id("login-form-os-captcha"));
        return element;
    }

    /**
     * Returns the aui-message error element
     * @return
     */
    public static SelenideElement auiErrorMessage() {
        element = $(By.xpath(xpathAuiMessageError));
        return element;
    }

    /**
     * Returns the login text box element
     * @return
     */
    private static SelenideElement usernameTextBox() {
        element = $(By.id("login-form-username"));
        return element;
    }

    /***
     * Returns the password text box element
     * @return
     */
    private static SelenideElement passwordTextBox() {
        element = $(By.id("login-form-password"));
        return element;
    }

    /***
     * Returns the check box element
     * @return
     */
    private static SelenideElement checkBoxRememberMe(){
        element = $(By.id("login-form-remember-me"));
        return element;
    }

    /***
     * Returns the log in button element
     * @return
     */
    private static SelenideElement loginButton(){
        element = $(By.id("login-form-submit"));
        return element;
    }
}