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
                    .exec(
                            http("Open")
                            .get("/")
                            .check(
                                    status().is(200),
                                    css("title").is("TopPage"),
                                    css("#csrfToken", "value").saveAs("csrfToken")
                                    )
                            )
                    .pause(2)

                    .exec(
                            http("Login")
                            .post("/processLogin")
                            .formParam("email", "#{userId}")
                            .formParam("password", "#{password}")
                            .formParam("_csrf", "#{csrfToken}")
                            .check(
                                    // リダイレクトは自動的に行われるため、リダイレクト後にcheckする
                                    status().is(200),
                                    css("title").is("BookSelectPage"),
                                    css("#csrfToken", "value").saveAs("csrfToken")
                                    )
                            )

                    // デバッグ用コード
                    .exec(
                            (session) -> {
                                System.out.println("Login userId => " + session.getString("userId"));
                                return session;
                    })

                    .pause(2)

                    .exec(
                            http("Add Book 1")
                            .post("/addBook")
                            .formParam("bookId", "3")
                            .formParam("_csrf", "#{csrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("CartViewPage"),
                                    css("#csrfToken", "value").saveAs("csrfToken")
                                    )
                            )
                    .pause(2)

                    .exec(
                            http("To Select 2")
                            .get("/toSelect")
                            .check(
                                    status().is(200),
                                    css("title").is("BookSelectPage"),
                                    css("#csrfToken", "value").saveAs("csrfToken")
                                    )
                            )
                    .pause(2)

                    .exec(http("Add Book 2")
                            .post("/addBook")
                            .formParam("bookId", "5")
                            .formParam("_csrf", "#{csrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("CartViewPage"),
                                    css("#csrfToken", "value").saveAs("csrfToken")
                                    )
                            )
                    .pause(2)

                    .exec(http("To Select 3")
                            .get("/toSelect")
                            .check(
                                    status().is(200),
                                    css("title").is("BookSelectPage")
                                    )
                            )
                    .pause(2)

                    .exec(http("To Search")
                            .get("/toSearch")
                            .check(
                                    status().is(200),
                                    css("title").is("BookSearchPage")
                                    )
                            )
                    .pause(2)

                    .exec(http("Search")
                            .get("/search")
                            .queryParam("categoryId", "2")
                            .queryParam("keyword", "Cloud")
                            .check(
                                    status().is(200),
                                    css("title").is("BookSelectPage"),
                                    css("#csrfToken", "value").saveAs("csrfToken")
                                    )
                            )
                    .pause(2)

                    .exec(http("Add Book 3")
                            .post("/addBook")
                            .formParam("bookId", "11")
                            .formParam("_csrf", "#{csrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("CartViewPage"),
                                    css("#csrfToken", "value").saveAs("csrfToken")
                                    )
                            )
                    .pause(2)

                    .exec(http("Remove Book")
                            .post("/removeBook")
                            .formParam("removeBookIdList", "3")
                            .formParam("_csrf", "#{csrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("CartViewPage"),
                                    css("#csrfToken", "value").saveAs("csrfToken")
                                    )
                            )
                    .pause(2)

                    .exec(http("Fix")
                            .post("/fix")
                            .formParam("_csrf", "#{csrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("BookOrderPage"),
                                    css("#csrfToken", "value").saveAs("csrfToken")
                                    )
                            )
                    .pause(2)

                    .exec(http("Order")
                            .post("/order1")
                            .formParam("settlementType", "1")
                            .formParam("_csrf", "#{csrfToken}")
                            .check(
                                    status().is(200),
                                    css("title").is("OrderSuccessPage"),
                                    css("#csrfToken", "value").saveAs("csrfToken")
                                    )
                            )
                    .pause(2)

                    .exec(http("Logout")
                            .post("/processLogout")
                            .formParam("_csrf", "#{csrfToken}")
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
                scn.injectOpen( // 既出のシナリオ（ScenarioBuilder）をオープンし、ワークロードモデルを設定する
                        rampUsers(5).during(100) // 100秒かけてユーザー数を5まで増やす
                )
                .protocols(httpProtocol)) // 既出のHTTP共通情報（HttpProtocolBuilder）を指定する
        .maxDuration(200); // 最大持続時間を200秒に設定する
    }
}