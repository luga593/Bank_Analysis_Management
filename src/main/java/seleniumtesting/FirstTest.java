package seleniumtesting;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
	

	public static void main(String[] args) {
	// TODO Auto-generated method stub

	//setting the driver executable
	System.setProperty("webdriver.chrome.driver", "/Users/andrea/Desktop/Development/workspace/TopicusFinal/chromedriver");

	//Initiating your chromedriver
	WebDriver driver=new ChromeDriver();

	//Applied wait time
	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	//maximize window
	driver.manage().window().maximize();

	//open browser with desried URL
	driver.get("https://www.google.com");

	//closing the browser
	driver.close();

	}

}