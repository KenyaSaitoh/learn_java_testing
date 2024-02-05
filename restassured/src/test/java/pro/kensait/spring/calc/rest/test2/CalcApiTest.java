package pro.kensait.spring.calc.rest.test2;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("POSTメソッドで加算処理を呼び出し、ステータスコード200であることを検証する")
    public void test_Add_Post_StatusCode200() {
        // パラメータを生成する
        CalcParam param = new CalcParam(30.0, 10.0);

        // RestAssuredを使用してAPIをテストし、レスポンスを取得する
        Response response = given()
                .contentType(ContentType.JSON)
                .body(param)
                .when()
                .post("/add")
                .then()
                .extract()
                .response();

        // ステータスコードとレスポンスボディを検証する
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @DisplayName("POSTメソッドで加算処理を呼び出し、ステータスコード400であることを検証する")
    public void test_Add_Post_StatusCode400() {
        // パラメータを生成する
        CalcParam param = new CalcParam(30.0, -1000.0);
        System.out.println(param);
        
        // RestAssuredを使用してAPIをテストし、レスポンスを取得する
        Response response = given()
                .contentType(ContentType.JSON)
                .body(param)
                .when()
                .post("/add")
                .then()
                .extract()
                .response();

        // ステータスコードとレスポンスボディを検証する
        assertEquals(400, response.getStatusCode());
    }
}