import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class post {
	@Test
	public void test1() {
		// TODO Auto-generated method stub
		String remembertoken = "3UeQw0wEvDjekl9gWRcigCLqWWJrds5SpqrbDlqwVsYWzSB1HQTDAxNJaz4GplyzRe46FkhPXXMcUOtZRllUw6ACnPuwYWFN1CSIai7HDgnLv3tX6vfE6YnBtOfJbxntXrVm7sZ5I9WxQa7RZRW14l3J69kv3pJjqThzuHKNiLzYqhoBafS6HNB7nbuvcgNsIn3ocmo2IGCngcFSxTACD2PjWENxJ0uSlcYxElT9qKOOx9cIgw28P9PxPzbSloX";
		String lat_data = "23.7897222";
		String lng_data = "90.41962439999998";
		RestAssured.baseURI ="http://api.dev-sheba.xyz"; 
		http://api.dev-sheba.xyz/v2/affiliates/34/lite-sp-pending
		given().
		       body("{"+"\"lat\":23.789711398931555," + 
		       		"\"lng:\"90.41962120682001," + 
		       		"\"remember_token\":\"3UeQw0wEvDjekl9gWRcigCLqWWJrds5SpqrbDlqwVsYWzSB1HQTDAxNJaz4GplyzRe46FkhPXXMcUOtZRllUw6ACnPuwYWFN1CSIai7HDgnLv3tX6vfE6YnBtOfJbxntXrVm7sZ5I9WxQa7RZRW14l3J69kv3pJjqThzuHKNiLzYqhoBafS6HNB7nbuvcgNsIn3ocmo2IGCngcFSxTACD2PjWENxJ0uSlcYxElT9qKOOx9cIgw28P9PxPzbSloX\""+"}")
		       .when().
		       post(generateResource("3194","34")).
		       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		       body("partners[0].moderation_status", equalTo("pending")).and().
		       body("partners[0].location", equalTo("Gulshan"));
		       

	}
	public static String generateResource(String lite_partner_id, String moderator_id)
	{
		return "/v2/affiliates/"+moderator_id+"/moderate/"+lite_partner_id+"/accept";
	}

}
