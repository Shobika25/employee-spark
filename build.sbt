
ThisBuild / scalaVersion := "2.12.18"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.training"

val sparkVersion = "3.5.9"

lazy val root = (project in file("."))
  .settings(
    name := "employee-spark",

    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
      "org.scalatest" %% "scalatest" % "3.2.18" % Test
    ),

    Test / parallelExecution := false,

    Test / fork := true,

    Test / javaOptions ++= Seq(
      "--add-exports=java.base/sun.nio.ch=ALL-UNNAMED",
      "--add-opens=java.base/java.lang=ALL-UNNAMED",
      "--add-opens=java.base/java.nio=ALL-UNNAMED",
      "--add-opens=java.base/java.util=ALL-UNNAMED"
    )
  )
