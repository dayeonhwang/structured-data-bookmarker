name := "structured-data-bookmarker"

version := "1.0"

scalaVersion := "2.12.2"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "ammonite-ops" % "1.0.0-RC9",
  "org.scala-js" %%% "scalajs-dom" % "0.9.3",
  "be.doeraene" %%% "scalajs-jquery" % "0.9.2"
)

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSBundlerPlugin)

mainClass in Compile := Some("bookmarker.Bookmarker")

scalaJSUseMainModuleInitializer := true

npmDependencies in Compile += "jquery" -> "2.1.3"
npmDependencies in Compile += "jquery-ui" -> "1.12.1"
webpackConfigFile in fastOptJS := Some(baseDirectory.value / "custom.webpack.config.js")