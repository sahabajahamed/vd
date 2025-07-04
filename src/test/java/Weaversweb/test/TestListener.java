package Weaversweb.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Weaversweb.basetest.BaseTest;

public class TestListener implements ITestListener {
     private static final Logger logger = LogManager.getLogger(TestListener.class);
    
   @Override
    public void onTestStart(ITestResult result) {
        logger.info("STARTED TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("PASSED TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("FAILED TEST: " + result.getMethod().getMethodName());
        Object testInstance = result.getInstance();

        if (testInstance instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testInstance;
            String path = baseTest.captureFailedScreenshot(result.getMethod().getMethodName());
            logger.error("Screenshot saved to: " + path);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("SKIPPED TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("TEST EXECUTION FINISHED");
    }
}
    

