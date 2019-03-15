package caas.mooncake.my.simulations

import caas.mooncake.my.elements.Constants._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DemoSimulation extends Simulation {

  val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(http("request_1")
    .get("/order-broker/merchants/orders")
    .queryParam("pageSize", "1")
    .queryParam("pageNumber", "1"))
    .exec(session => {
      // Debug session to verify in tests output all values are correct
      println(session)
      session
    })


  setUp(scn.inject(atOnceUsers(1)))
    .protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(2000),
      global.successfulRequests.percent.gt(99),
      forAll.failedRequests.percent.lt(5)
    )

}
