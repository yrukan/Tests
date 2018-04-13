package by.htp.testapp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {

	private WebDriver driver;

	@BeforeTest
	
	public void loadPage() throws InterruptedException {
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "D:\\students\\yuliya\\chromedriver_win32\\chromedriver.exe");
		 */

		System.setProperty("webdriver.chrome.driver", "D:\\Java Projects\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://mail.ru/");
		Thread.sleep(2000);
	}

	@Test
	public void logIn() throws InterruptedException {		

		WebElement login = driver.findElement(By.id("mailbox:login"));
		login.sendKeys("TATHTP");
		Thread.sleep(2000);

		WebElement password = driver.findElement(By.id("mailbox:password"));
		password.sendKeys("Klopik123");
		Thread.sleep(2000);

		WebElement submit = driver.findElement(By.xpath("//*[@id='mailbox:submit']/input"));
		submit.click();
		Thread.sleep(2000);

		/*
		 * driver.get("https://e.mail.ru/messages/inbox/?back=1"); WebElement
		 * myDynamicElement = (new WebDriverWait(driver,
		 * 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("PH_user-email")
		 * ));
		 */

		WebElement title = driver.findElement(By.id("PH_user-email"));
		String account = title.getText();
		Thread.sleep(2000);				

		Assert.assertEquals(account, "tathtp@mail.ru", "Account name is not correct");
	}
	
	@AfterTest
	public void finishTest() {
	driver.close();
	}
}
