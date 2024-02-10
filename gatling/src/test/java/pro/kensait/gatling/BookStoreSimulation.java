package pro.kensait.gatling;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;

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

    /*
     * このままではNG
     * シナリオはSeleniumとは異なるので、「打鍵指示書」は作り直す
     * HTTPリクエストのみに注目すればよい（リクエストが送信されないクリックは不必要）
     * URLや、GETかPOSTは明示する
     * queryParamも必要
     * 200応答であることを検証する
     * クッキーは管理されるのか、要確認
     * 動的な項目取得と、それをリクエストに入れる方法も確認
     */
    private ScenarioBuilder scenario = scenario("BookStoreTest")
            .exec(http("Open Home Page").get("/"))
            .pause(Duration.ofSeconds(1))
            .exec(http("Input Email").post("/login").formParam("email", "alice@gmail.com"))
            .pause(Duration.ofSeconds(1))
            .exec(http("Input Password").post("/login").formParam("password", "password"))
            .pause(Duration.ofSeconds(1))
            .exec(http("Click Login").get("/loginButton"))
            .pause(Duration.ofSeconds(1))
            .exec(http("Redirect to Select").get("/toSelect"))
            .pause(Duration.ofSeconds(1))
            .exec(http("Verify BookSelectPage Title").get("/toSelect")
                    .check(substring("BookSelectPage")))
            .pause(Duration.ofSeconds(1))
            .exec(http("Click toSearchLink").get("/toSearchLink"))
            .pause(Duration.ofSeconds(1))
            .exec(http("Select Category").post("/search").formParam("category", "1"))
            .pause(Duration.ofSeconds(1))
            .exec(http("Click search1Button").get("/search1Button")) // ID属性による操作の代わり
            .pause(Duration.ofSeconds(1))
            .exec(http("Verify BookSearchPage Title").get("/search")
                    .check(substring("BookSearchPage")))
            .pause(Duration.ofSeconds(1))
            .exec(http("Input Keyword").post("/search").formParam("keyword", "SQL"))
            .pause(Duration.ofSeconds(1))
            .exec(http("Click button-17").get("/button-17")) // ID属性による操作の代わり
            .pause(Duration.ofSeconds(1))
            .exec(http("Click toSelectLink").get("/toSelectLink")) // ID属性による操作の代わり
            .pause(Duration.ofSeconds(1))
            .exec(http("Click check-18").get("/check-18")) // チェックボックス操作の代わり
            .pause(Duration.ofSeconds(1))
            .exec(http("Click removeButton").get("/removeButton")) // ID属性による操作の代わり
            .pause(Duration.ofSeconds(1))
            .exec(http("Click fixButton").get("/fixButton")) // ID属性による操作の代わり
            .pause(Duration.ofSeconds(1))
            .exec(http("Verify BookOrderPage Title").get("/order")
                    .check(substring("BookOrderPage")))
            .pause(Duration.ofSeconds(1))
            .exec(http("Click bankTransfer").get("/bankTransfer")) // ID属性による操作の代わり
            .pause(Duration.ofSeconds(1))
            .exec(http("Click orderButton1").get("/orderButton1")) // ID属性による操作の代わり
            .pause(Duration.ofSeconds(1))
            // ポップアップウィンドウの操作はHTTPリクエストでは直接模倣できないため、この部分は省略
            .exec(http("Click logoutButton").get("/logoutButton")) // ログアウト操作
            .pause(Duration.ofSeconds(1))
            .exec(http("Verify FinishPage Title").get("/finish").check(substring("FinishPage")));

    {
        setUp(
                scenario.injectOpen(
                        nothingFor(Duration.ofSeconds(10)), // 最初の10秒間は何もしません
                        rampUsers(5).during(Duration.ofSeconds(100)) // 100秒かけてユーザー数を5まで増やします
                )
                        .protocols(httpProtocol))
                                .maxDuration(120); // シミュレーションの最大持続時間を120秒（2分）に設定します。
    }
}