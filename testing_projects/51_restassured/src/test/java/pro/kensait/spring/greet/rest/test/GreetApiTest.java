package pro.kensait.spring.greet.rest.test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GreetApiTest {

    // テストクラス全体の前処理
    @BeforeAll
    static void initAll() {
        RestAssured.baseURI = "http://localhost"; // ベースURI
        RestAssured.port = 8080; // ポート番号
        RestAssured.basePath = "/greet"; // ベースパス
    }

    @Test
    @DisplayName("RESTサービスを呼び出し、パスパラメータに応じて、Hello 〇〇という応答を検証する")
    void test_SayHello() {
        Response response = given()
                .pathParam("personName", "Alice") // パスパラメータを設定する
                .when()
                .get("/hello/{personName}") // GETメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディを検証する
        assertEquals("Hello Alice!", response.asString());
    }

    @Test
    @DisplayName("RESTサービスを呼び出し、パスパラメータに応じて、Goodbye 〇〇という応答を検証する")
    void test_SayGoodbye() {
        Response response = given()
                .pathParam("personName", "Bob") // パスパラメータを設定する
                .when()
                .get("/goodbye/{personName}") // GETメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディを検証する
        assertEquals("Goodbye Bob!", response.asString());
    }

    @Test
    @DisplayName("RESTサービスを呼び出し、クエリパラメータに応じて、Good Morning 〇〇という応答を検証する")
    void test_SayGoodMorning() {
        Response response = given()
                .queryParam("personName", "Carol") // クエリパラメータを設定する
                .when()
                .get("/morning") // GETメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディを検証する
        assertEquals("Good Morning Carol!", response.asString());
    }

    @Test
    @DisplayName("RESTサービスを呼び出し、フォームエンコーデッドパラメータに応じて、Good Afternoon 〇〇という応答を検証する")
    void test_SayGoodAfternoon() {
        Response response = given()
                .contentType(ContentType.URLENC) // コンテンツタイプにURLエンコーデッドを設定する
                .formParam("personName", "Dave") // フォームパラメータを設定する
                .when()
                .post("/afternoon") // POSTメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディを検証する
        assertEquals("Good Afternoon Dave!", response.asString());
    }

    @Test
    @DisplayName("RESTサービスを呼び出し、JSONパラメータに応じて、Good Evening 〇〇という応答を検証する")
    void test_SayGoodEvening() {
        String jsonBody = "{\"personName\": \"Ellen\"}";
        Response response = given()
                .contentType(ContentType.JSON) // コンテンツタイプにJSONを設定する
                .body(jsonBody) // リクエストボディを設定する
                .when()
                .post("/evening") // POSTメソッドでRESTサービスを呼び出す
                .then()
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディを検証する
        assertEquals("Good Evening Ellen!", response.asString());
    }

    @Test
    @DisplayName("RESTサービスを呼び出し、HTTPヘッダに応じて、Good Night 〇〇という応答を検証する")
    void test_SayGoodNight() {
        Response response = given()
                .header("personName", "Frank") // HTTPヘッダを設定する
                .when()
                .get("/night") // GETメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディを検証する
        assertEquals("Good Night Frank!", response.asString());
    }
}