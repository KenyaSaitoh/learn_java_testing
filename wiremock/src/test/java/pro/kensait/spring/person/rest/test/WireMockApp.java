package pro.kensait.spring.person.rest.test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;

public class WireMockApp {

    // WireMockサーバーのインスタンスを生成し、ポート8080で起動
    public static void main(String[] args) {
        WireMockServer server = new WireMockServer(8080); // ポート番号を指定
        server.start();

        ObjectMapper mapper = new ObjectMapper();

        // GET /persons/{personId}のURLに対する疑似的な振る舞いを設定
        stubFor(get(urlPathMatching("/persons/\\d+"))
                .willReturn(aResponse()
                    .withStatus(200) // HTTPステータス200
                    .withHeader("Content-Type", "application/json")
                    .withJsonBody(mapper.valueToTree(alice))));

        // GET /personsのURLに対する疑似的な振る舞いを設定
        stubFor(get(urlPathEqualTo("/persons"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(jsonPersonList)));

        // GET /persons/query_by_ageのURLに対する疑似的な振る舞いを設定
        stubFor(get(urlPathEqualTo("/persons/query_by_age"))
                .withQueryParam("lowerAge", equalTo("30"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withJsonBody(mapper.valueToTree(personList))));

        // POST /personsのURLに対する疑似的な振る舞いを設定
        stubFor(post(urlPathEqualTo("/persons"))
                .withRequestBody(containing("personName"))
                .withRequestBody(containing("age"))
                .withRequestBody(containing("gender"))
                .willReturn(aResponse()
                    .withStatus(201)
                    .withHeader("Content-Type", "application/json")
                    .withJsonBody(mapper.valueToTree(frank))));

        // PUT /persons/{personId}のURLに対する疑似的な振る舞いを設定
        stubFor(put(urlPathMatching("/persons/\\d+"))
                .withRequestBody(containing("personName"))
                .withRequestBody(containing("age"))
                .withRequestBody(containing("gender"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withJsonBody(mapper.valueToTree(frank))));

        // DELETE /persons/{personId}のURLに対する疑似的な振る舞いを設定
        stubFor(delete(urlPathMatching("/persons/\\d+"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")));
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

    static Person alice = new Person(1, "Alice", 25, "female");
    static Person frank = new Person(6, "Frank", 36, "male");

    static List<Person> personList;
    {
        personList.add(new Person(2, "Bob", 35, "male"));
        personList.add(new Person(3, "Carol", 30, "female"));
    }
}