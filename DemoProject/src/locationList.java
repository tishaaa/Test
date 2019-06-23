import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyData;

public class locationList {
	public static ResponseBody response;
    public static String jsonAsString;
	@Test
	public void test1() {
		// TODO Auto-generated method stub
		RestAssured.baseURI ="http://api.dev-sheba.xyz"; 
		response = 
		given()
		.header("app-key","111111","app-secret","c1Dutiw4yP")
		.when()
		.get("/v1/vendors/locations")
		.then().assertThat().statusCode(200)
		.contentType(ContentType.JSON)
		.extract().response();
		 jsonAsString = response.asString();
		 //System.out.println(jsonAsString);
		
		       

	}
	
	
	
}
