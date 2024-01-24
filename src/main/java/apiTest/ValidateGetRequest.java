package apiTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ValidateGetRequest {


    public String urlGetRequest1 = "https://fakerestapi.azurewebsites.net/api/v1/Books/100";
    public String urlGetRequest2 = "https://fakerestapi.azurewebsites.net/api/v1/Books/$10";


    @Test
    public void verifyResponseCodeOfGivenGetRequest(){
        given()
                .when()
                .get(urlGetRequest1)
                .then()
                .statusCode(200);

    }

    @Test
    public void verifyIDValueInResponseOfGivenGetRequest(){
        given()
                .when()
                .get(urlGetRequest1)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("id", equalTo(100));
    }

    @Test
    public void verifyTitleValueInResponseOfGivenGetRequest(){
        given()
                .when()
                .get(urlGetRequest1)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("title", equalTo("Book 100"));
    }

    @Test
    public void verifyResponseCodeOfInValidGetRequest(){
        given()
                .when()
                .get(urlGetRequest2)
                .then()
                .statusCode(400);
    }

    @Test
    public void verifyResponseUsingAssert(){
        Response res = given()
                .when()
                .get(urlGetRequest1)
                .then()
                .statusCode(200)
                .log()
                .body().extract().response();

        String output = res.asString();

        Assert.assertTrue(output.contains("Book 100"));


    }

}
