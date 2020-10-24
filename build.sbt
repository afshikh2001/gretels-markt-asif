name := """gretels-markt-asif"""
organization := "com.asif.markt"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"        % "3.1.+",
  //"com.h2database"  %  "h2"                 % "1.4.+",
  "ch.qos.logback"  %  "logback-classic"    % "1.2.+"
)
libraryDependencies += "postgresql" % "postgresql" % "9.1-901.jdbc4"


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.asif.markt.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.asif.markt.binders._"
