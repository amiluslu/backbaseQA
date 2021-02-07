package com.amilus.backbaseqa.base;


import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class WsTestBase {

    public RequestSpecification REQUEST;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        try {
        Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        String credentials = props.getProperty("api.username")+":"+props.getProperty("api.pass");
        byte [] encodedCredentials = Base64.encodeBase64(credentials.getBytes());
        String encodedCredentialsAsString = new String(encodedCredentials);

        RestAssured.baseURI = props.getProperty("api.uri");
        REQUEST = RestAssured.given();
        REQUEST.header("Authorization","Basic "+encodedCredentialsAsString)
                .header("Content-Type","application/json;charset=utf-8");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {

    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {

    }
    public String randomStringGenerator() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            int index
                    = (int)(alphabet.length()
                    * Math.random());
            sb.append(alphabet
                    .charAt(index));
        }
        return sb.toString();
    }
}
