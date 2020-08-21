package Tests;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import BaseTest.BaseTest;
import PageObjects.HomePage;
import PageObjects.weatherPage;

public class weatherPageTest extends BaseTest {
	String cityname = "Pune";

	@Test
	public void navigatingToWeather() throws InterruptedException, IOException {

		// Clicking on weather link and waiting till loading icon disappears
		weatherPage weatherpage = homepage.clickonweatherLink();
		weatherpage.searchtext(cityname);
		assertEquals(weatherpage.popupvisibility(), true);
		System.out.println(weatherpage.findTempValue("Pune"));

		// storing wind,humidity and temperature value in an List so that we can compare
		// the values with API response later
		List<List<String>> weathercsv = new ArrayList<List<String>>();
		for (int i = 0; i < weatherpage.weathervaluesText().size(); i++) {
			System.out.println(weatherpage.weathervaluesText().get(i));
			if (i == 1) {
				// taking only the numerical value from the website
				weathercsv.addAll(
						Arrays.asList(Arrays.asList("wind", weatherpage.weathervaluesText().get(i).substring(6, 10))));
			}
			if (i == 2) {
				weathercsv.addAll(Arrays
						.asList(Arrays.asList("humidity", weatherpage.weathervaluesText().get(i).substring(10, 13))));
			}
			if (i == 3) {
				weathercsv.addAll(Arrays.asList(
						Arrays.asList("temparature", weatherpage.weathervaluesText().get(i).substring(16, 19))));
			}
		}
		// storing List data in CSV
		FileWriter csvWriter = new FileWriter("output\\seleniumtest.csv");
		for (List<String> rowData : weathercsv) {
			csvWriter.append(String.join(",", rowData));
			csvWriter.append("\n");
		}
		csvWriter.flush();
		csvWriter.close();

	}
}