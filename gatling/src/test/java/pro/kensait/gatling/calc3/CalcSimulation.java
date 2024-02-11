package pro.kensait.gatling.calc3;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class CalcSimulation extends Simulation {

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

    ScenarioBuilder scn = scenario("Calculation Scenario")
            .exec(http("Open Homepage")
                    .get("/")
                    .check(status().is(200))
                    .check(css("title").is("CalcInputPage"))
                    )
            .pause(1, 4)

            .forever().on(      // 永遠に繰り返す（ただしmaxDurationが優先される）
            // .repeat(100).on( // 最大100回繰り返す（ただしmaxDurationが優先される）
                    pace(5) // 一時停止時間に関わらず、5秒ごとに実行される
                    .exec(http("Add Operation")
                            .post("/add")
                            .formParam("param1", "30")
                            .formParam("param2", "10")
                            .check(status().is(200))
                            .check(css("title").is("CalcOutputPage"))
                            )
                    .pause(1, 4) // ランダムで1～4秒間、一時停止する
                    .exec(http("Subtract Operation")
                            .post("/subtract")
                            .formParam("param1", "30")
                            .formParam("param2", "10")
                            .check(status().is(200))
                            .check(css("title").is("CalcOutputPage"))
                            )
                    .pause(1, 4)
                    )
            ;
    {
        setUp(scn.injectOpen(
                rampUsers(3).during(30)).protocols(httpProtocol))
                        .maxDuration(50);
    }
}
