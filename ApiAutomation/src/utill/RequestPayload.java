package utill;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import io.restassured.matcher.RestAssuredMatchers;


public class RequestPayload {

	/*public static void main(String [] args){
		
	}
	*/
	@SuppressWarnings("unchecked")
    public static String getJsonRequest(String apiName)
    {        
        StringBuilder  cwd = new StringBuilder(System.getProperty("user.dir"));
         cwd.append("\\").append("Resource").append("\\").append(apiName);
         String prettyJsonString = null;
        try (FileReader reader = new FileReader(cwd.toString()))
        {
        	//JSON parser object to parse read file
            JSONParser jsonParser = new JSONParser();
            //Read JSON file
            Object obj = jsonParser.parse(reader);          
            System.out.println(obj);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            prettyJsonString = gson.toJson(obj);
            System.out.println("Request body------");
            System.out.println(prettyJsonString);
             System.out.println("\n".concat("\n"));
          
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        return prettyJsonString;
    }
	
	 public static void main(String[] args){
	
	String json = "{'shopper': {'Id': '4973860941232342', 'Context': {'CollapseOrderItems': false, 'IsTest': false } }, 'SelfIdentifiersData': {'SelfIdentifierData': [{'SelfIdentifierType': {'SelfIdentifierType': '111'} }, {'SelfIdentifierType': {'SelfIdentifierType': '2222'} } ] } }";

	/*Configuration conf = Configuration.defaultConfiguration();
	List<String> jsonPaths = JsonPath.using(conf).parse(json).read("$");

	for (String path : jsonPaths) {
	    System.out.println(path);
	}*/
	Map<String, Object> flattenJson = JsonFlattener.flattenAsMap(json);

	System.out.println(flattenJson);
	
	 }
	 public static void mai(String[] args)
	    {
		 
	        JSONParser jsonParser = new JSONParser();
	         
	        try (FileReader reader = new FileReader("E:\\DriveDbackupOfSame\\SeleniumJob\\JavaWorkspace\\ApiAutomation\\Resource\\postAPI\\employees.json"))
	        {
	        	
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            if (obj instanceof JSONObject) {
	        	    JSONObject jo = (JSONObject) obj;
	        	} else {
	        	    JSONArray ja = (JSONArray) obj;
	        	}
	            
	            
	            JSONArray employeeList = (JSONArray) obj;
	            System.out.println(employeeList);
	             
	            //Iterate over employee array
	            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }
    private static void parseEmployeeObject(JSONObject employee)
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");
         
        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");   
        System.out.println(firstName);
         
        //Get employee last name
        String lastName = (String) employeeObject.get("lastName"); 
        System.out.println(lastName);
         
        //Get employee website name
        String website = (String) employeeObject.get("website");   
        System.out.println(website);
    }
}
