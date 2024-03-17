package pro.kensait.spring.person.rest.test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PersonApiTest {

    // テストクラス全体の前処理
    @BeforeAll
    static void initAll() {
        RestAssured.baseURI = "http://localhost"; // ベースURI
        RestAssured.port = 8080; // ポート番号
        RestAssured.basePath = "/persons"; // ベースパス
        //RestAssured.defaultParser = Parser.JSON; // デフォルトパーサー
    }

    // 特定のpersonIdに対応するPersonを取得するテスト
    @Test
    void test_GetPerson() {
        // 期待値となるPersonインスタンスを生成する
        Person expectedPerson = new Person(1, "Alice", 25, "female");

        Integer personId = 1; // テスト対象のpersonId

        // RestAssuredを使用してRESTサービスを呼び出す
        Response response = given()
                .pathParam("personId", personId) // パスパラメータを設定する
                .when()
                .get("/{personId}") // GETメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディをPerson型で取り出す
        Person actualPerson = response.as(Person.class);

        // レスポンスボディを検証する
        assertEquals(expectedPerson, actualPerson);
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

    // 特定の年齢以下のPersonのリストを取得するテスト
    @Test
    void test_QueryByLowerAge() throws Exception {
        // 期待値となるList<Person>を生成する
        List<Person> expectedList = List.of(
                new Person(2, "Bob", 35, "male"),
                new Person(3, "Carol", 30, "female"),
                new Person(5, "Ellen", 33, "male"));

        Integer lowerAge = 30; // テスト対象の年齢

        // RestAssuredを使用してRESTサービスを呼び出す
        Response response = given()
                .queryParam("lowerAge", lowerAge) // クエリ文字列を設定する
                .when()
                .get("/query_by_age") // GETメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する
        System.out.println(response.asString());

        // レスポンスボディをList<Person>型で取り出す
        List<Person> actualList = response.jsonPath().getList("", Person.class);

        // レスポンスボディを検証する
        assertIterableEquals(expectedList, actualList);
    }

    // 新しいPersonを作成するテスト
    @Test
    void test_CreatePerson() {
        // リクエストボディを生成する
        Person person = new Person("Frank", 36, "male");
        given()
                .contentType(ContentType.JSON) // コンテンツタイプにJSONを設定する
                .body(person) // リクエストボディを設定する
                .when()
                .post() // POSTメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(201); // ステータスコードが201であることを検証する
    }

    // 特定のPersonを置換するテスト
    @Test
    void test_ReplacePerson() {
        // リクエストボディを生成する
        Integer personId = 6; // テスト対象のpersonId
        Person person = new Person(personId, "Frank", 36, "male");

        // RestAssuredを使用してRESTサービスを呼び出す
        Response response = given()
                .contentType(ContentType.JSON) // コンテンツタイプにJSONを設定する
                .body(person) // リクエストボディを設定する
                .pathParam("personId", personId) // パスパラメータを設定する
                .when()
                .put("/{personId}") // PUTメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200) // ステータスコードが200であることを検証する
                .extract()
                .response(); // レスポンスを抽出する

        // レスポンスボディをPerson型で取り出す
        Person actualPerson = response.as(Person.class);

        // レスポンスボディを検証する
        assertEquals(person, actualPerson);
    }

    // 特定のPersonを削除するテスト
    @Test
    public void test_DeletePerson() {
        Integer personId = 6; // テスト対象のpersonId

        // RestAssuredを使用してRESTサービスを呼び出す
        given()
                .pathParam("personId", personId) // パスパラメータを設定する
                .when()
                .delete("/{personId}") // DELETEメソッドでRESTサービスを呼び出す
                .then()
                .statusCode(200); // ステータスコードが200であることを検証する
    }
}