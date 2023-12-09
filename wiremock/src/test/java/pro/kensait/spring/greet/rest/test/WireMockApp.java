package pro.kensait.spring.greet.rest.test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;

public class WireMockApp {

    public static void main(String[] args) {
        // WireMockサーバーのインスタンスを生成し、ポート8080で起動
        WireMockServer wireMockServer = new WireMockServer(8080); // ポート番号を指定
        wireMockServer.start();

        // WireMockの設定をlocalhostのポート8080に合わせる
        configureFor("localhost", 8080);

        // /greet/hello/{name}のURLに対するモックレスポンスを設定
        stubFor(get(urlPathMatching("/greet/hello/.*"))
                .willReturn(aResponse()
                    .withStatus(200) // HTTPステータス200
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Hello Alice!"))); // Aliceに対する挨拶文

        // /greet/goodbye/{name}のURLに対するモックレスポンスを設定
        stubFor(get(urlPathMatching("/greet/goodbye/.*"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Goodbye Bob!"))); // Bobに対する別れの挨拶文
        
        // /greet/morningのURLにクエリパラメータを使用したモックレスポンスを設定
        stubFor(get(urlPathEqualTo("/greet/morning"))
                .withQueryParam("personName", equalTo("Carol"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Good Morning Carol!"))); // Carolに対する朝の挨拶文

        // /greet/afternoonのURLにフォームパラメータを使用したモックレスポンスを設定
        stubFor(post(urlPathEqualTo("/greet/afternoon"))
                .withFormParam("personName", equalTo("Dave"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Good Afternoon Dave!"))); // Daveに対する午後の挨拶文

        // /greet/eveningのURLにJSONリクエストボディを使用したモックレスポンスを設定
        stubFor(post(urlPathEqualTo("/greet/evening"))
                .withRequestBody(matchingJsonPath("$.personName", equalTo("Ellen")))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Good Evening Ellen!"))); // Ellenに対する夕方の挨拶文

        // /greet/nightのURLにヘッダパラメータを使用したモックレスポンスを設定
        stubFor(get(urlPathEqualTo("/greet/night"))
                .withHeader("personName", equalTo("Frank"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Good Night Frank!"))); // Frankに対する夜の挨拶文
    }
}