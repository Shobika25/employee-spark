
package com.training

import org.apache.spark.sql.SparkSession

object EmployeeAnalysis {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("EmployeeAnalysis")
      .getOrCreate()

    try {
      import spark.implicits._

      val employees = Seq(
        ("John", 30000),
        ("David", 40000),
        ("Smith", 50000)
      ).toDF("name", "salary")

      employees.show()

    } finally {
      spark.stop()
    }
  }
}
