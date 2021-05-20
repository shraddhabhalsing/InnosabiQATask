package com.qa.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.base.Base;
import com.qa.page.InnosabiPage;
import com.qa.util.CommonUtil;

public class InnosabiPageTest extends Base {

	//Create Object of MainPage where OR and methods are maintained
	InnosabiPage mainPageObj=new InnosabiPage();
	public static ExtentHtmlReporter pathHtml;
	public static ExtentReports exReport;
	public static ExtentTest exLog,exLog1,exLog2,exLog3,exLog4,exLog5,exLog6,exLog7,exLog8;

	//Prerequisite steps to be executed prior executing actual TestCases

	@BeforeTest
	public void basicSetUp()
	{	DriversetUp();
		pathHtml=new ExtentHtmlReporter(System.getProperty("user.dir")+prop.getProperty("ReportPath"));
		exReport=new ExtentReports();
		exReport.attachReporter(pathHtml);
	}

	// Testcase to Launch URL 

	@Test(priority=1)
	public void launchURL(){
		String pageTitle=LaunchBrowser();
		mainPageObj.initializeWebElement();
		exLog=exReport.createTest("Verify user can launch URL", "Automation");
		if (pageTitle.equals("innosabi spark – agile innovation"))
		{
			exLog.log(Status.PASS,"URL is launched successfully") ;
		}

		else

		{
			exLog.log(Status.FAIL,"Failed to launch URL") ;
		}
	}
	
	@DataProvider
	public Object[][] passSheet()
	{
		Object[][] val=CommonUtil.readDataFromExcel("InputData");
		return val;
	}

	// TestCase to verify user is able to Log into application

	@Test(priority=2,dataProvider="passSheet")
	@Parameters({"userName","password"})
	public void loginIntoInnosabiSparkPortal(String userName ,String password )
	{
		Boolean verifyLoginSuccess=mainPageObj.login(userName, password);
		exLog1=exReport.createTest("Verify user logged in app", "Automation");
		if (verifyLoginSuccess)
		{
			exLog1.log(Status.PASS,"User logged in successfully") ;
		}
		else
		{
			exLog1.log(Status.FAIL,"Failed to logged in ") ;
		}
	}

	//Test Case to verify innovation Challenge Link is working

	@Test(priority=3)
	public void verifyinnovationChallengeLink( )
	{
		Boolean verifyinnovationChallengeLinkClicked=mainPageObj.clickInnovativeChallengeLink();
		exLog2=exReport.createTest("Verify user innovation Challenge link is clicked", "Automation");
		if (verifyinnovationChallengeLinkClicked)
		{
			exLog2.log(Status.PASS,"User successfully clicked on innovation Challenge link") ;
		}
		else
		{
			exLog2.log(Status.FAIL,"Failed to click on innovation Challenge link ") ;
		}
	}

	// TestCase to fill innovative idea details in the form and submit

	@Test(priority=4)
	@Parameters({"title","describeIdea"})
	public void fillInnovativeIdeaAndSubmitDetails(String title,String describeIdea)
	{
		Boolean verifyIdeaSubmitted=mainPageObj.fillFormAndSubmit(title,describeIdea);
		exLog3=exReport.createTest("Verify user is able to submit idea", "Automation");
		if (verifyIdeaSubmitted)
		{
			exLog3.log(Status.PASS,"User successfully submitted the idea") ;
		}
		else
		{
			exLog3.log(Status.FAIL,"Failed to submit the idea") ;
		}
	}

	// TestCase to validate comment is posted

	@Test(priority=5)
	@Parameters({"Comment"})
	public void verifyItemsInCart(String Comment )
	{
		Boolean verifyPost=mainPageObj.verifyCartItemAndCheckOutProduct(Comment);
		exLog6=exReport.createTest("Validate user is able to comment on idea", "Automation");
		if (verifyPost)
		{
			exLog6.log(Status.PASS,"User is able to add comment on idea") ;
		}
		else
		{
			exLog6.log(Status.FAIL,"Failed to add comment on idea") ;
		}
	}

	//Close browser

	@AfterTest
	public void closeBrowser() {
		exReport.flush();
		//driver.close();
	}
}
