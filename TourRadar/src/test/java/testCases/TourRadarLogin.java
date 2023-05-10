package testCases;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Pages.ElementWait;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.SeleniumUtil;

public class TourRadarLogin {

	static WebDriver driver;
	LoginPage login;
	MainPage mainPage;
	SeleniumUtil seleniumUtils;
	ExtentHtmlReporter reporter;
	ExtentReports extent;
	ExtentTest logger;

	@BeforeClass
	public void loadClass() {
		extent = new ExtentReports();
		reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\TestCases.html");
		extent.attachReporter(reporter);

		reporter.config().setChartVisibilityOnOpen(true);
		reporter.config().setReportName("TourRadar_TestReport");
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

	}

	@BeforeMethod
	public void launchBrowser() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		login = new LoginPage(driver);
		mainPage = new MainPage(driver);
		seleniumUtils = new SeleniumUtil(driver);
	}

	@Test(priority = 1)
	public void invalidLogin() throws InterruptedException, IOException, AWTException {

		logger = extent.createTest("TestCase_1", "Invalid Login check");

		SoftAssert softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get("https://www.tourradar.com/");
		Reporter.log("Browser Opened and url lanuched");

		seleniumUtils.reportLog(logger, "Browser Opened and url lanuched",
				seleniumUtils.getCurrentPageTitleAndCompare("TourRadar - Book Tours & Travel Packages"));
		Thread.sleep(5000);

		mainPage.mouseHoverLogin();
		mainPage.clickLogin();
		Reporter.log("Click on Login page");

		login.login("test@gmail.com", "Tourradar@202");
		Reporter.log("Entered username and password");

		login.clickSubmitButton();
		Reporter.log("Clicked on submit button");

		softAssert.assertTrue(login.getErrorMessage().equalsIgnoreCase("Wrong login data."),
				"Verify the error message");
		softAssert.assertAll();

	}

	@Test(priority = 2)
	public void loginSuccess() throws InterruptedException, IOException, AWTException {
		logger = extent.createTest("TestCase_2", "Valid Login check");

		SoftAssert softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get("https://www.tourradar.com/");
		Reporter.log("Browser Opened and url lanuched");

		seleniumUtils.reportLog(logger, "Browser Opened and url lanuched",
				seleniumUtils.getCurrentPageTitleAndCompare("TourRadar - Book Tours & Travel Packages"));
		Thread.sleep(5000);

		mainPage.mouseHoverLogin();
		mainPage.clickLogin();
		Reporter.log("Click on Login page");

		login.login("ramyavellakatta@gmail.com", "Tourradar@2023");
		Reporter.log("Entered username and password");

		login.clickSubmitButton();
		Reporter.log("Clicked on submit button");

		mainPage.mouseHoverLogin();
		softAssert.assertTrue(mainPage.getCustmerName().equalsIgnoreCase("Hi, Vellakatta"),
				"Verify the customer Name after login");
		Reporter.log("UserName verfied after login");
		softAssert.assertAll();
	}

	@Test(priority = 3)
	public void downloadBrochure() throws InterruptedException, IOException, AWTException {
		logger = extent.createTest("TestCase_3", "Verify download brochure");

		SoftAssert softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get("https://www.tourradar.com/");
		Reporter.log("Browser Opened and url lanuched");

		seleniumUtils.reportLog(logger, "Browser Opened and url lanuched",
				seleniumUtils.getCurrentPageTitleAndCompare("TourRadar - Book Tours & Travel Packages"));
		Thread.sleep(5000);

		mainPage.mouseHoverLogin();
		mainPage.clickLogin();
		Reporter.log("Click on Login page");

		login.login("ramyavellakatta@gmail.com", "Tourradar@2023");
		Reporter.log("Entered username and password");

		login.clickSubmitButton();
		Reporter.log("Clicked on submit button");

		mainPage.mouseHoverLogin();
		softAssert.assertTrue(mainPage.getCustmerName().equalsIgnoreCase("Hi, Vellakatta"),
				"Verify the customer Name after login");
		Reporter.log("Verify the customer Name after login");

		mainPage.typeSearchValue("Switzerland");
		Reporter.log("Enter search value");

		mainPage.typeDateValue("Jul");
		Reporter.log("pick the date");
		mainPage.clickSearchButton();
		Reporter.log("Click on search button");
		Thread.sleep(5000);
		mainPage.closeConsentPopUp();
		mainPage.clickDownloadBrochure();
		Thread.sleep(3000);
		mainPage.clickDownloadBrochurePopUp();
		Reporter.log("Download the brochure");
		softAssert.assertAll();

	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	@AfterClass
	public void closeAllConnections() {
		extent.flush();
	}
}
