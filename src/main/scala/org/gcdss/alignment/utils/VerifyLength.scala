package org.gcdss.alignment.utils

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkContext, SparkConf}
import org.bdgenomics.adam.rdd.read.AlignmentRecordRDD
import org.bdgenomics.adam.rdd.ADAMContext._

/**
  * Created by xubo on 2017/3/9.
  */
object VerifyLength {
  def main(args: Array[String]) {


    val conf = new SparkConf()
      .setAppName("CloudBWAAdam VerifyLength")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    if (System.getProperties.getProperty("os.name").contains("Windows")) {
      conf.setMaster("local[16]")
    }
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)
    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.OFF)
    val sc = new SparkContext(conf)
    val file = args(1)
    if (args(0).equalsIgnoreCase("sam")) {
      verifySam(sc, file)
    } else if (args(0).equalsIgnoreCase("adam")) {
      verifyAdam(sc, file)
    }

  }

  def verifySam(sc: SparkContext, file: String): Unit = {
    val RDD = sc.textFile(file)

    var count = RDD.filter(!_.startsWith("@")).count()
    println("count=" + count + "\t file:" + file)

  }

  def verifyAdam(sc: SparkContext, file: String): Unit = {
    val RDD: AlignmentRecordRDD = sc.loadAlignments(file)
    //    val RDD: AlignmentRecordRDD = sc.loadParquetAlignments(file)
    var count = RDD.rdd.count()
    println("count=" + count + "\t file:" + file)
  }
}
