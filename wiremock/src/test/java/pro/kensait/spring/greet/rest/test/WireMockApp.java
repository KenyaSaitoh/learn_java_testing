package pro.kensait.spring.greet.rest.test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;

public class WireMockApp {

    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(8080); // ポート番号を指定
        wireMockServer.start();
     
        configureFor("localhost", 8080);
        stubFor(get(urlPathMatching("/greet/hello/.*"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Hello Alice!"))); // Aliceに置き換えられた例

        stubFor(get(urlPathMatching("/greet/goodbye/.*"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Goodbye Bob!"))); // Bobに置き換えられた例
        
        stubFor(get(urlPathEqualTo("/greet/morning"))
                .withQueryParam("personName", equalTo("Carol"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Good Morning Carol!"))); // Carolに置き換えられた例

        stubFor(post(urlPathEqualTo("/greet/afternoon"))
                .withFormParam("personName", equalTo("Dave"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Good Afternoon Dave!"))); // Daveに置き換えられた例

        stubFor(post(urlPathEqualTo("/greet/evening"))
                .withRequestBody(matchingJsonPath("$.personName", equalTo("Ellen")))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Good Evening Ellen!"))); // Ellenに置き換えられた例

        stubFor(get(urlPathEqualTo("/greet/night"))
                .withHeader("personName", equalTo("Frank"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Good Night Frank!"))); // Frankに置き換えられた例
    }
}
