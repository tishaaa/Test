import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.eventFrom;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyData;

public class locationList {
	//public static ResponseBody response;
    //public static String jsonAsString;
	@Test
	public void test1() {
		// TODO Auto-generated method stub
		RestAssured.baseURI ="http://api.dev-sheba.xyz";
		//RestAssured.defaultParser = Parser.JSON;
		Response response =
		given()
		.header("app-key","111111")
		.header("app-secret","c1Dutiw4yP")
		.when()
		.get("/v1/vendors/locations")
		.then().assertThat().statusCode(200)
		.contentType(ContentType.JSON)
		.extract().response();

		String names = response.jsonPath().getString("data.locations.name");
		//System.out.println(names);
		String location = response.jsonPath().getString("data[0].locations.name[0]");
		String lat = response.jsonPath().getString("data[0].locations.lat[0]");
		String lng = response.jsonPath().getString("data[0].locations.lng[0]");

        Response response1 =
                given()
                        .header("app-key","111111")
                        .header("app-secret","c1Dutiw4yP")
                        .queryParam("lat",lat)
                        .queryParam("lng",lng)
                        .when()
                        .get("/v1/vendors/categories")
                        .then().assertThat().statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().response();

        String mastercategory = response1.jsonPath().getString("data[0].id");
		//System.out.println(mastercategory);
       // System.out.println(mastercategories);
        StringBuilder secondarycategoryurl = new StringBuilder()
				.append("/v1/vendors/categories/")
				.append(mastercategory)
				.append("/secondaries?lat=")
				.append(lat)
				.append("&lng=")
				.append(lng);
        Response response2 =
                given()
                        .header("app-key","111111")
                        .header("app-secret","c1Dutiw4yP")
                        .when()
                        .get(secondarycategoryurl.toString())
                        .then().assertThat().statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().response();

		String secondarycategory = response2.jsonPath().getString("data[0].id");
		//System.out.println(secondarycategory);

		StringBuilder serviceurl = new StringBuilder()
				.append("/v1/vendors/categories/")
				.append(secondarycategory)
				.append("/services?lat=")
				.append(lat)
				.append("&lng=")
				.append(lng);
		Response response3 =
				given()
						.header("app-key","111111")
						.header("app-secret","c1Dutiw4yP")
						.when()
						.get(serviceurl.toString())
						.then().assertThat().statusCode(200)
						.contentType(ContentType.JSON)
						.extract().response();

		String serviceid = response3.jsonPath().getString("data[0].id");
		String servicetype = response3.jsonPath().getString("data[0].type");
		String serviceminquantity = response3.jsonPath().getString("data[0].min_quantity");
		String servicequestion = response3.jsonPath().getString("data[0].questions");
		//System.out.println(serviceid);
		//System.out.println(servicequestion);
		String options = "";

		if(servicetype.equals("Fixed"))
		{
			options = "[]";
		}
		else
		{

		}
		StringBuilder servicelist = new StringBuilder()
				.append("[{\"id\":")
				.append(serviceid)
				.append(",\"quantity\":")
				.append(serviceminquantity)
				.append(",\"option\":")
				.append(options)
				.append("}]");
		Response response4 =
				given()
						.header("app-key","111111")
						.header("app-secret","c1Dutiw4yP")
						.queryParam("services",servicelist.toString())
						.queryParam("lat",lat)
						.queryParam("lng",lng)
						.when()
						.get("/v1/vendors/partners")
						.then().assertThat().statusCode(200)
						.contentType(ContentType.JSON)
						.extract().response();

		String partnerid = response4.jsonPath().getString("data[0].id");
		//System.out.println(partnerid);
		Response response5 =
				given()
						.header("app-key","111111")
						.header("app-secret","c1Dutiw4yP")
						.queryParam("category",secondarycategory)
						.queryParam("partner",partnerid)
						.queryParam("limit",2)
						.when()
						.get("/v1/vendors/times")
						.then().assertThat().statusCode(200)
						.contentType(ContentType.JSON)
						.extract().response();

		String scheduledate = response5.jsonPath().getString("data[0].date");
		String scheduletime = response5.jsonPath().getString("data[0].slots[0].key");
		//System.out.println(scheduledate);
		//System.out.println(scheduletime);
		Map<String,String> requestBody = new HashMap<String,String>();
		requestBody.put("lat", lat);
		requestBody.put("lng", lng);
		requestBody.put("services", servicelist.toString());
		requestBody.put("partner", partnerid);
		requestBody.put("mobile", "01790233889");
		requestBody.put("date", scheduledate);
		requestBody.put("time", scheduletime);
		requestBody.put("address", "Mohammadpur");
		requestBody.put("name", "Tisha");
		Response response6 =
		given()
				.header("app-key","111111")
				.header("app-secret","c1Dutiw4yP")
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("/v1/vendors/orders")
				.then().assertThat().statusCode(200)
				.contentType(ContentType.JSON)
				.extract().response();
		//System.out.println(response6.jsonPath().getString("data"));
		String successCode = response6.jsonPath().get("data.message");
		//System.out.println(successCode);
		Assert.assertEquals(  successCode, "SUCCESSFUL");
		/*System.out.println(lat);
		System.out.println(lng);
		System.out.println(servicelist.toString());
		System.out.println(partnerid);
		System.out.println(scheduledate);
		System.out.println(scheduletime);*/













	}


}
