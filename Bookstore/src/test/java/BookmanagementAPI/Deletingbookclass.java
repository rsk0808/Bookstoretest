package BookmanagementAPI;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Deletingbookclass {
	@BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://your-api-url";
    }

    @Test
    public void deleteBookWithValidId() {
        Response response = 
            given()
                .pathParam("bookId", 1)
            .when()
                .delete("/books/{bookId}")
            .then()
                .statusCode(200)
                .extract().response();
        
        Assert.assertEquals(response.jsonPath().getString("message"), "Book deleted successfully");
    }

    @Test
    public void deleteBookWithInvalidId() {
        Response response = 
            given()
                .pathParam("bookId", 999)
            .when()
                .delete("/books/{bookId}")
            .then()
                .statusCode(404)
                .extract().response();
        
        Assert.assertEquals(response.jsonPath().getString("message"), "Book not found");
    }
}



