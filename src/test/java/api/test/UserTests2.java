package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints2;
import api.payload.User;

import io.restassured.response.Response;


public class UserTests2 {
	
	Faker fk;
	User userPayload;
	
	public 	Logger logger;
	
	
	@BeforeClass
	public void setupData() {
		
		fk=new Faker();
		userPayload=new User();
		
        logger = LogManager.getLogger(this.getClass());
		
		userPayload.setId(fk.idNumber().hashCode());
		userPayload.setUsername(fk.name().username());
	    userPayload.setFirstName(fk.name().firstName());
	    userPayload.setLastName(fk.name().lastName());
	    userPayload.setEmail(fk.internet().safeEmailAddress());
	    userPayload.setPassword(fk.internet().password(5,10));
	    userPayload.setPhone(fk.phoneNumber().subscriberNumber(10));
	}
	
	
	@Test(priority = 1)
	
	public void testPostUserByName(){
		
		logger.info("*****Creating the User*****");
		
		Response res=UserEndPoints2.createUser(userPayload);
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(),200);
		
		logger.info("*****User is created*****");
		
	}
	
	@Test(priority = 2)
	public void testReadUserByName() {
		
		logger.info("*****Reading the User info*****");
		Response res=UserEndPoints2.readUser(this.userPayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(),200);
		logger.info("*****User info is displayed*****");
	}
	
	
	@Test(priority = 3)
	
	public void testUpdateUserByName() {
		
		
		     // update data using pay load
		
		    logger.info("*****Updating the User*****");
		
		    userPayload.setFirstName(fk.name().firstName());
		    userPayload.setLastName(fk.name().lastName());
		    userPayload.setEmail(fk.internet().safeEmailAddress());
		    
		    Response res=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		    res.then().log().body();
		   
		    Assert.assertEquals(res.getStatusCode(), 200);
		    
		    logger.info("*****User Updated*****");
		   
		    //check data after update
		    Response resafterupdate=UserEndPoints2.readUser(this.userPayload.getUsername());
		    Assert.assertEquals(resafterupdate.getStatusCode(), 200); 
		
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		logger.info("*****Deleting the User*****");
		
		Response res=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("*****Deleted the User*****");
	}
}
