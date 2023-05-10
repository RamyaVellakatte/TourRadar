package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class LoginPage {


	WebDriver driver;
	ElementWait waitTime ;
	ExtentHtmlReporter reporter;
	ExtentReports extent; 
    
	//Constructor that will be automatically called as soon as the object of the class is created
	public LoginPage(WebDriver driver ) {
		this.driver=driver;
		waitTime = new ElementWait(driver);
		 reporter=new ExtentHtmlReporter("./Reports/TestCases.html");
		 extent = new ExtentReports();
	}

	//Locator for userID field
	By userId = By.id("g_email");

	//Locator for password field
	By password = By.id("g_password");

	//Locator for submit Button 
	By submitButton = By.id("g_send");

	

	

	//Locator for error message 
	By errorMessageContainer = By.id("message-sign-in");

	//Method to login button
		public void login(String userID, String pwd) throws IOException {
				try{
					waitTime.elementToBeClickable(driver.findElement(userId)).sendKeys(userID);
					waitTime.elementToBeClickable(driver.findElement(password)).sendKeys(pwd);
				} catch(Exception e) {
					 
				}
		}

	//Method to click submit button
	public void clickSubmitButton() throws IOException {
			try{
				waitTime.elementToBeClickable(driver.findElement(submitButton)).click();
				Thread.sleep(5000);
			} catch(Exception e) {
				 
			}
	}

	
	//Method to get the error message
	public String getErrorMessage() throws IOException {
		try {
			return waitTime.waitForVisibility(driver.findElement(errorMessageContainer)).getText();
		} catch(Exception e) {
			System.out.println(e.getStackTrace());
			return "null";
		}
	}	
	

}
