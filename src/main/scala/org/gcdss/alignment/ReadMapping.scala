package org.gcdss.alignment

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.bdgenomics.formats.avro.AlignmentRecord

/**
  * Created by xubo on 2017/3/5.
  */
abstract class ReadMapping extends Mapping {


  def compute(sc: SparkContext, preRDD: RDD[(String, Iterable[AlignmentRecord])], index: String, batch: Int): RDD[String]

  def preprocess(sc: SparkContext, fastq1: String, fastq2: String, splitNum: Int): RDD[(String, Iterable[AlignmentRecord])]

  override def run(sc: SparkContext, index: String, fastq1: String, fastq2: String, output: String, batch: Int, splitNum: Int): RDD[String] = {
    var preRDD = preprocess(sc, fastq1, fastq2, splitNum)
    //    println("preRDD.count():"+preRDD.count())
//    preRDD.cache()
    var resultRDD = compute(sc, preRDD, index, batch)
    //    println("resultRDD.count():"+resultRDD.count())
    resultRDD
  }
}

