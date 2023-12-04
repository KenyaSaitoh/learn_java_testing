package pro.kensait.spring.calc.rest.server.api;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CalcApiTest_2 {

    @BeforeAll
    public static void setup() {
        RestAssured.port = 8080; // ポート番号
        RestAssured.baseURI = "http://localhost"; // APIのホスト
        RestAssured.basePath = "/calc"; // ベースパス
    }

    @Test
    public void test_AddMethod_ReturnsStatusCode400() {
        String requestBody = "{\"param1\": 30.0, \"param2\": -5000.0}";

        // RestAssuredを使用してAPIをテストし、レスポンスを取得
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/add")
                .then()
                .extract()
                .response();

        // ステータスコードとレスポンスボディを検証
        assertEquals(400, response.getStatusCode());
    }
}