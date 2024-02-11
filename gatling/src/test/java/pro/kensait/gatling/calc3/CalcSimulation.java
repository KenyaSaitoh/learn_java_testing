package pro.kensait.gatling.calc3;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class CalcSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0");

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
