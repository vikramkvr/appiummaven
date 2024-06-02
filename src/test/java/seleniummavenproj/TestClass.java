package seleniummavenproj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestClass {
	public WebDriver driver;
	@Test
	public void test()
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\chromedriver1.exe");
		
		driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		//System.out.println(System.getProperty("user.dir"));
		System.out.println("this is from test method");
		
	}

}

