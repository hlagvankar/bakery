name := "bakery"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq("com.typesafe" % "config" % "1.3.3",
  "com.typesafe.play" %% "play-json" % "2.5.9",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5",
  "org.specs2" %% "specs2-core" % "4.0.1" % Test,
  "org.specs2" %% "specs2-mock" % "4.0.0" % Test,
  "org.scalatest" % "scalatest_2.11" % "3.0.5" % "test")