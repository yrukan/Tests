package by.htp.testapp;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class SendEmailTest {
	private WebDriver driver;

	@Test
	public void sendEmail() throws InterruptedException {
		
		WebElement writeLetter = driver.findElement(By.xpath("//*[@id='b-toolbar__left']/div/div/div[2]/div/a"));		
		writeLetter.click();		
		Thread.sleep(1000);
		
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		WebElement email = driver.findElement(By.xpath("//textarea[@data-original-name='To']"));
		email.sendKeys("y.rukan@mail.ru");
		Thread.sleep(1000);

		WebElement subject = driver.findElement(By.xpath("//input[@name='Subject']"));
		subject.sendKeys("Test Mail from Yuliya");
		Thread.sleep(1000);		
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='{#aria.rich_text_area}']")));		
		
		WebElement textArea = driver.findElement(By.id("tinymce"));
		textArea.sendKeys("Test text from Yuliya");
		Thread.sleep(1000);
		
		//driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='b-toolbar__right']/div[3]/div")));		
		driver.switchTo().defaultContent();

		WebElement send = driver.findElement(By.xpath("//*[@id=\'b-toolbar__right\']/div[3]/div/div[2]/div[1]/div"));		
		send.click();
		Thread.sleep(1000);
		
		WebElement sendArea = driver.findElement(By.xpath("//*[@id=\'b-compose__sent\']/div"));
		String sentEmailAttr = sendArea.getAttribute("class");
		System.out.println("*******************: " + sentEmailAttr);
		
		Assert.assertEquals(sentEmailAttr, "message-sent message-sent_IsSocialConnect", "Email is not sent");	
		
	}

	@BeforeTest
	public void loadPage() throws InterruptedException {
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "D:\\students\\yuliya\\chromedriver_win32\\chromedriver.exe");
		 */

		System.setProperty("webdriver.chrome.driver", "D:\\Java Projects\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://mail.ru/");
		Thread.sleep(1000);
	}

	@BeforeTest
	public void logIn() throws InterruptedException {

		WebElement login = driver.findElement(By.id("mailbox:login"));
		login.sendKeys("y.rukan");
		Thread.sleep(1000);

		WebElement password = driver.findElement(By.id("mailbox:password"));
		password.sendKeys("P@ssw0rd");
		Thread.sleep(1000);

		WebElement submit = driver.findElement(By.xpath("//*[@id='mailbox:submit']/input"));
		submit.click();
		Thread.sleep(1000);

		WebElement title = driver.findElement(By.id("PH_user-email"));
		String account = title.getText();
		Thread.sleep(1000);

		Assert.assertEquals(account, "y.rukan@mail.ru", "Account name is not correct");
	}

	@AfterTest
	public void finishTest() {
		driver.close();
	}

}
