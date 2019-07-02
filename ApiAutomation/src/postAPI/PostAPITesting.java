package postAPI;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utill.RequestPayload;

public class PostAPITesting {

@Test
public void RegistrationSuccessful()
{		
	RestAssured.baseURI ="http://restapi.demoqa.com/customer/register";
	RequestSpecification request = RestAssured.given();

	/**
	 *
	{
	"UserName":"sdimpleuser2dd2011",
	"Email":"sample2ee26d9@gmail.com",
	"FirstName":"Virender",
	"LastName":"Singh",
	"Password":"password1"
	}
	 */
	JSONObject requestParams = new JSONObject();
	requestParams.put("FirstName", "Virender"); // Cast
	requestParams.put("LastName", "Singh");
	requestParams.put("UserName", "sdimpleuser2dd2011");
	requestParams.put("Password", "password1");
	requestParams.put("Email",  "sample2ee26d9@gmail.com");
	
	//System.out.println("Request Body: "+requestParams.toJSONString());
	//request.body(requestParams.toJSONString());
	String requestBody=RequestPayload.getJsonRequest("postAPI\\postAPI.json");
	request.body(requestBody);
	/*PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
	authScheme.setUserName("admin");
	authScheme.setPassword("admin");
	RestAssured.authentication = authScheme;*/
	
	Response response = request.post();
	System.out.println("Response body------");
	response.prettyPrint();
	int statusCode = response.getStatusCode();
	Assert.assertEquals(statusCode, 200);
	String successCode = response.jsonPath().get("fault");
	Assert.assertEquals( "FAULT_USER_ALREADY_EXISTS", successCode, "OPERATION_SUCCESS");
}
}