package documentation;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utill.RequestPayload;
public class API_Doc {
	
	void getSample(){
	// Specify the base URL to the RESTful web service
	RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
	 // Get the RequestSpecification of the request that you want to sent
	 // to the server. The server is specified by the BaseURI that we have
	 // specified in the above step.
	 RequestSpecification httpRequest = RestAssured.given();
	 // Make a request to the server by specifying the method Type and the method URL.
	 // This will return the Response from the server. Store the response in a variable.
	 Response response = httpRequest.request(Method.GET, "/Hyderabad");
	 // Now let us print the body of the message to see what response
	 // we have recieved from the server
	 String responseBody = response.getBody().asString();
	 System.out.println("Response Body is =>  " + responseBody);
	}
	
	void postSmple(){
		RestAssured.baseURI ="http://restapi.demoqa.com/customer/register";
		RequestSpecification requestSpecification = RestAssured.given();
		JSONObject requestBody = new JSONObject();
		requestBody.put("FirstName", "Virender"); // Cast
		requestBody.put("LastName", "Singh");
		requestBody.put("UserName", "sdimpleuser2dd2011");
		requestBody.put("Password", "password1");
		requestBody.put("Email",  "sample2ee26d9@gmail.com");
		requestSpecification.body(requestBody);
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("admin");
		authScheme.setPassword("admin");
		RestAssured.authentication = authScheme;
		Response response = requestSpecification.post();
		response.prettyPrint();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String successCode = response.jsonPath((JsonPathConfig) response.getBody()).get("fault");
		Assert.assertEquals( "FAULT_USER_ALREADY_EXISTS", successCode, "OPERATION_SUCCESS");
		
	}
}

/***
Thinks which we verify into API response:
we can verify the Status code, Status message, Headers and even the Body of the response.


What is client server Architecture:
A Machine, who wants to get the specific information. Also, called as Client in Client-Server context.
A Provider Machine who provides the information on the behalf of sent request. Also, called as Server in Client-Server context.

Protocol between Client and Server:
A medium for communication, specifically a protocol is language for two systems to interact with each other. Also called communication protocol

Types of protocol:
HTTP:
HTTP is based on client and server model. 
HTTP is used for making a connection between the web client and web server. HTTP shows information in web pages.
TCP:
Transmission control protocol is used for communication over a network. 
In TCP data is broken down into small packets and then sent to the destination. However, IP is making sure packets are transmitted to the right address.
FTP:
File transfer protocol is basically used for transferring files to different networks. 
There may be a mass of files such as text files, multimedia files, etc. This way of file transfer is quicker than other methods.
SMTP:
Simple mail transfer protocol manages the transmission and outgoing mail over the internet.

Ethernet & Telnet & Gopher 
http://ecomputernotes.com/computernetworkingnotes/computer-network/protocol

HTTP Request:
Request is a packet of Information that one computer sends to another computer to communicate information.
It contains the following things like:
Request Line

Request Header::
Request Method: GET, Put, Post, Delete, Patch
Accept-Language: It tells the server about the language preferences of the Client. This can be used to reply to the client in the preferred language

Request Body: Request Body is the part of the HTTP Request where additional content can be sent to the server. it could be Json Or XML


HTTP Response: HTTP Response is the packet of information sent by Server to the Client, wrt request which is sent by client.
It contains the following things like:
Request Line
Request Header::
Content-Type. The value of the header is application/json; charset=utf-8. 
It means that server is informing the client that the body of the response will contain a JSON formatted data
Response Body contains the resource data that was requested by the client.

Response Body:





**/