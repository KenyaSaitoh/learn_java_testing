package pro.kensait.spring.person.rest.test2;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;

public class WireMockApp {

    // WireMockサーバーのインスタンスを生成し、ポート8080で起動
    public static void main(String[] args) {
        WireMockServer server = new WireMockServer(8080); // ポート番号を指定
        server.start();

        // GET /personsのURLに対する疑似的な振る舞いを設定
        stubFor(get(urlPathEqualTo("/persons"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBodyFile("persons.json")));
    }
}