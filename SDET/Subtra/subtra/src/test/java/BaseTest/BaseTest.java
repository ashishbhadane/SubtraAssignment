package BaseTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import PageObjects.HomePage;

public class BaseTest {
	private WebDriver driver;
	protected HomePage homepage;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.ndtv.com/");
		driver.manage().window().maximize();
		homepage = new HomePage(driver);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}