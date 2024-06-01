package pro.kensait.spring.person.rest.test;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PersonApiTest2 {

    // テストクラス全体の前処理
    @BeforeAll
    static void initAll() {
        RestAssured.baseURI = "http://localhost"; // ベースURI
        RestAssured.port = 8080; // ポート番号
        RestAssured.basePath = "/persons"; // ベースパス
    }

    // 全Personのリストを取得するテスト
    @Test
    void test_GetAllPersons() {
        // RestAssuredを使用してRESTサービスを呼び出す
        Response response = given()
                .when()
                .get() // GETメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する
        System.out.println(response.asString());
    }
}