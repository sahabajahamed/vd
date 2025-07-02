package Weaversweb.test;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Weaversweb.utils.LoggerUtil;

import Weaversweb.basetest.BaseTest;

public class TestListener implements ITestListener {
    
   @Override
    public void onTestStart(ITestResult result) {
        LoggerUtil.info("STARTED TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtil.info("PASSED TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtil.error("FAILED TEST: " + result.getMethod().getMethodName());
        Object testInstance = result.getInstance();

        if (testInstance instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testInstance;
            String path = baseTest.captureFailedScreenshot(result.getMethod().getMethodName());
            LoggerUtil.error("Screenshot saved to: " + path);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtil.info("SKIPPED TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerUtil.info("TEST EXECUTION FINISHED");
    }
}
    

