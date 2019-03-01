package license.models

import java.time.Instant
import spray.json.{ JsNumber, JsString, _ }


case class Claim(hostname: String, expiration: Instant)

object Claim {
  def fromJson(json: String): Claim = {
    val obj = json.parseJson.asJsObject
    val hostname = obj.getFields("hostname").map { case JsString(str) => str }.headOption.getOrElse("")
    val expiration = obj.getFields("expiration").map { case JsNumber(d) => d.toLong }.headOption.getOrElse(0l)
    Claim(hostname, Instant.ofEpochSecond(expiration))
  }
}
