package PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class weatherPage {
	private WebDriver driver;
	public By searchTextBox = By.xpath("//input[@id='searchBox']");
	public By cityCheckbox = By.xpath("//input[@id='Pune']");
	public By mapentry = By.xpath("//div[contains(text(),'Pune')]");
	public By weatherpopup = By.xpath("//div[@class='leaflet-popup-content']");
	public By resetButton = By.xpath("//span[@title='Reset to default']");
	public By tempValue = By
			.xpath("//div[@title='Pune']/div[@class='temperatureContainer']/span[@class='tempRedText']");
	public By weathervalues = By.xpath("//div[@class='leaflet-popup-content']//child::b");
	public By loadingText = By.xpath("//div[@id='loading']");

	public weatherPage(WebDriver driver) {
		this.driver = driver;
	}

	public void searchtext(String cityname) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingText));
		driver.findElement(resetButton).click();
		driver.findElement(cityCheckbox).click();
		driver.findElement(mapentry).click();
	}

	public boolean popupvisibility() {
		return driver.findElement(weatherpopup).isDisplayed();
	}

	public String findTempValue(String cityname) {
		String temp = driver.findElement(tempValue).getText();
		temp = temp.substring(0, temp.length() - 1);
		return temp;
	}

	public List<String> weathervaluesText() {
		List<String> weathervaluesinText = new ArrayList<String>();
		List<WebElement> weatherpopupvalues = driver.findElements(weathervalues);
		for (WebElement webElement : weatherpopupvalues) {
			weathervaluesinText.add(webElement.getText());
		}
		return weathervaluesinText;

	}
}
