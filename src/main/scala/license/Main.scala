package license

import java.time.format.DateTimeFormatter
import java.time.{ LocalDate, ZoneOffset }

import license.generator.Generator
import license.util.AppConfig
import license.validator.Validator

object Main {

  def main(args: Array[String]): Unit = {

    val appConfig = new AppConfig()
    val generator = new Generator(appConfig)
    val validator = new Validator(appConfig)

    if (args.nonEmpty) {
      if (args.head == "generate-key") {
        args.tail match {
          case Array(hostname, date) => {
            val fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val expDate = LocalDate.parse(date, fmt).atStartOfDay().toInstant(ZoneOffset.UTC)
            System.out.println(
              generator.generate(hostname, expDate).getOrElse("")
            )
          }
          case _ => System.out.println("Not enough arguments, submit as: $ ./generate-key <hostname> <expiration-date>")
        }
      } else if (args.head == "validate-key") {
        args.tail match {
          case Array(licenseKey) => System.out.println(validator.validate(licenseKey))
          case _ => System.out.println("Not enough arguments, submit as: $ ./validate-key <key>)")
        }
      } else {
        System.err.println("Not valid command")
      }
    }
  }
}
