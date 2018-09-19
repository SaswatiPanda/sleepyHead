package Reporting;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportDemo {

	ExtentReports extent;
	ExtentTest logger;
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		ExtentHtmlReporter report =new ExtentHtmlReporter("./Reports/screencapture.html");
		extent = new ExtentReports();
		
		extent.attachReporter(report);
		logger = extent.createTest("Screen Capture Test");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String temp = Utilityss.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		
		extent.flush();
	
		driver.quit();
	}
		@Test
		public void test() throws IOException
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Winy\\eclipse-workspace\\Selenium Automation\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.facebook.com/");
			
			System.out.println("Title is "+driver.getTitle());
			Assert.assertTrue(driver.getTitle().contains("Winy"));
		}
			
			//System.out.println("Login to Amazon");
			
			
			//logger.log(Status.INFO, "Log in to Amazon");
			//logger.log(Status.PASS, "Title Verified");
			
			//extent.flush();
			
			//ExtentTest logger2 = extent.createTest("Logoff Test");
			//logger2.log(Status.FAIL, "Log off failed");
			
			//logger2.fail("Screenshot attached", MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\Winy\\Desktop\\ss.docx").build());
			
			
			//extent.flush();
			
		//}
	 

}
