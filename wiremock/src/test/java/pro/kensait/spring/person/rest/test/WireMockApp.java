package pro.kensait.spring.person.rest.test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;

public class WireMockApp {

    // WireMockサーバーのインスタンスを生成し、ポート8080で起動
    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(8080); // ポート番号を指定
        wireMockServer.start();

        // WireMockの設定をlocalhostのポート8080に合わせる
        configureFor("localhost", 8080);

        // GET /persons/{personId}のURLに対するモックレスポンスを設定
        stubFor(get(urlPathMatching("/persons/\\d+"))
                .willReturn(aResponse()
                    .withStatus(200) // HTTPステータス200
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"personId\": 1, \"personName\": \"Alice\", \"age\": 30, \"gender\": \"Female\"}"))); // モックレスポンスのボディ

        ObjectMapper mapper = new ObjectMapper();
        JsonNode personNodes = mapper.valueToTree(personList2);

        // GET /personsのURLに対するモックレスポンスを設定
        stubFor(get(urlPathEqualTo("/persons"))
                .willReturn(aResponse()
                    .withStatus(200) // HTTPステータス200
                    .withHeader("Content-Type", "application/json")
                    .withBody(jsonPersonList))); // 静的なJSONレスポンス

        // GET /persons/query_by_ageのURLに対するモックレスポンスを設定
        stubFor(get(urlPathEqualTo("/persons/query_by_age"))
                .withQueryParam("lowerAge", equalTo("30")) // クエリパラメータの条件
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withJsonBody(personNodes))); // ObjectMapperを使用した動的なJSONレスポンス

        // POST /personsのURLに対するモックレスポンスを設定
        stubFor(post(urlPathEqualTo("/persons"))
                .withRequestBody(containing("personName"))
                .withRequestBody(containing("age"))
                .withRequestBody(containing("gender"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"personId\": 3, \"personName\": \"Charlie\", \"age\": 20, \"gender\": \"Male\"}"))); // 新規作成されたPersonオブジェクト

        // PUT /persons/{personId}のURLに対するモックレスポンスを設定
        stubFor(put(urlPathMatching("/persons/\\d+"))
                .withRequestBody(containing("personName"))
                .withRequestBody(containing("age"))
                .withRequestBody(containing("gender"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"personId\": 1, \"personName\": \"UpdatedAlice\", \"age\": 35, \"gender\": \"Female\"}"))); // 更新されたPersonオブジェクト

        // DELETE /persons/{personId}のURLに対するモックレスポンスを設定
        stubFor(delete(urlPathMatching("/persons/\\d+"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("1"))); // 削除されたPersonの数（この場合は1）

    }

    // 静的なJSONレスポンスのデータ
    static final String jsonPersonList = """
            [
                {
                    "personId": 1,
                    "personName": "Alice",
                    "age": 25,
                    "gender": "female"
                },
                {
                    "personId": 2,
                    "personName": "Bob",
                    "age": 35,
                    "gender": "male"
                },
                {
                    "personId": 3,
                    "personName": "Carol",
                    "age": 30,
                    "gender": "female"
                }
            ]
            """;

    static List<Person> personList2;
    {
        personList2.add(new Person(2, "Bob", 35, "male"));
        personList2.add(new Person(3, "Carol", 30, "female"));
    }
}