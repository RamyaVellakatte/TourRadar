package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;



public class SeleniumUtil {
	
	
	WebDriver driver;
	ElementWait waitTime ;
	
	
	//Constructor that will be automatically called as soon as the object of the class is created
	public SeleniumUtil(WebDriver driver) {
		this.driver=driver;
		waitTime = new ElementWait(driver);
	}
	
	
	
	//Method to compare the String 
	public boolean getTextAndCompare(WebElement element, String text) {
		boolean status = element.getText().contentEquals(text);
		return status;
	}	

		
	
	public boolean getCurrentPageTitleAndCompare(String value) {
		String title= driver.getTitle();
		if(title.equalsIgnoreCase(value)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	
	public void reportLog(ExtentTest logger, String steps, boolean value) throws IOException {
		
		if(value== true) {
			logger.log(Status.PASS, steps);
		}else {
			logger.log(Status.FAIL, steps);
		}
			
	}	
}
