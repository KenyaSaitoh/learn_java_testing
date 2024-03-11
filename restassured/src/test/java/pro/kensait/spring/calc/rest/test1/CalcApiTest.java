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

    // テストクラス全体の前処理
    @BeforeAll
    static void initAll() {
        RestAssured.port = 8080; // ポート番号
        RestAssured.baseURI = "http://localhost"; // APIのホスト
        RestAssured.basePath = "/calc"; // ベースパス
    }

    @Test
    @DisplayName("GETメソッドで加算処理を呼び出す")
    void test_Add_Get() {
        // RestAssuredを使用してAPIをテストし、レスポンスを取得する
        Response response = given()
                .queryParam("param1", 30.0) // クエリ文字列を設定する
                .queryParam("param2", 10.0) // クエリ文字列を設定する
                .when()
                .get("/add1") // GETメソッドでサーバーを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディを検証する
        assertEquals("40.0", response.getBody().asString());
    }

    @Test
    @DisplayName("POSTメソッドで加算処理を呼び出す")
    void test_Add_Post() {
        // RestAssuredを使用してAPIをテストし、レスポンスを取得する
        Response response = given()
                .contentType(ContentType.URLENC) // MIMEタイプにURLエンコーデッドを設定する
                .formParam("param1", 30.0) // フォームパラメータを設定する
                .formParam("param2", 10.0) // フォームパラメータを設定する
                .when()
                .post("/add2") // POSTメソッドでサーバーを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディを検証する
        assertEquals("40.0", response.getBody().asString());
    }
}