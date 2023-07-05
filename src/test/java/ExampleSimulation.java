import io.gatling.core.scenario.Scenario;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class ExampleSimulation  extends Simulation {

    HttpProtocolBuilder httpProtocol =
            http.baseUrl("https://www.bignited.be")
                    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .acceptLanguageHeader("en-US,en;q=0.5")
                    .acceptEncodingHeader("gzip, deflate")
                    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");


   HttpRequestActionBuilder request = http("openHomePage").get("/");

    ScenarioBuilder openWebsite = scenario("Users").exec(request);

    {
        setUp(
                openWebsite.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }
}
