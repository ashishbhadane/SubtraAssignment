package Tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class comparator {

	// This is comparison logic file. I will convert CSVs into Hashmaps
	@Test
	public void comparingCSV() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("output\\seleniumtest.csv"));
		String line = null;
		HashMap<String, String> seleniumValues = new HashMap<String, String>();
		while ((line = br.readLine()) != null) {
			String str[] = line.split("\n");
			for (int i = 0; i < str.length; i++) {
				String arr[] = str[i].split(",");
				seleniumValues.put(arr[0], arr[1]);
			}
		}

		BufferedReader br1 = new BufferedReader(
				new FileReader("D:\\SDET\\Rest Assured\\RestAssured\\resources\\new.csv"));
		String line1 = null;
		HashMap<String, String> apivalues = new HashMap<String, String>();
		while ((line1 = br1.readLine()) != null) {
			String str1[] = line1.split("\n");
			for (int i = 0; i < str1.length; i++) {
				String arr1[] = str1[i].split(",");
				apivalues.put(arr1[0], arr1[1]);
			}
		}

		// I will compare wind,temp, humidity values and fail the assertion if they are
		// more than 15% different than each other
		for (Map.Entry<String, String> entry1 : seleniumValues.entrySet()) {
			String key = entry1.getKey();
			String value1 = entry1.getValue();

			String value2 = apivalues.get(key);

			assertFalse(
					(Float.parseFloat(value1) - Float.parseFloat(value2)
							/ Math.max(Float.parseFloat(value1), Float.parseFloat(value2))) < 0.15,
					"api value is " + value2 + " and UI value from NDTV website is " + value1 + " for value " + key
							+ " difference is less than 15 %");

		}
	}
}