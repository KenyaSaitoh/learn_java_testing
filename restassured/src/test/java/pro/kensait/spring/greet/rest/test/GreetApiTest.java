package pro.kensait.spring.greet.rest.test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GreetApiTest {

    // テストクラス全体の前処理
    @BeforeAll
    static void initAll() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/greet";
    }

    @Test
    @DisplayName("REST APIを呼び出し、パスパラメータに応じて、Hello 〇〇という応答を検証する")
    void test_Say_Hello() {
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
    @DisplayName("REST APIを呼び出し、パスパラメータに応じて、Goodbye 〇〇という応答を検証する")
    void test_Say_Goodbye() {
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
    @DisplayName("REST APIを呼び出し、クエリパラメータに応じて、Good Morning 〇〇という応答を検証する")
    void test_Say_GoodMorning() {
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
    @DisplayName("REST APIを呼び出し、フォームエンコーデッドパラメータに応じて、Good Afternoon 〇〇という応答を検証する")
    void test_Say_GoodAfternoon() {
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
    @DisplayName("REST APIを呼び出し、JSONパラメータに応じて、Good Evening 〇〇という応答を検証する")
    void test_Say_GoodEvening() {
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
    @DisplayName("REST APIを呼び出し、HTTPヘッダに応じて、Good Night 〇〇という応答を検証する")
    void test_Say_GoodNight() {
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