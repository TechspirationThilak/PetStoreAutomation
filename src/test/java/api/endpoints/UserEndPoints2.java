package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	static ResourceBundle getUrl(){
		
		ResourceBundle routes=ResourceBundle.getBundle("routes");  //Load the properties file // routes is the  Name of the file
		return routes;
	}
	
    public static Response createUser(User payload){
	
	    String post_url=getUrl().getString("post_url");
		
		Response res=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(post_url);   //referring from routes class
		
		 return res;
	}
	
	public static Response  readUser(String userName){
		
		String get_url=getUrl().getString("get_url");
		
		Response res=given()
				.pathParam("username", userName)
				
				.when()
				.get(get_url);     //referring from routes
		
		return res;
	}
	
     public static Response updateUser( String userName,User payload){
    	 
    	 
    	String update_url=getUrl().getString("update_url");
		
		Response res=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
		
		.when()
		.put(update_url);  //referring from routes class
		
		return res;
     }
     
     public static Response deleteUser(String userName) {
    	 
    	 String delete_url=getUrl().getString("delete_url");
    	 
    	 Response res= given()
    			 .pathParam("username", userName)
    			 
    			 .when()
    			 .delete(delete_url);
    	 
    	 return res;
     }
}
