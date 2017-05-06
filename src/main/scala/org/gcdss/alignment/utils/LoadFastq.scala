package org.gcdss.alignment.utils

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.bdgenomics.adam.rdd.ADAMContext._

/**
  * Created by xubo on 2017/2/22.
  * run success
  */
object LoadFastq {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)
    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
    println("start:")
    val fastq1 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired1.fastq"

    val fastq2 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired2.fastq"

    val conf = new SparkConf().setMaster("local[16]").setAppName(this.getClass().getSimpleName().filter(!_.equals('$')))
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)
    //    val ac = new ADAMContext(sc)
    val df3 = sc.loadAlignments(fastq1, filePath2Opt = Option(fastq2)).rdd
    //    val df3 = sqlContext.read.option("mergeSchema", "true").parquet(output + "/0")
    df3.take(3).foreach(println)
    println("count" + df3.count())
    val df4 = sc.loadAlignments(fastq1).rdd
    println(df4.count)
    val df5 = sc.loadAlignments(fastq2).rdd
    println(df5.count)
    println("end")
  }
}
