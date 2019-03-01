package example

import java.time.{ Duration, Instant }

import license.generator.Generator
import license.util.AppConfig
import license.validator.Validator
import org.scalatest._

class LicenseGeneratorValidatorSpec extends FlatSpec with Matchers {

  val appConfig = new AppConfig()
  val generator = new Generator(appConfig)
  val validator = new Validator(appConfig)
  var validKey: String = _

  "Generator" should "not generate a key because expiration date is before now" in {
    val token = generator.generate("company.com", Instant.now.minus(Duration.ofHours(5).plusMinutes(4)))
    token shouldEqual None
  }

  "Generator" should "not generate a key because domain is empty" in {
    val token = generator.generate("", Instant.now.plus(Duration.ofHours(5).plusMinutes(4)))
    token shouldEqual None
  }

  "Generator" should "generate a key" in {
    val token = generator.generate("company.com", Instant.now.plus(Duration.ofHours(5).plusMinutes(4)))
    validKey = token.getOrElse("")
    token.nonEmpty shouldEqual true
  }

  "Validator" should "not accept an empty key" in {
    val output = validator.validate("")
    output shouldEqual "invalid"
  }

  "Validator" should "not accept not valid key" in {
    val output = validator.validate("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyAiZG9tYWluIjogImNvbXBhbnkuY29tIiwgImV4cGlyYXRpb24iOiAxNTUxNDUwODgwIH0.")
    output shouldEqual "invalid"
  }

  "Validator" should "accept a valid key" in {
    val output = validator.validate(validKey)
    output shouldEqual "valid"
  }
}
