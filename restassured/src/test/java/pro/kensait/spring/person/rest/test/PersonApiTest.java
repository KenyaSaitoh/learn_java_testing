package pro.kensait.spring.person.rest.test;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PersonApiTest {

    // テストクラス全体の前処理
    @BeforeAll
    public static void initAll() {
        RestAssured.port = 8080; // APIサーバーのポート
        RestAssured.baseURI = "http://localhost"; // APIサーバーのベースURI
        RestAssured.basePath = "/persons"; // APIのベースパス
    }

    // 特定のpersonIdに対応するPersonを取得するテスト
    @Test
    public void test_GetPerson() {
        Integer personId = 1; // テスト対象のpersonId
        given()
                .pathParam("personId", personId) // パスパラメータを設定
                .when()
                .get("/{personId}") // GETリクエストを実行
                .then()
                .statusCode(200); // ステータスコードが200であることを検証
    }

    // 全Personのリストを取得するテスト
    @Test
    public void test_GetAllPersons() {
        Response response = given()
                .when()
                .get() // 正しいエンドポイントを指定
                .then()
                .statusCode(200) // ステータスコードが200であることを検証
                .extract()
                .response(); // レスポンスを抽出
        System.out.println(response.asString());
    }

    // 特定の年齢以下のPersonのリストを取得するテスト
    @Test
    public void test_QueryByLowerAge() {
        Integer lowerAge = 30; // テスト対象の年齢
        given()
                .queryParam("lowerAge", lowerAge) // クエリパラメータを設定
                .when()
                .get("/query_by_age") // GETリクエストを実行
                .then()
                .statusCode(200); // ステータスコードが200であることを検証
    }

    // 新しいPersonを作成するテスト
    @Test
    public void test_CreatePerson() {
        // リクエストボディを生成する
        Person person = new Person("Frank", 36, "male");
        given()
                .contentType(ContentType.JSON) // コンテンツタイプをJSONに設定
                .body(person) // リクエストボディを設定
                .when()
                .post() // POSTリクエストを実行
                .then()
                .statusCode(200); // ステータスコードが200であることを検証
    }

    // 特定のPersonを更新するテスト
    @Test
    public void test_ReplacePerson() {
        Integer personId = 6; // テスト対象のpersonId
        Person person = new Person(personId, "Frank", 36, "male");
        given()
                .contentType(ContentType.JSON) // コンテンツタイプをJSONに設定
                .body(person) // リクエストボディを設定
                .pathParam("personId", personId) // パスパラメータを設定
                .when()
                .put("/{personId}") // PUTリクエストを実行
                .then()
                .statusCode(200); // ステータスコードが200であることを検証
    }

    // 特定のPersonを削除するテスト
    @Test
    public void test_DeletePerson() {
        Integer personId = 1; // テスト対象のpersonId
        given()
                .pathParam("personId", personId) // パスパラメータを設定
                .when()
                .delete("/{personId}") // DELETEリクエストを実行
                .then()
                .statusCode(200); // ステータスコードが200であることを検証
    }
}