
package com.training

import org.apache.spark.sql.SparkSession
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

class EmployeeAnalysisTest extends AnyFunSuite with BeforeAndAfterAll {

  private val spark = SparkSession.builder()
    .appName("EmployeeAnalysisTest")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._

  test("increaseSalary should add 5000 to every employee salary") {

    val employees = Seq(
      ("John", 30000),
      ("David", 40000)
    ).toDF("name", "salary")

    val result = EmployeeTransformations.increaseSalary(employees)

    val salaries = result
      .select("salary")
      .as[Int]
      .collect()
      .sorted

    assert(salaries.sameElements(Array(35000, 45000)))
  }

  test("removeDuplicates should remove duplicate employee records") {

    val employees = Seq(
      ("John", 30000),
      ("John", 30000),
      ("David", 40000)
    ).toDF("name", "salary")

    val result = EmployeeTransformations.removeDuplicates(employees)

    assert(result.count() == 2)
  }

  override protected def afterAll(): Unit = {
    try {
      spark.stop()
    } finally {
      super.afterAll()
    }
  }
}
