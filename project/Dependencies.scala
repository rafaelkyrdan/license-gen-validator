import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  val authentikatJwt = "com.jason-goodwin" %% "authentikat-jwt" % "0.4.5"
  val jwtCore = "com.pauldijou" %% "jwt-core" % "0.19.0"
  val jwtCirce = "com.pauldijou" %% "jwt-circe" % "0.19.0"
  val commonsCodec = "commons-codec" % "commons-codec" % "1.10"
  val sprayJson = "io.spray" %% "spray-json" % "1.3.4"
  val typeSafeConfig = "com.typesafe" % "config" % "1.3.2"
  val javaInject = "javax.inject" % "javax.inject" % "1"

}
