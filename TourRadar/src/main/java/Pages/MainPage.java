package Pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class MainPage {

	
	WebDriver driver;
	ElementWait waitTime ;
	ExtentHtmlReporter reporter;
	ExtentReports extent; 
	//ExtentTest logger;
	Actions action;
	//Constructor that will be automatically called as soon as the object of the class is created

	public MainPage(WebDriver driver) {
		this.driver = driver;
		waitTime = new ElementWait(driver);
		 //reporter=new ExtentHtmlReporter("./Reports/TestCases.html");
		 extent = new ExtentReports();
		 //this.logger = logger;
		 action = new Actions(this.driver);
	}
	
	
	
	
	//Locator for login button
	By LoginBtn = By.xpath("//li[@class='js-ao-header-navigation__item js-profile js-ao-header-desktop-nav ao-header-desktop-nav ao-header-navigation__profile']");
	
	//Locator for login option
	By LoginOption = By.xpath("//a[contains(text(),'Log In')]");
		
	
	//Locator for SignUp option
	By signUpOption = By.xpath("(//a[contains(text(),'Sign Up')])[1]");
	

	//Locator for search field
	By searchField = By.xpath("//input[@id='ao-clp-clean-search__where-input']");

	//Locator for date Picker
	By datePicker = By.xpath("//input[@id='ao-clp-clean-search__when-input']");

	//Locator for Adventures field
	By adventuresField = By.xpath("//input[@name='who']");

	//Locator for search Button 
	By searchButton = By.xpath("//div[@class='ao-clp-clean-search__cta']/button[contains(text(),'Search')]");

	
	//Locator for customerName  
	By customerName = By.xpath("//ul[@class='ao-header-navigation__dropdown-list--profile-active']/h4");

	//Locator for downloadBrochureButton  
		By downloadBrochureButton = By.xpath("(//div[@class='js-serp-tour-list list']//li[1])//button");

	//Locator for downloadBrochurePopUp  
	By downloadBrochurePopUp = By.xpath("//div[@class='js-scout-component__modal-dialog scout-component__modal-dialog']//a[contains(text(),'Download Brochure')]");
					
	//Locator for emailField  
	By emailField = By.name("email");

		
	



public void mouseHoverLogin() {
		action.moveToElement(driver.findElement(LoginBtn)).build().perform();
	}
	
	//Method to click login button
	public void clickLogin() {
		driver.findElement(LoginOption).click();
	}

	//Method to click Search button
	public void clickSearchButton() {
		driver.findElement(searchButton).click();
	}	

	public void clickDownloadBrochure() {
		driver.findElement(downloadBrochureButton).click();
	}	

	public void clickDownloadBrochurePopUp() {
		driver.findElement(downloadBrochurePopUp).click();
	}	
	
	public void closeConsentPopUp() {
		driver.findElement(By.xpath("//div[@class='cn-buttons-block']/button[contains(text(),'Accept All Cookies')]")).click();
	}	
	
	
	//Method to get the CustomerName 
			public String getCustmerName() {
				try {
					return driver.findElement(customerName).getText();
				} catch(Exception e) {
					System.out.println(e.getStackTrace());
					return "null";
				}	
			}
			
		//Method to enter a value in search Field 
		public void typeSearchValue(String searchValue) throws IOException {
				try{
					waitTime.elementToBeClickable(driver.findElement(searchField)).click();
					Thread.sleep(2000);
					waitTime.elementToBeClickable(driver.findElement(searchField)).sendKeys(searchValue);
					 Thread.sleep(2000);
					//logger.log(Status.PASS, "Entered search value successfully");
				} catch(Exception e) {
					//logger.log(Status.FAIL, "Failed to enter saerch value");
					 
				}
		}
				
		//Method to enter a value in search Field 
		public void typeDateValue(String monthValue) throws IOException {
				try{
					//calendar array 
					waitTime.elementToBeClickable(driver.findElement(datePicker)).click();
					List<WebElement> months = driver.findElements(By.xpath("(//div[@class='am-month-selector__months'])/div/button"));	
					for(int i= 0; i<months.size();i++) {
						if((months.get(i).getText()).equals(monthValue)) {
							
							months.get(i).click();
							break;
						}
					}
				} catch(Exception e) {
					 
				}		
		}	
}
