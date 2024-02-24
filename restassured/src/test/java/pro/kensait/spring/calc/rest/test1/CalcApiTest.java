package pro.kensait.spring.calc.rest.test1;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CalcApiTest {

    // テストクラス実行の前処理
    @BeforeAll
    public static void initAll() {
        RestAssured.port = 8080; // ポート番号
        RestAssured.baseURI = "http://localhost"; // APIのホスト
        RestAssured.basePath = "/calc"; // ベースパス
    }

    @Test
    @DisplayName("GETメソッドで加算処理を呼び出す")
    public void test_Add_Get() {
        // RestAssuredを使用してAPIをテストし、レスポンスを取得する
        Response response = given()
                .queryParam("param1", 30.0)
                .queryParam("param2", 10.0)
                .when()
                .get("/add1")
                .then()
                .extract()
                .response();

        // ステータスコードとレスポンスボディを検証する
        assertEquals(200, response.getStatusCode());
        assertEquals("40.0", response.getBody().asString());
    }

    @Test
    @DisplayName("POSTメソッドで加算処理を呼び出す")
    public void test_Add_Post() {
        // RestAssuredを使用してAPIをテストし、レスポンスを取得する
        Response response = given()
                .contentType(ContentType.URLENC)
                .param("param1", 30.0)
                .param("param2", 10.0)
                .when()
                .post("/add2")
                .then()
                .extract()
                .response();

        // ステータスコードとレスポンスボディを検証する
        assertEquals(200, response.getStatusCode());
        assertEquals("40.0", response.getBody().asString());
    }
}