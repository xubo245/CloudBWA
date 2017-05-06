package org.gcdss.alignment.utils

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}
import org.bdgenomics.adam.rdd.ADAMContext
import org.bdgenomics.adam.rdd.ADAMContext._
/**
  * Created by xubo on 2017/2/22.
  * run success
  */
object LoadByAdam {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)
    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
    println("start:")
    val output = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired.adam"

    val conf = new SparkConf().setMaster("local[16]").setAppName(this.getClass().getSimpleName().filter(!_.equals('$')))
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
//    val ac = new ADAMContext(sc)
//    val df3 = ac.loadAlignments(output)
    val df3 = sc.loadAlignments(output)
    //    val df3 = sqlContext.read.option("mergeSchema", "true").parquet(output + "/0")
    //    val df3 = sqlContext.read.option("mergeSchema", "true").parquet(output + "/0")
    //    df3.printSchema()
    //    df3.show()

    println("count" + df3.rdd.count())
    println("end")
  }
}
