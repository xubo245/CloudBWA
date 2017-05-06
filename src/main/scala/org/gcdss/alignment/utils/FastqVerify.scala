package org.gcdss.alignment.utils

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xubo on 2017/2/22.
  * run success
  */
object FastqVerify {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)
    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
    println("start:")
    //    val fastq1 = "hdfs://219.219.220.149:9000/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time100.samtime20170308213016203"
    val fastq1 = "hdfs://219.219.220.149:9000/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000Nhs20Paired12time6k1.sam"


    val conf = new SparkConf().setMaster("local[16]").setAppName(this.getClass().getSimpleName().filter(!_.equals('$')))
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)
    //    val ac = new ADAMContext(sc)
    val df3 = sc.textFile(fastq1)
    //    val df3 = sqlContext.read.option("mergeSchema", "true").parquet(output + "/0")
    df3.take(3).foreach(println)
    //    println("count" + df3.count())

    var samRDD = df3.filter(!_.startsWith("@")).map { each =>
      var arr = each.split("\\s+")
      arr(10)
    }
    samRDD.take(10).foreach(println)
    //    val df4 = sc.loadAlignments(fastq1).rdd
    println(samRDD.count)
    //    println()
    println("end")
  }
}
