package apiTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ValidatePostMethod {

    public static String urlPostRequest ="https://fakerestapi.azurewebsites.net/api/v1/Books";
    public static String contentType = "application/json";
    public static Map payload = null;



    @Test
    public static void validateResponseCodeInPostMethod(){
        payload = new HashMap();
        payload.put("id", "101");
        payload.put("title", "Book 101");
        payload.put("description", "this book we are creating for testing purpose");
        payload.put("pageCount", "1");
        payload.put("excerpt", "testing");
        payload.put("publishDate","2023-12-16T16:44:59.059Z");

        given().contentType(contentType).body(payload)
                .when()
                .post(urlPostRequest)
                .then()
                .statusCode(200);
    }


    @Test
    public static void validateIDValueInResponseOfPostMethod(){
        payload = new HashMap();
        payload.put("id", "101");
        payload.put("title", "Book 101");
        payload.put("description", "this book we are creating for testing purpose");
        payload.put("pageCount", "1");
        payload.put("excerpt", "testing");
        payload.put("publishDate","2023-12-16T16:44:59.059Z");

        given().contentType(contentType).body(payload)
                .when()
                .post(urlPostRequest)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("id", equalTo(101));

    }

    @Test
    public static void validateTitleValueInResponseOfPostMethod(){
        payload = new HashMap();
        payload.put("id", "101");
        payload.put("title", "BookNewForAutomation");
        payload.put("description", "this book we are creating for testing purpose");
        payload.put("pageCount", "1");
        payload.put("excerpt", "testing");
        payload.put("publishDate","2023-12-16T16:44:59.059Z");

        given().contentType(contentType).body(payload)
                .when()
                .post(urlPostRequest)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("title", equalTo("BookNewForAutomation"));

    }


    @Test
    public static void validateResponseUsingAssert(){
        payload = new HashMap();
        payload.put("id", "101");
        payload.put("title", "BookNewForAutomation");
        payload.put("description", "this book we are creating for testing purpose");
        payload.put("pageCount", "1");
        payload.put("excerpt", "testing");
        payload.put("publishDate","2023-12-16T16:44:59.059Z");

        Response res =  given().contentType(contentType).body(payload)
                .when()
                .post(urlPostRequest)
                .then()
                .statusCode(200)
                .log()
                .body().extract().response();


        String response = res.asString();

        Assert.assertTrue(response.contains("BookNewForAutomation"));
        Assert.assertTrue(response.contains("this book we are creating for testing purpose"));
        Assert.assertTrue(response.contains("testing"));


    }




}
