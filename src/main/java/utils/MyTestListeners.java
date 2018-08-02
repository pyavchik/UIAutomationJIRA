package utils;


import com.codeborne.selenide.Configuration;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.codeborne.selenide.Selenide.screenshot;


public class MyTestListeners implements ITestListener{

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Configuration.browser = "firefox";
        Configuration.timeout = 24000;
        Configuration.screenshots = false;
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        screenshot(iTestResult.getInstanceName() + "_" + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

}
