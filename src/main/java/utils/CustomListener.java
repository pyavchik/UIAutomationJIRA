package utils;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class CustomListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        //Before every method in the Test Class
        System.out.println("beforeInvocation: " + iTestResult.getClass().getName() + "==> " + iInvokedMethod.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        //After every method in the Test Class
        System.out.println("afterInvocation: " + iTestResult.getClass().getName() + "==> " + iInvokedMethod.getTestMethod().getMethodName());
    }
}
