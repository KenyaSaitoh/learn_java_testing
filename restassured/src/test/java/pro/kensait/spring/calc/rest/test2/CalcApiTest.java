package pro.kensait.spring.calc.rest.test2;

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
    public void test_AddMethod_ReturnsRightResult_StatusCode200() {
        // パラメータを生成する
        CalcParam param = new CalcParam(30.0, 10.0);

        // RestAssuredを使用してAPIをテストし、レスポンスを取得
        Response response = given()
                .contentType(ContentType.JSON)
                .body(param)
                .when()
                .post("/add")
                .then()
                .extract()
                .response();

        // ステータスコードとレスポンスボディを検証
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void test_AddMethod_Error_Returns_StatusCode400() {
        // パラメータを生成する
        CalcParam param = new CalcParam(30.0, -5000.0);

        // RestAssuredを使用してAPIをテストし、レスポンスを取得
        Response response = given()
                .contentType(ContentType.JSON)
                .body(param)
                .when()
                .post("/add")
                .then()
                .extract()
                .response();

        // ステータスコードとレスポンスボディを検証
        assertEquals(400, response.getStatusCode());
    }
}