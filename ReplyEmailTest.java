package by.htp.testapp;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ReplyEmailTest {
	private WebDriver driver;

	@Test
	public void replyEmail() throws InterruptedException {
		
		WebElement emails = driver.findElement(By.xpath("//*[@id=\'b-letters\']/div[1]/div[2]/div"));		
		
		List<WebElement> emailsList = emails.findElements(By.xpath("//div[@class='b-datalist__item js-datalist-item b-datalist__item_unread']"));
		
		
		for(WebElement e: emailsList)
		{
			
			
			WebElement email = e.findElement(By.xpath("//a[@data-subject='Test Mail from Yuliya']"));
			//WebElement email = e.findElement(By.xpath("//div[@class='b-datalist__item__addr']"));
			System.out.println(email.getText());
			System.out.println("****************");
			email.click();
			
			//WebElement replyButton = driver.findElement(By.xpath("//*[@id=\'b-toolbar__right\']/div[3]/div[1]/div/div[2]/div[2]/div/div[1]/span"));	
			WebElement replyButton = driver.findElement(By.xpath("//*[@id=\'b-toolbar__right\']/div[3]/div[1]/div/div[2]/div[2]/div/div[1]"));
			
			replyButton.click();
			Thread.sleep(1000);			
			
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='{#aria.rich_text_area}']")));
			
			WebElement textArea = driver.findElement(By.id("tinymce"));
			textArea.sendKeys("Autoreply text from Yuliya");
			Thread.sleep(1000);
			
			driver.switchTo().defaultContent();
			
			WebElement sendReplyButton = driver.findElement(By.xpath("//*[@id=\'b-toolbar__right\']/div[4]/div/div[2]/div[1]/div/span"));		
			sendReplyButton.click();
			Thread.sleep(1000);
			
			WebElement receivedEmailsListLink = driver.findElement(By.xpath("//*[@id=\'b-nav_folders\']/div/div[1]/a/span[2]"));		
			receivedEmailsListLink.click();
			Thread.sleep(1000);	
			
//			WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='b-datalist__item js-datalist-item b-datalist__item_unread']")));
			//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			//driver.switchTo().defaultContent();
		}		
		
		Thread.sleep(1000);

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
		// driver.close();
	}

}
