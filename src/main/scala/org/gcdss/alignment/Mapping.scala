package org.gcdss.alignment

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * Created by xubo on 2017/3/5.
  */
trait Mapping {
  def run(sc: SparkContext, index: String, fastq1: String, fastq2: String, output: String, batch: Int, splitNum: Int): RDD[String]
}
