package license.validator

import java.time.Instant

import javax.inject.Inject
import license.models.Claim
import license.util.AppConfig
import pdi.jwt.{ JwtAlgorithm, JwtCirce }

import scala.util.{ Failure, Success }

class Validator @Inject()(config: AppConfig) {

  private final val secretKey = config.secretKey
  private final val hostname = config.hostname

  def validate(token: String): String = {
    JwtCirce.decode(token, secretKey, Seq(JwtAlgorithm.HS256)) match {
      case Success(claims) => {
        val claim = Claim.fromJson(claims.toJson)
        if (isValid(claim)) "valid" else "invalid"
      }
      case Failure(e) => "invalid"
    }
  }

  private def isValid(claim: Claim): Boolean = {
    claim.hostname == hostname && claim.expiration.isAfter(Instant.now)
  }
}
