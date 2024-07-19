package pro.kensait.spring.calc.rest.test;

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
        RestAssured.baseURI = "http://localhost"; // ベースURI
        RestAssured.port = 8080; // ポート番号
        RestAssured.basePath = "/calc"; // ベースパス
    }

    @Test
    @DisplayName("GETメソッドで加算処理を呼び出す")
    void test_Add_Get() {
        // RestAssuredを使用してRESTサービスを呼び出す
        Response response = given()
                .queryParam("param1", 30.0) // クエリパラメータを設定する
                .queryParam("param2", 10.0) // クエリパラメータを設定する
                .when()
                .get("/add1") // GETメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスの情報を表示する
        System.out.println("ボディ => " + response.asString());
        System.out.println("HTTPヘッダ => " + response.getHeaders());
        System.out.println("ステータスコード => " + response.getStatusCode());
        System.out.println("コンテンツタイプ => " + response.getContentType());
        System.out.println("応答時間 => " + response.getTime());

        // レスポンスボディを検証する
        assertEquals("40.0", response.asString());
    }

    @Test
    @DisplayName("POSTメソッドで加算処理を呼び出す")
    void test_Add_Post() {
        // RestAssuredを使用してRESTサービスを呼び出す
        Response response = given()
                .contentType(ContentType.URLENC) // コンテンツタイプにフォームURLエンコーデッドを設定する
                .formParam("param1", 30.0) // フォームパラメータを設定する
                .formParam("param2", 10.0) // フォームパラメータを設定する
                .when()
                .post("/add2") // POSTメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディを検証する
        assertEquals("40.0", response.asString());
    }
}