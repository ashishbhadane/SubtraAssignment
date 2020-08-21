package subtra.RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITest {

	@Test
	public void weatherAPI() throws IOException {
		RestAssured.baseURI = "http://api.openweathermap.org/data/2.5";
		Response response = given().

				when().queryParam("q", "Pune").queryParam("units", "metric")
				.queryParam("appid", "a354a2f87f14b8b01d0a3fe513ce5255").get("/weather").
				// get("http://api.openweathermap.org/data/2.5/weather?q=Pune&units=metric&appid=a354a2f87f14b8b01d0a3fe513ce5255").

				then().contentType(ContentType.JSON).extract().response();

		// parsing response and storing values in variables
		float strresponse = (float)response.path("main.temp");
		int humidity = response.path("main.humidity");
		float windspeed = response.path("wind.speed");

		// storing weather variables in csv file to compare
		List<List<String>> rows = Arrays.asList(Arrays.asList("wind", String.valueOf(windspeed)),
				Arrays.asList("humidity", String.valueOf(humidity)),
				Arrays.asList("temparature", String.valueOf(strresponse)));
		FileWriter csvWriter = new FileWriter("resources\\new.csv");
		for (List<String> rowData : rows) {
			csvWriter.append(String.join(",", rowData));
			csvWriter.append("\n");
		}
		csvWriter.flush();
		csvWriter.close();

	}

}
