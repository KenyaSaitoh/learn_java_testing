package pro.kensait.spring.calc.rest.test1;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CalcApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.port = 8080; // ポート番号
        RestAssured.baseURI = "http://localhost"; // APIのホスト
        RestAssured.basePath = "/calc"; // ベースパス
    }

    @Test
    public void test_AddMethod_ByGet_RightResult() {
        // RestAssuredを使用してAPIをテストし、レスポンスを取得
        Response response = given()
                .queryParam("param1", 30.0)
                .queryParam("param2", 10.0)
                .when()
                .get("/add1")
                .then()
                .extract()
                .response();

        // ステータスコードとレスポンスボディを検証
        assertEquals(200, response.getStatusCode());
        assertEquals("40.0", response.getBody().asString());
    }

    @Test
    public void test_AddMethod_ByPost_RightResult() {
        // RestAssuredを使用してAPIをテストし、レスポンスを取得
        Response response = given()
                .contentType(ContentType.URLENC)
                .param("param1", 30.0)
                .param("param2", 10.0)
                .when()
                .post("/add2")
                .then()
                .extract()
                .response();

        // ステータスコードとレスポンスボディを検証
        assertEquals(200, response.getStatusCode());
        assertEquals("40.0", response.getBody().asString());
    }
}