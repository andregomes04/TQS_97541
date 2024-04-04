package tqs.lab7;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestTest{

    @Test
    void checkEndpoint() {
        given()
        .when()
            .get("https://jsonplaceholder.typicode.com/todos")
        .then().assertThat()
            .statusCode(200);
    }

    @Test
    void checkTitleOfToDo4() {
        given()
        .when()
            .get("https://jsonplaceholder.typicode.com/todos/4")
        .then()
            .body("title", equalTo("et porro tempora"));
    }

    @Test
    void checkForObjects() {
        given()
        .when()
            .get("https://jsonplaceholder.typicode.com/todos")
        .then()
            .body("id", hasItems(198, 199));
    }

    @Test
    void checkForRunTime() {
        RequestSpecification requestSpec = new RequestSpecBuilder().build();
        requestSpec.baseUri("https://jsonplaceholder.typicode.com");
        Response response = given().spec(requestSpec).get("/todos");
        Assert.assertTrue(response.getTime() < 2000);
    }

}