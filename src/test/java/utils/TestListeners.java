package utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import pages.BasePage;

public class TestListeners extends BasePage implements ITestListener {

	public void onTestFailure(ITestResult iTestResult) {
		String methodName = iTestResult.getName();
		TakesScreenshot screenshot = (TakesScreenshot) getDriver();
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("./ScreenShots/" + methodName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Test '" + methodName + "' has failed and a screenshot was taken.");
		//log.error("Test '" + methodName + "' has failed and a screenshot was taken.");
	}

}
