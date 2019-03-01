package license.generator

import java.time.Instant

import javax.inject.Inject
import license.util.AppConfig
import pdi.jwt.{ JwtAlgorithm, JwtCirce }

import scala.util.Try

class Generator @Inject()(config: AppConfig) {

  private final val secretKey = config.secretKey

  def generate(hostname: String, validUpTo: Instant): Option[String] = {
    (hostname, validUpTo) match {
      case (str, expiration) if str.isEmpty || expiration.isBefore(Instant.now) => None
      case (_, _) => {
        val claim = s"""{ "hostname": "$hostname", "expiration": ${validUpTo.getEpochSecond} }""".stripMargin
        Try {
          JwtCirce.encode(claim, secretKey, JwtAlgorithm.HS256)
        }.toOption
      }
    }
  }
}
