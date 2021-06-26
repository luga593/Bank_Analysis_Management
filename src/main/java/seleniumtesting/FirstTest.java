package seleniumtesting;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirstTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// setting the driver executable
		// System.setProperty("webdriver.chrome.driver", "chromedriver");
		System.setProperty("webdriver.chrome.driver",
				"chromedriver.exe");
		// Initiating your chromedriver
		//System.out.println(new File(System.getProperty("C:\\Users\\victo\\Desktop\\Technical Computer Science\\Year1\\Module 4\\Topicus\\src\\main\\java\\seleniumtesting\\FirstTest.java")).getParent());
		WebDriver driver = new ChromeDriver();
		// Applied wait time
		// driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		// maximize window
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		// open browser with desried URL
		driver.get("http://localhost:8080/Topicus/newMainPage.jsp");
		driver.findElement(By.cssSelector("button[class='w3-button w3-black w3-padding-large w3-large w3-margin-top']"))
				.click();
		if (driver.getCurrentUrl().equals("http://localhost:8080/Topicus/testLogin.jsp")) {
			//System.out.println("yes");
		}
		driver.findElement(By.id("login-username")).sendKeys("user1");
		driver.findElement(By.id("login-password")).sendKeys("user2");
		driver.findElement(By.cssSelector("button[class='btn-login']")).click();
		if (driver.getCurrentUrl().equals("http://localhost:8080/Topicus/mainPage.jsp")) {
			//System.out.println("yes");
		}
		driver.findElement(By.linkText("Processes & Transactions")).click();
		driver.findElement(By.xpath("//button[text()='Get table']")).click();
		System.out.println(driver.findElement(By.id("table")).isDisplayed());
		if(driver.findElement(By.id("table")).isDisplayed()) {
			System.out.println("yes");
		}
		if (driver.getCurrentUrl().equals("http://localhost:8080/Topicus/Table.jsp")) {
			//System.out.println("yes");
		}
		driver.get("http://localhost:8080/Topicus/mainPage.jsp");
		driver.findElement(By.linkText("Bank Statement Upload")).click();
		if (driver.getCurrentUrl().equals("http://localhost:8080/Topicus/upload.jsp")) {
			//System.out.println("yes");
		}
		driver.findElement(By.className("loginButton"));
		driver.get("http://localhost:8080/Topicus/mainPage.jsp");
		
		;
		// Assert.assertTrue(((RemoteWebDriver)
		// driver).findElementByCssSelector("button[class='w3-button w3-black
		// w3-padding-large w3-large w3-margin-top']\")").isEnabled());
		// WebElement searchIcon = driver.findElement(By.name("btnK"));
		// searchIcon.click();
		// driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		// WebElement searchIcon = driver.findElement(By.name("btnK"));
		// searchIcon.click();
		// closing the browser
		driver.close();

	}

}