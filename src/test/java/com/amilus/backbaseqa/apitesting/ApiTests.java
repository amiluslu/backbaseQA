package com.amilus.backbaseqa.apitesting;

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

public class Users extends WsTestBase {

    String tokenGenerated;

    @Test(priority = 0)
    public void authentication(){
        Gson gson = new Gson();
        UserPojo userPojo = new UserPojo();
        User user = User.getUserFromJson();
        user.setEmail("amilus@amilus.com");
        user.setPassword("amiluslu3458");
        userPojo.setUser(user);
        String body = gson.toJson(userPojo);
        Response response = REQUEST.body(body).when().post("/api/users/login");
        if(response.statusCode() == 200){
            String jsonString = response.getBody().asString();
            tokenGenerated = JsonPath.parse(jsonString).read("$.user.token");
        }
        else {
            response.prettyPrint();
            Assert.fail("Error occured: "+response.getBody());
        }
    }

    @Test(priority = 1)
    public void registration(){
        Gson gson = new Gson();
        UserPojo userPojo = new UserPojo();
        User user = User.getUserFromJson();
        user.setEmail(randomStringGenerator()+"@"+randomStringGenerator()+".com");
        user.setUsername(randomStringGenerator());
        user.setPassword("amiluslu3458");
        userPojo.setUser(user);
        String body = gson.toJson(userPojo);
        REQUEST.body(body).when().post("/api/users").then().statusCode(200);
    }

    @Test (priority = 2,dependsOnMethods = "authentication")
    public void getUser(){
        REQUEST.auth().basic("candidatex","qa-is-cool").header("Authorization","Bearer "+tokenGenerated)
                .header("Content-Type","application/json;charset=UTF-8");
        REQUEST.get("/api/user").then().statusCode(200);
    }

    @Test (priority = 3,dependsOnMethods = "authentication")
    public void updateUser(){
        Gson gson = new Gson();
        UserPojo userPojo = new UserPojo();
        User user = User.getUserFromJson();
        user.setEmail("amilus@amilus.com");
        user.setBio("bio sertay");
        user.setImage("image isil");
        userPojo.setUser(user);
        String body = gson.toJson(userPojo);
        REQUEST.header("Authorization","Bearer "+tokenGenerated)
                .header("Content-Type","application/json");
        REQUEST.body(body).when().put("/api/user").then().statusCode(201);
    }

    @Test (priority = 4,dependsOnMethods = "authentication")
    public void getProfile(){
        REQUEST.get("/api/profiles/amilus").then().statusCode(200);
    }

    @Test (priority = 5,dependsOnMethods = "authentication")
    public void followUser(){
        REQUEST.header("Authorization","Bearer "+tokenGenerated)
                .header("Content-Type","application/json");
        Response response = REQUEST.post("/api/profiles/amilus/follow");
        response.prettyPrint();
    }

//    @Test (priority = 2,dependsOnMethods = "authentication")
//    public void unfollowUser(){
//        REQUEST.header("Authorization","Bearer "+tokenGenerated)
//                .header("Content-Type","application/json");
//        Response response = REQUEST.auth().oauth2(tokenGenerated).post("/api/profiles/amilus/follow");
//        response.prettyPrint();
//    }

//    @Test (priority = 2,dependsOnMethods = "authentication")
//    public void listArticles(){
//        Response response = REQUEST.get("/api/articles");
//        response.prettyPrint();
//    }

//    @Test (priority = 2,dependsOnMethods = "authentication")
//    public void feedArticles(){
//        String amil = "{\n" +
//                "  \"article\": {\n" +
//                "    \"title\": \"How to train your dragon\",\n" +
//                "    \"description\": \"Ever wonder how?\",\n" +
//                "    \"body\": \"You have to believe\",\n" +
//                "    \"tagList\": [\"magic\", \"cool\", \"dragons\"]\n" +
//                "  }\n" +
//                "}";
//
//        REQUEST.header("Authorization","Bearer "+tokenGenerated)
//                .header("Content-Type","application/json;charset=utf-8");
//        Response response = REQUEST.body(amil).post("/api/articles");
//        response.prettyPrint();
//    }


}
