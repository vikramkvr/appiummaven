package seleniummavenproj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

@Test
public class TestCases {
	
	Properties obj = new Properties();
	GenericMethods gm = new GenericMethods();
	DataHandler data = new DataHandler();
	ExtentReports report = new ExtentReports();
	ExtentTest etest;
	Logger log = Logger.getLogger(System.getProperty("user.dir")+"\\src\\test\\resources\\logging.logs");
	
	
	@BeforeTest
	public void defaultthings() throws IOException
	{
		
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\objectrepo.properties");
		obj.load(fs);
		
		
	}
	

	public void firsttest() throws IOException
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() +" is under execution----------------------------------------------");
		WebDriver driver = null;
		driver = gm.initiatedriver(driver, "chrome");
		System.out.println("testing...");
		String url = data.getdata("Sheet1", "firsttest", "URL");
		System.out.println(url);
		driver.get(url);
		System.out.println("browser opened");
		//maximizing browser
		driver.manage().window().maximize();
		System.out.println("browser maximized");
		
	}
	

	public void secondtest() throws IOException
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() +"is under execution----------------------------------------------");
		WebDriver driver = null;
		
		driver = gm.initiatedriver(driver, "chrome");
		System.out.println("testing...");
		driver.get("https://www.google.com");
		System.out.println("browser opened");
		//maximizing browser
		driver.manage().window().maximize();
		System.out.println("browser maximized");
	
	}
	

	public void thirdtest() throws IOException
	{
		//etest=report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		etest = report.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.info("this is the method "+Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() +"is under execution----------------------------------------------");
		WebDriver driver = null;
		
		driver = gm.initiatedriver(driver, "chrome");
		System.out.println("testing...");
		String url = data.getdata(obj.getProperty("sheetname"),Thread.currentThread().getStackTrace()[1].getMethodName(), "url");
		driver.get(url);
		System.out.println("browser opened");
		//maximizing browser
		driver.manage().window().maximize();
		System.out.println("browser maximized");
		//etest.log(LogStatus.PASS,etest.addScreenCapture(capture(driver))+ "Test Passed");
		etest.log(Status.PASS,etest.addScreenCaptureFromBase64String(capture(driver))+ "Test Passed");
		
	}
	
	public void fourthtest() throws IOException
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() +"is under execution----------------------------------------------");
		WebDriver driver = null;
		
		driver = gm.initiatedriver(driver, "chrome");
		System.out.println("testing...");
		driver.get("https://www.google.com");
		System.out.println("browser opened");
		//maximizing browser
		driver.manage().window().maximize();
		System.out.println("browser maximized");
	
	}
	
	public void fifthtest() throws IOException
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() +"is under execution----------------------------------------------");
		WebDriver driver = null;
		
		driver = gm.initiatedriver(driver, "chrome");
		System.out.println("testing...");
		driver.get("https://www.google.com");
		System.out.println("browser opened");
		//maximizing browser
		driver.manage().window().maximize();
		System.out.println("browser maximized");
	
	}
	
	public void sixthtest() throws IOException
	{
		//etest=report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		etest=report.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.info("this is the method "+Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() +"is under execution----------------------------------------------");
		WebDriver driver = null;
		
		driver = gm.initiatedriver(driver, "chrome");
		
		String url = data.getdata(obj.getProperty("sheetname"),Thread.currentThread().getStackTrace()[1].getMethodName(), "url");
		driver.get(url);
		
		etest.log(Status.INFO, "browser opened");
		//maximizing browser
		driver.manage().window().maximize();
		System.out.println("browser maximized");
		etest.log(Status.PASS,etest.addScreenCaptureFromBase64String(capture(driver))+ "Test Passed");
	}
	
	
	public static String capture(WebDriver wd) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) wd;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest =System.getProperty("user.dir")+"\\src\\test\\resources\\"+getcurrentdateandtime()+".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
		}

	private static long getcurrentdateandtime() {
		// TODO Auto-generated method stub
		//Getting the current date
	      Date date = new Date();
	      //This method returns the time in millis
	      long timeMilli = date.getTime();
	      System.out.println("Time in milliseconds using Date class: " + timeMilli);

	      //creating Calendar instance
	      Calendar calendar = Calendar.getInstance();
	      //Returns current time in millis
	      long timeMilli2 = calendar.getTimeInMillis();
	      System.out.println("Time in milliseconds using Calendar: " + timeMilli2);
	      
	      //Java 8 - toEpochMilli() method of ZonedDateTime
	      System.out.println("Getting time in milliseconds in Java 8: " + 
	      ZonedDateTime.now().toInstant().toEpochMilli());
		return ZonedDateTime.now().toInstant().toEpochMilli();
		
		
	}

	@AfterTest
	public void closemethod()
	{
		
		System.out.println("browser closed");
		//report.endTest(etest);
		report.flush();
	}

}
