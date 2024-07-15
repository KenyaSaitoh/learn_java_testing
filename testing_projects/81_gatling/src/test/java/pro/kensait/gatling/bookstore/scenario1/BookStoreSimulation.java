package pro.kensait.gatling.bookstore.scenario1;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class BookStoreSimulation extends Simulation {

    // HTTPに関する共通情報を設定する
    HttpProtocolBuilder httpProtocol = http
            // ベースURLを設定
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

    // CSVファイルからテストデータを読み込むためのフィーダーを設定する
    FeederBuilder.Batchable<String> feeder = csv("data/users.csv").circular();

    // シナリオを構築する
    ScenarioBuilder scn = scenario("BookStoreTest")
            .forever().on(
                    pace(30)
                    .feed(feeder)
                    // Action 1：Open
                    .exec(
                            http("Open")
                            .get("/")
                            .check(
                                    status().is(200),
                                    css("title").is("TopPage"),
                                    css("#csrfToken", "value").saveAs("sessionCsrfToken")
                                    )
                            )
                    .pause(2)
                    // Action 2：Login
                    .exec(
                            http("Login")
                            .post("/processLogin")
                            .formParam("email", "#{userId}")
                            .formParam("password", "#{password}")
                            .formParam("_csrf", "#{sessionCsrfToken}")
                            .check(
                                    // リダイレクトは自動的に行われるため、リダイレクト後にcheckする
                                    status().is(200),
                                    css("title").is("BookSelectPage"),
                                    css("#csrfToken", "value").saveAs("sessionCsrfToken")
                                    )
                            )

                    // デバッグ用コード
                    .exec(
                            (session) -> {
                                System.out.println("Login userId => " + session.getString("userId"));
                                return session;
                    })

                    .pause(2)
                    // Action 3：Add Book
                    .exec(
                            http("Add Book")
                            .post("/addBook")
                            .formParam("bookId", "3")
                            .formParam("_csrf", "#{sessionCsrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("CartViewPage"),
                                    css("#csrfToken", "value").saveAs("sessionCsrfToken")
                                    )
                            )
                    .pause(2)
                    // Action 4：To Select
                    .exec(
                            http("To Select")
                            .get("/toSelect")
                            .check(
                                    status().is(200),
                                    css("title").is("BookSelectPage"),
                                    css("#csrfToken", "value").saveAs("sessionCsrfToken")
                                    )
                            )
                    .pause(2)
                    // Action 5：Add Book
                    .exec(http("Add Book")
                            .post("/addBook")
                            .formParam("bookId", "5")
                            .formParam("_csrf", "#{sessionCsrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("CartViewPage"),
                                    css("#csrfToken", "value").saveAs("sessionCsrfToken")
                                    )
                            )
                    .pause(2)
                    // Action 6：To Select
                    .exec(http("To Select")
                            .get("/toSelect")
                            .check(
                                    status().is(200),
                                    css("title").is("BookSelectPage")
                                    )
                            )
                    .pause(2)
                    // Action 7：To Search
                    .exec(http("To Search")
                            .get("/toSearch")
                            .check(
                                    status().is(200),
                                    css("title").is("BookSearchPage")
                                    )
                            )
                    .pause(2)
                    // Action 8：Search
                    .exec(http("Search")
                            .get("/search")
                            .queryParam("categoryId", "2")
                            .queryParam("keyword", "Cloud")
                            .check(
                                    status().is(200),
                                    css("title").is("BookSelectPage"),
                                    css("#csrfToken", "value").saveAs("sessionCsrfToken")
                                    )
                            )
                    .pause(2)
                    // Action 9：Add Book
                    .exec(http("Add Book")
                            .post("/addBook")
                            .formParam("bookId", "11")
                            .formParam("_csrf", "#{sessionCsrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("CartViewPage"),
                                    css("#csrfToken", "value").saveAs("sessionCsrfToken")
                                    )
                            )
                    .pause(2)
                    // Action 10：Remove Book
                    .exec(http("Remove Book")
                            .post("/removeBook")
                            .formParam("removeBookIdList", "3")
                            .formParam("_csrf", "#{sessionCsrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("CartViewPage"),
                                    css("#csrfToken", "value").saveAs("sessionCsrfToken")
                                    )
                            )
                    .pause(2)
                    // Action 11：Fix
                    .exec(http("Fix")
                            .post("/fix")
                            .formParam("_csrf", "#{sessionCsrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("BookOrderPage"),
                                    css("#csrfToken", "value").saveAs("sessionCsrfToken")
                                    )
                            )
                    .pause(2)
                    // Action 12：Order
                    .exec(http("Order")
                            .post("/order1")
                            .formParam("settlementType", "1")
                            .formParam("_csrf", "#{sessionCsrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("OrderSuccessPage"),
                                    css("#csrfToken", "value").saveAs("sessionCsrfToken")
                                    )
                            )
                    .pause(2)
                    // Action 13：Logout
                    .exec(http("Logout")
                            .post("/processLogout")
                            .formParam("_csrf", "#{sessionCsrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("FinishPage")
                                    )
                            )
                    )
            ;

    // シミュレーション全体の設定
    {
        setUp(
                scn.injectOpen( // 設定済みのシナリオをオープンし、ワークロードモデルを設定する
                        rampUsers(5).during(100) // 100秒かけてユーザー数を5まで増やす
                )
                .protocols(httpProtocol)) // 設定済みのHTTP共通情報を指定する
        .maxDuration(200); // 最大持続時間を200秒に設定する
    }
}