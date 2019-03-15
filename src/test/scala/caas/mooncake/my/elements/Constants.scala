package caas.mooncake.my.elements

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.core.feeder.FeederBuilder
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

import scala.concurrent.duration._


object Constants {
  val config = ConfigFactory.load()
  val env = System.getProperty("env")
  val tenant = System.getProperty("tenant")
  val url = if(env == "prod") {
    s"https://$tenant-approuter-caas2-sap.cfapps.us10.hana.ondemand.com"
  } else {
    s"https://$tenant-approuter-caas2-sap-$env.cfapps.us10.hana.ondemand.com"
  }

  val numberOfUsers: Int = System.getProperty("numberOfUsers").toInt
  val duration: FiniteDuration = System.getProperty("durationMinutes").toInt.minutes
  val pause: FiniteDuration = System.getProperty("pauseBetweenRequestsMs").toInt.millisecond
  private val repeatTimes: Int = System.getProperty("numberOfRepetitions").toInt
  private val isDebug = System.getProperty("debug").toBoolean

  val customer_token: String = config.getString("token.customer")
  val merchant_token: String = config.getString("token.merchant")
  val responseTimeMs: Int = config.getInt("application.responseTimeMs") //500
  val responseSuccessPercentage: Int = config.getInt("application.responseSuccessPercentage") //99
  val successStatus = config.getIntList("application.successStatus") //200

//  println(s"env is: $env")
//  println(s"tenant is: $tenant")
//  println(s"url is: $url")
//  println(s"successStatus is: $successStatus")
//  println(successStatus.getClass())
//  println("Customer token is: "+customer_token)
//  println("Merchant token is: "+merchant_token)

  // Define HTTP protocol to be used in simulations
  val httpProtocol = http
    .baseUrl(url)
    .header("Authorization", merchant_token)
    // Check response code is 200
//    .check(status.is(successStatus))
}
