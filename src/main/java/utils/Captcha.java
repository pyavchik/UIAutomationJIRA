package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.j.antigate.CapchaBypass;
import utils.j.antigate.CapchaUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Captcha {

    private final static String IMAGE_NAME = "./captcha.png";
    private final static String ACCOUNT_KEY_ANTI_CAPCHA = "298ac7ee43930d7b98a4fb6e6e687683";

    public static String getCaptcha(){
        try {
            takeScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String answer = "0";
        try {
            File f = new File(IMAGE_NAME);
            FileInputStream is = new FileInputStream(f);
            String balance = CapchaUtils.getBalance(ACCOUNT_KEY_ANTI_CAPCHA);
            answer = CapchaBypass.CapchaAnswer(is, ACCOUNT_KEY_ANTI_CAPCHA, "NO", "NO", "NO");
        } catch (ArrayIndexOutOfBoundsException | IOException | InterruptedException e) {

        }

        return answer;
    }

    public static void takeScreenshot() throws IOException {
        File screenShotFile = ((TakesScreenshot)getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            BufferedImage fullImage = ImageIO.read(screenShotFile);
            BufferedImage eleScreenshot= fullImage.getSubimage(610*2, 450*2, 200*2, 70*2);
            ImageIO.write(eleScreenshot, "png", screenShotFile);
            FileUtils.copyFile(screenShotFile, new File(IMAGE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
