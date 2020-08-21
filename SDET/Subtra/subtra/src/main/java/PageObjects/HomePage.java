package PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;
	public By expandTopNav = By.xpath("//a[@id='h_sub_menu']");
	public By weatherLink = By.linkText("WEATHER");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public weatherPage clickonweatherLink() {
		driver.findElement(expandTopNav).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(weatherLink));
		driver.findElement(weatherLink).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return new weatherPage(driver);
	}

}
