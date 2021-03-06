package gridtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridExecutionJenkins {
	WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		new DesiredCapabilities();
		DesiredCapabilities capability = DesiredCapabilities.firefox();		
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
		//driver = new FirefoxDriver(capability);
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseUrl + "/?gws_rd=ssl");
	}

	@Test
	public void testGoogle() throws Exception {
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("testing");
		driver.findElement(By.name("btnG")).click();
		driver.findElement(
				By.linkText("Software testing - Wikipedia, the free encyclopedia"))
				.click();
		Thread.sleep(10000);
		assertEquals("Software testing - Wikipedia, the free encyclopedia",
				driver.getTitle());
	}
	
	@Test
	public void testLinkedIn() throws Exception {
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("linkedin");
		driver.findElement(By.name("btnG")).click();
		driver.findElement(
				By.linkText("LinkedIn: World's Largest Professional Network"))
				.click();
		Thread.sleep(10000);
		assertEquals("World�s Largest Professional Network | LinkedIn",
				driver.getTitle());
	}
	
	@Test
	public void testSmoke() throws Exception {
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("smoke testing");
		driver.findElement(By.name("btnG")).click();
		driver.findElement(
				By.linkText("Smoke testing (software) - Wikipedia, the free encyclopedia"))
				.click();
		Thread.sleep(10000);
		assertEquals("Smoke testing (software) - Wikipedia, the free encyclopedia",
				driver.getTitle());
	}
	
	@Test
	public void testRegression() throws Exception {
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("regression testing");
		driver.findElement(By.name("btnG")).click();
		driver.findElement(
				By.xpath("(//a[contains(text(),'Regression testing - Wikipedia, the free encyclopedia')])[2]"))
				.click();
		Thread.sleep(10000);
		assertEquals("Regression testing - Wikipedia, the free encyclopedia",
				driver.getTitle());
	}
	
	@Test
	public void testAutomation() throws Exception {
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("Automation testing");
		driver.findElement(By.name("btnG")).click();
		driver.findElement(
				By.xpath("(//a[contains(text(),'Test automation - Wikipedia, the free encyclopedia')])[2]"))
				.click();
		Thread.sleep(10000);
		assertEquals("Test automation - Wikipedia, the free encyclopedia",
				driver.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
