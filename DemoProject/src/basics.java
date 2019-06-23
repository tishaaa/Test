import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.testng.annotations.Test;

public class basics {

	/**
	 * @param args
	 */
	@Test()
    public void getLocation() {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI ="http://api.dev-sheba.xyz"; 
		given().header("app-key", "11111", "app-secret","c1Dutiw4yP").
		when().get("/v1/vendors/locations").
		then().statusCode(200);
		       

	}
	
	
	
	
	/*public static int getRandomAffiliateId() {
		ArrayList<Integer> a = (ArrayList<Integer>) Arrays.asList(1, 2, 3, 4, 5 , 6);
		Random rand = new Random();
	    return a.get(rand.nextInt(a.size()));
		
		
		
	}*/

}
