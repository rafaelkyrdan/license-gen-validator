package license.util

import com.typesafe.config.ConfigFactory
import javax.inject.Singleton

@Singleton
class AppConfig() {
  private val config = ConfigFactory.load()
  val secretKey = config.getString("secretKey")
  val hostname = config.getString("hostname")
}
