package pro.kensait.gatling;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class BookStoreSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
        //ベースURLを設定
        .baseUrl("http://localhost:8080")
        // Acceptヘッダ（一般的なWebブラウザの想定）
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        // DNTヘッダに"1"を設定（追跡を望まない）
        .doNotTrackHeader("1")
        // Accept-Languageヘッダ（日本語を優先する）
        .acceptLanguageHeader("ja,ja-JP;q=0.9,en-US;q=0.8,en;q=0.7")
        // Accept-Encodingヘッダ（コンテンツの圧縮形式をサーバーに伝える）
        .acceptEncodingHeader("gzip, deflate")
        // User-Agentヘッダ（WindowsのChromeの想定）
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                + "(KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

    private ScenarioBuilder scenario = scenario("BookStoreTest")
        .exec(http("Request_HomePage") // ラベルを設定
            .get("/")) // パスを設定
        .pause(1)
        .exec(http("Login") // ラベルを設定
            .post("/login") // パスを設定
            .formParam("email", "alice@gmail.com")
            .formParam("password", "password"))
        .pause(1)
        .exec(http("Access_BookSelectPage") // ラベルを設定
            .get("/toSelect"))  // パスを設定
        // ここに他のステップを追加
        ;

    {
        setUp(scenario.injectOpen(atOnceUsers(1)).protocols(httpProtocol));
    }
}

