package reusables;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GoogleAPIRe {
	
	public RequestSpecification request;
	public static Properties prop=new Properties();
	public static String placeID;
	FileInputStream file;
	public Response res;

	
	public void propReader() throws IOException
	{
		file=new FileInputStream("C:\\Users\\anilc\\selenium_training\\CaseStudy3\\src\\test\\java\\resources\\input.properties");
		prop.load(file);
	}
	
	public void connect() throws IOException
	{
		propReader();
		request=RestAssured.given();
		request.baseUri("https://rahulshettyacademy.com");
	}
	
	@SuppressWarnings("unchecked")
	public void createReqInput()
	{
		request.header("Content-Type","application/json");
		request.queryParam("key", "qaclick123");
		
		JSONObject obj=new JSONObject();
		obj.put("accuracy", prop.getProperty("accVal"));
		obj.put("name", prop.getProperty("name"));
		obj.put("phone_number", prop.getProperty("phNo"));
		obj.put("address", prop.getProperty("addr"));
		obj.put("website", prop.getProperty("website"));
		obj.put("language", prop.getProperty("lang"));	
		
		JSONObject childo=new JSONObject();
		childo.put("lat",prop.getProperty("latval"));
		childo.put("lng",prop.getProperty("lngval"));
		
		obj.put("location", childo);
		
		JSONArray arr=new JSONArray();
		arr.add(prop.getProperty("type1"));
		arr.add(prop.getProperty("type2"));
	
		request.body(obj.toJSONString());
	}
	
	public void postreq()
	{
		res=request.post("/maps/api/place/add/json");
		System.out.println(res.asString());
	}

	public void postResValid()
	{
		Assert.assertTrue(res.getStatusCode()==200);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue((res.jsonPath().get("status")).equals("OK"));
		placeID=res.jsonPath().get("place_id");
		System.out.println("PlaceID res is" + placeID );
		
		sa.assertTrue(!placeID.isEmpty());
		sa.assertAll();
	}
	
	public void getReq() throws IOException
	{
		connect();
		request.queryParam("key", "qaclick123");
		request.queryParam("place_id",placeID);
		System.out.println("PlaceID get is" + placeID );
		res=request.get("/maps/api/place/get/json");
		System.out.println(res.asString());
	}

	public void getAssertion()
	{
		SoftAssert sa = new SoftAssert();
		sa.assertTrue((res.jsonPath().get("location.latitude")).equals(prop.getProperty("latval")));
		sa.assertTrue((res.jsonPath().get("accuracy")).equals(prop.getProperty("accVal")));
		sa.assertAll();
	}

	
	@SuppressWarnings("unchecked")
	public void createUpdateReqInput() {
		request.header("Content-Type","application/json");
		request.queryParam("key", "qaclick123");
		request.queryParam("place_id", placeID);
		
		JSONObject obj=new JSONObject();
		obj.put("place_id", placeID);
		obj.put("address", prop.getProperty("newAddress"));
		obj.put("key","qaclick123");
		
		System.out.println("Update PlaceID: "+ placeID);	
		System.out.println("Update Address: "+ prop.getProperty("newAddress"));

		System.out.println(obj.toJSONString());	
		request.body(obj.toJSONString());	
	}

	public void updatereq() {
		res=request.put("/maps/api/place/update/json");
		System.out.println(res.asString());
		
	}

	public void updateResValid() {
		Assert.assertTrue(res.getStatusCode()==200);
		SoftAssert sa = new SoftAssert();
		System.out.println("Update Msg to check: " + prop.getProperty("updateSuccessMsg"));
		sa.assertTrue((res.jsonPath().get("msg")).equals(prop.getProperty("updateSuccessMsg")));
		sa.assertAll();	
	}

	public void getUpdateAssertion() {
		Assert.assertTrue(res.getStatusCode()==200);
		SoftAssert sa = new SoftAssert();
		System.out.println("Address Updated: " + prop.getProperty("newAddress"));
		sa.assertTrue((res.jsonPath().get("address")).equals(prop.getProperty("newAddress")));
		sa.assertTrue((res.jsonPath().get("name")).equals(prop.getProperty("name")));
		sa.assertAll();

		
	}

	
	
	@SuppressWarnings("unchecked")
	public void createDelReqInput() {
		request.header("Content-Type","application/json");
		request.queryParam("key", "qaclick123");
	
		JSONObject obj=new JSONObject();
		obj.put("place_id", placeID);
		System.out.println("PlaceID Delete Requested" + placeID);
		
		request.body(obj.toJSONString());
		
	}

	public void delreq() 
	{
		res=request.delete("/maps/api/place/delete/json");
		System.out.println(res.asString());
	}

	public void delResValid() {
		Assert.assertTrue(res.getStatusCode()==200);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue((res.jsonPath().get("status")).equals("OK"));
		sa.assertAll();
		
	}

	public void getDelAssertion()
	{
		Assert.assertTrue(res.getStatusCode()==404);
		SoftAssert sa = new SoftAssert();
		System.out.println("Delete Msg to check: " + prop.getProperty("placeNotFoundMsg"));
		sa.assertTrue((res.jsonPath().get("msg")).equals(prop.getProperty("placeNotFoundMsg")));
		sa.assertAll();
	}

}

