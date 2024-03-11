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

    // テストクラス全体の前処理
    @BeforeAll
    static void initAll() {
        RestAssured.port = 8080; // ポート番号
        RestAssured.baseURI = "http://localhost"; // APIのホスト
        RestAssured.basePath = "/calc"; // ベースパス
    }

    @Test
    @DisplayName("POSTメソッドで加算処理を呼び出し、ステータスコード200であることを検証する")
    void test_Add_Post_StatusCode200() {
        // パラメータを生成する
        CalcParam param = new CalcParam(30.0, 10.0);

        // RestAssuredを使用してRest APIサーバーを呼び出す
        Response response = given()
                .contentType(ContentType.JSON) // MIMEタイプにJSONを設定する
                .body(param) // リクエストボディを設定する
                .when()
                .post("/add") // POSTメソッドでサーバーを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディを検証する
        assertEquals("40.0", response.body().asString());
    }

    @Test
    @DisplayName("POSTメソッドで加算処理を呼び出し、ステータスコード400であることを検証する")
    void test_Add_Post_StatusCode400() {
        // パラメータを生成する
        CalcParam param = new CalcParam(30.0, -1000.0);

        // RestAssuredを使用してRest APIサーバーを呼び出す
        given()
                .contentType(ContentType.JSON) // MIMEタイプにJSONを設定する
                .body(param) // リクエストボディを設定する
                .when()
                .post("/add") // POSTメソッドでサーバーを呼び出す
                .then()
                .statusCode(400); // ステータスコードが400であることを検証する
    }
}