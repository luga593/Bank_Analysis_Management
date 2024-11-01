/*
package seleniumtesting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

class Testing {
	private static WebDriver driver;

	@BeforeAll
	public static void setDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		Testing.driver = new ChromeDriver();
		Testing.driver.manage().window().maximize();
		Testing.driver.manage().deleteAllCookies();
		Testing.driver.get("http://localhost:8080/Topicus/newMainPage.jsp");
	}

	@Test
	public void testlogin() {
		// setDriver();
		// System.out.println(WebTesting.driver);
		driver.findElement(By.cssSelector("button[class='w3-button w3-black w3-padding-large w3-large w3-margin-top']"))
				.click();
		Assertions.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/Topicus/testLogin.jsp"));
		driver.findElement(By.id("login-username")).sendKeys("user1");
		driver.findElement(By.id("login-password")).sendKeys("user2");
		driver.findElement(By.cssSelector("button[class='btn-login']")).click();
		Assertions.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/Topicus/mainPage.jsp"));
		driver.findElement(By.id("log-out")).click();
		Assertions.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/Topicus/testLogin.jsp"));
		driver.findElement(By.id("login-username")).sendKeys("user1");
		driver.findElement(By.id("login-password")).sendKeys("User2");
		driver.findElement(By.cssSelector("button[class='btn-login']")).click();
		Assertions.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/Topicus/failedLogin.jsp"));
	}

	public void login() {
		// setDriver();
		Testing.driver.get("http://localhost:8080/Topicus/newMainPage.jsp");
		driver.findElement(By.cssSelector("button[class='w3-button w3-black w3-padding-large w3-large w3-margin-top']"))
				.click();
		driver.findElement(By.id("login-username")).sendKeys("user1");
		driver.findElement(By.id("login-password")).sendKeys("user2");
		driver.findElement(By.cssSelector("button[class='btn-login']")).click();

	}
	@Test
	public void testMainFunctionalities() {
		login();
		
		driver.findElement(By.linkText("Bank Statement Upload")).click();
		Assertions.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/Topicus/upload.jsp"));
		File file = new File("Sample ING.940");
		driver.findElement(By.name("fileToUpload")).sendKeys(file.getAbsolutePath());
		driver.findElement(By.cssSelector("button[value='Upload File']")).click();
		Assertions.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/Topicus/TableSingular.jsp"));
		driver.get("http://localhost:8080/Topicus/upload.jsp");
		file = new File("Test-Solution.940");
		driver.findElement(By.name("fileToUpload")).sendKeys(file.getAbsolutePath());
		driver.findElement(By.cssSelector("button[value='Upload File']")).click();
		Assertions.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/Topicus/UploadInvalidName.jsp"));
		driver.get("http://localhost:8080/Topicus/upload.jsp");
		Select select = new Select(driver.findElement(By.id("Selector")));
		select.selectByIndex(0);
		driver.findElement(By.id("SelectFilebutton")).click();
		//driver.findElement(By.cssSelector("button[id='SelectFilebutton']")).click();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.findElement(By.id("SelectFilebutton"));
		Assertions.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/Topicus/TableSingular.jsp"));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertTrue(driver.findElement(By.id("table")).isDisplayed());
		driver.get("http://localhost:8080/Topicus/mainPage.jsp");
		driver.findElement(By.linkText("Processes & Transactions")).click();
		Assertions.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/Topicus/Table.jsp"));
		driver.get("http://localhost:8080/Topicus/mainPage.jsp");
		driver.findElement(By.linkText("Chart View And Graph")).click();
		Assertions.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/Topicus/Chart.jsp"));
		driver.findElement(By.linkText("CONTACT")).click();
		Assertions.assertTrue(driver.getCurrentUrl().equals("https://topicus.nl/contact"));
		//driver.findElement(By.id("table"));
	}
	
	*/
/*
	@Test
	public void testProcessesAndTransactions() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login();
		// driver.findElement(By.linkText("Processes & Transactions")).click();
	}
	*//*

}
*/
