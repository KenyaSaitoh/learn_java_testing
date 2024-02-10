package pro.kensait.gatling.bookstore.scenario2;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class GeneratedSimulation extends Simulation {

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

    private ScenarioBuilder scenario = scenario("BookStoreUserScenario")
            .exec(http("Open")
                    .get("/")
                    .check(status().is(200),
                            css("title").is("TopPage"),
                            css("#csrfToken", "value").saveAs("csrfToken")))
            .pause(1)
            .exec(http("Login")
                    .post("/processLogin")
                    .formParam("email", "alice@gmail.com")
                    .formParam("password", "password")
                    .formParam("_csrf", "#{csrfToken}")
                    .check(status().is(200),
                            css("title").is("BookSelectPage"),
                            css("#csrfToken", "value").saveAs("csrfToken")))
            .pause(1)
            .exec(http("Add Book 1")
                    .post("/addBook")
                    .formParam("bookId", "2")
                    .formParam("_csrf", "#{csrfToken}")
                    .check(status().is(200),
                            css("title").is("CartViewPage"),
                            css("#csrfToken", "value").saveAs("csrfToken")))
            .pause(1)
            .exec(http("To Select 2")
                    .get("/toSelect")
                    .check(status().is(200),
                            css("title").is("BookSelectPage"),
                            css("#csrfToken", "value").saveAs("csrfToken")))
            .pause(1)
            .exec(http("Add Book 2")
                    .post("/addBook")
                    .formParam("bookId", "5")
                    .formParam("_csrf", "#{csrfToken}")
                    .check(status().is(200),
                            css("title").is("CartViewPage"),
                            css("#csrfToken", "value").saveAs("csrfToken")))
            .pause(1)
            .exec(http("To Select 3")
                    .get("/toSelect")
                    .check(status().is(200),
                            css("title").is("BookSelectPage")))
            .pause(1)
            .exec(http("To Search")
                    .get("/toSearch")
                    .check(status().is(200),
                            css("title").is("BookSearchPage")))
            .pause(1)
            .exec(http("Search")
                    .get("/search")
                    .queryParam("categoryId", "2")
                    .queryParam("keyword", "Cloud")
                    .check(status().is(200),
                            css("title").is("BookSelectPage"),
                            css("#csrfToken", "value").saveAs("csrfToken")))
            .pause(1)
            .exec(http("Add Book 3")
                    .post("/addBook")
                    .formParam("bookId", "14")
                    .formParam("_csrf", "#{csrfToken}")
                    .check(status().is(200),
                            css("title").is("CartViewPage"),
                            css("#csrfToken", "value").saveAs("csrfToken")))
            .pause(1)
            .exec(http("Fix")
                    .post("/fix")
                    .formParam("_csrf", "#{csrfToken}")
                    .check(status().is(200),
                            css("title").is("BookOrderPage"),
                            css("#csrfToken", "value").saveAs("csrfToken")))
            .pause(1)
            .exec(http("Order")
                    .post("/order1")
                    .formParam("settlementType", "1")
                    .formParam("_csrf", "#{csrfToken}")
                    .check(status().is(200),
                            css("title").is("OrderSuccessPage"),
                            css("#csrfToken", "value").saveAs("csrfToken")))
            .pause(1)
            .exec(http("Logout")
                    .post("/processLogout")
                    .formParam("_csrf", "#{csrfToken}")
                    .check(status().is(200),
                            css("title").is("FinishPage")));

    {
        setUp(
                scenario.injectOpen(
                        rampUsers(5).during(Duration.ofSeconds(100)) // 100秒かけてユーザー数を5まで増やす
                )
                        .protocols(httpProtocol))
                                .maxDuration(120); // シミュレーションの最大持続時間を120秒（2分）に設定する
    }
}