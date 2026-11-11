package com.training

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object EmployeeTransformations {

  def increaseSalary(df: DataFrame): DataFrame = {
    df.withColumn("salary", col("salary") + 5000)
  }

  def removeDuplicates(df: DataFrame): DataFrame = {
    df.dropDuplicates()
  }
}