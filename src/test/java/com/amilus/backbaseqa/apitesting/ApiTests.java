package com.amilus.backbaseqa.apitesting;

import com.amilus.backbaseqa.base.Listener;
import com.amilus.backbaseqa.base.WsTestBase;
import com.amilus.backbaseqa.models.User;
import com.amilus.backbaseqa.pages.SignUpPage;
import com.amilus.backbaseqa.pojos.UserPojo;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests extends WsTestBase {

    String tokenGenerated;

    @Test(priority = 0)
    public void authentication(){
        Gson gson = new Gson();
        UserPojo userPojo = new UserPojo();
        User user = new User();
        user.setEmail("amilus@amilus.com");
        user.setPassword("amiluslu3458");
        userPojo.setUser(user);
        String body = gson.toJson(userPojo);
        Response response = REQUEST.body(body).when().post("/api/users/login");
        if( (response.statusCode() >= 200) && (response.statusCode() <=299)) {
            String jsonString = response.getBody().asString();
            tokenGenerated = JsonPath.parse(jsonString).read("$.user.token");
            Listener.test.get().pass("Authentication is successful. Token: "+tokenGenerated);
        }
        else {
            Listener.test.get().fail("Error Occurred: "+response.getBody());
            response.prettyPrint();
            Assert.fail("Error occured: "+response.getBody());
        }
    }

    @Test(priority = 1)
    public void registration(){
        Gson gson = new Gson();
        UserPojo userPojo = new UserPojo();
        User user = new User();
        user.setEmail(randomStringGenerator()+"@"+randomStringGenerator()+".com");
        user.setUsername(randomStringGenerator());
        user.setPassword("amiluslu3458");
        userPojo.setUser(user);
        String body = gson.toJson(userPojo);
        Response response = REQUEST.body(body).when().post("/api/users");
        if( (response.statusCode() >= 200) && (response.statusCode() <=299)) {
            Listener.test.get().pass("Registration is successful. "+response.getBody());
        }
        else {
            Listener.test.get().fail("Error Occurred: "+response.getBody());
            response.prettyPrint();
            Assert.fail("Error occured: "+response.getBody());
        }
    }

    @Test (priority = 2,dependsOnMethods = "authentication")
    public void getUser(){
        REQUEST.auth().basic("candidatex","qa-is-cool").header("Authorization","Bearer "+tokenGenerated)
                .header("Content-Type","application/json;charset=UTF-8");
        Response response = REQUEST.get("/api/user");
        if( (response.statusCode() >= 200) && (response.statusCode() <=299)) {
            Listener.test.get().pass("getUser is successful. "+response.getBody());
        }
        else {
            Listener.test.get().fail("Error Occurred: "+response.getBody());
            response.prettyPrint();
            Assert.fail("Error occured: "+response.getBody());
        }
    }

    @Test (priority = 3,dependsOnMethods = "authentication")
    public void updateUser(){
        Gson gson = new Gson();
        UserPojo userPojo = new UserPojo();
        User user = new User();
        user.setEmail("amilus@amilus.com");
        user.setBio("bio sertay");
        user.setImage("image isil");
        userPojo.setUser(user);
        String body = gson.toJson(userPojo);
        REQUEST.header("Authorization","Bearer "+tokenGenerated)
                .header("Content-Type","application/json");
        Response response = REQUEST.body(body).when().put("/api/user");
        if( (response.statusCode() >= 200) && (response.statusCode() <=299)) {
            Listener.test.get().pass("updateUser is successful. "+response.getBody());
        }
        else {
            Listener.test.get().fail("Error Occurred: "+response.getBody());
            response.prettyPrint();
            Assert.fail("Error occured: "+response.getBody());
        }
    }

    @Test (priority = 4,dependsOnMethods = "authentication")
    public void getProfile(){
        Response response = REQUEST.get("/api/profiles/amilus");
        if( (response.statusCode() >= 200) && (response.statusCode() <=299)) {
            Listener.test.get().pass("getProfile is successful. "+response.getBody());
        }
        else {
            Listener.test.get().fail("Error Occurred: "+response.getBody());
            response.prettyPrint();
            Assert.fail("Error occured: "+response.getBody());
        }
    }

    @Test (priority = 5,dependsOnMethods = "authentication")
    public void followUser(){
        REQUEST.header("Authorization","Bearer "+tokenGenerated)
                .header("Content-Type","application/json");
        Response response = REQUEST.post("/api/profiles/amilus/follow");
        if( (response.statusCode() >= 200) && (response.statusCode() <=299)) {
            Listener.test.get().pass("followUser is successful. "+response.getBody());
        }
        else {
            Listener.test.get().fail("Error Occurred: "+response.getBody());
            response.prettyPrint();
            Assert.fail("Error occured: "+response.getBody());
        }
    }

    @Test (priority = 6,dependsOnMethods = "authentication")
    public void unfollowUser(){
        REQUEST.header("Authorization","Bearer "+tokenGenerated)
                .header("Content-Type","application/json");
        Response response = REQUEST.delete("/api/profiles/amilus/follow");
        if( (response.statusCode() >= 200) && (response.statusCode() <=299)) {
            Listener.test.get().pass("unfollowUser is successful. "+response.getBody());
        }
        else {
            Listener.test.get().fail("Error Occurred: "+response.getBody());
            response.prettyPrint();
            Assert.fail("Error occured: "+response.getBody());
        }
    }
}
