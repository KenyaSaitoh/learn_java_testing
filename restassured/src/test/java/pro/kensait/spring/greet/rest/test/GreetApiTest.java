package pro.kensait.spring.greet.rest.test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GreetApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/greet";
    }

    @Test
    public void testSayHello() {
        String response = given()
                .pathParam("personName", "Alice")
                .when()
                .get("/hello/{personName}")
                .then()
                .extract()
                .asString();
        assertEquals("Hello Alice!", response);
    }

    @Test
    public void testSayGoodbye() {
        String response = given()
                .pathParam("personName", "Bob")
                .when()
                .get("/goodbye/{personName}")
                .then()
                .extract()
                .asString();
        assertEquals("Goodbye Bob!", response);
    }

    @Test
    public void testSayGoodMorning() {
        String response = given()
                .queryParam("personName", "Carol")
                .when()
                .get("/morning")
                .then()
                .extract()
                .asString();
        assertEquals("Good Morning Carol!", response);
    }

    @Test
    public void testSayGoodAfternoon() {
        String response = given()
                .contentType(ContentType.URLENC)
                .formParam("personName", "Dave")
                .when()
                .post("/afternoon")
                .then()
                .extract()
                .asString();
        assertEquals("Good Afternoon Dave!", response);
    }

    @Test
    public void testSayGoodEvening() {
        String jsonBody = "{\"personName\": \"Ellen\"}";
        String response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/evening")
                .then()
                .extract()
                .asString();
        assertEquals("Good Evening Ellen!", response);
    }

    @Test
    public void testSayGoodNight() {
        String response = given()
                .header("personName", "Frank")
                .when()
                .get("/night")
                .then()
                .extract()
                .asString();
        assertEquals("Good Night Frank!", response);
    }
}
