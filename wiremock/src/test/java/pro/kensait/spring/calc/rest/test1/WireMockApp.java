package pro.kensait.spring.calc.rest.test1;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;

public class WireMockApp {

    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(8080); // ポート番号を指定
        wireMockServer.start();

        configureFor("localhost", 8080);
        stubFor(get(urlPathEqualTo("/calc/add1"))
                .withQueryParam("param1", equalTo("30.0"))
                .withQueryParam("param2", equalTo("10.0"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json") // コンテンツタイプを設定
                        .withBody("40.0"))); // ここでレスポンスのボディを適宜変更

        stubFor(post(urlPathEqualTo("/calc/add2"))
                .withFormParam("param1", matching("\\d+\\.?\\d*"))
                .withFormParam("param2", matching("\\d+\\.?\\d*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json") // コンテンツタイプを設定
                        .withBody("40.0"))); // ここでレスポンスのボディを適宜変更
    }
}