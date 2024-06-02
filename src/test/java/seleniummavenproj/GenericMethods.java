package seleniummavenproj;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericMethods {
	
	Properties obj = new Properties();
	public void explicitwait(WebDriver driver,String object,Duration time)
	{
		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(object)));
		if(we.isDisplayed())
			System.out.println("Able to find element");
		
		else
			System.out.println("Not able to find the element");
		
	}
	
	
		
	public WebDriver initiatedriver(WebDriver driver,String browser) throws IOException
	{
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\objectrepo.properties");
		obj.load(fs);
		System.out.println("In the method initiatedriver");
		if(browser.equalsIgnoreCase("Chrome"))
		{
			//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\datadriven\\browserdrivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\chromedriver1.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
			
		}
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			
		}
		else
			System.out.println("browser not detected");
		return driver;
	}


}
