package BookmanagementAPI;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Addingbookclass {
	@BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://your-api-url";
    }

    @Test
    public void addBookWithValidInputs() {
        String requestBody = "{ \"title\": \"Valid Book\", \"author\": \"Valid Author\", \"isbn\": \"123-4567890123\" }";
        
        Response response = 
            given()
                .contentType(JSON)
                .body(requestBody)
            .when()
                .post("/books")
            .then()
                .statusCode(201)
                .extract().response();
        
        Assert.assertEquals(response.jsonPath().getString("message"), "Book added successfully");
    }

    @Test
    public void addBookWithInvalidInputs() {
        String requestBody = "{ \"title\": \"\", \"author\": \"\", \"isbn\": \"\" }";
        
        Response response = 
            given()
                .contentType(JSON)
                .body(requestBody)
            .when()
                .post("/books")
            .then()
                .statusCode(400)
                .extract().response();
        
        Assert.assertEquals(response.jsonPath().getString("message"), "Invalid book data");
    }
}




