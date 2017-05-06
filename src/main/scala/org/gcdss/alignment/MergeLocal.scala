package org.gcdss.alignment

import java.io.PrintWriter

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xubo on 2017/3/29.
  *
  * It is faster than Merge method.
  */
object MergeLocal {
  var out: PrintWriter = null

  def main(args: Array[String]) {
    //    var file=Source.fromFile(args(0))
    //    var buf1=file.buffered
    //    while(buf1.hasNext){
    //
    //    }
    val startTime = System.currentTimeMillis()
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)

    val conf = new SparkConf()
      .setAppName("CloudBWA Merge")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    if (System.getProperties.getProperty("os.name").contains("Windows")) {
      conf.setMaster("local[16]")
    }
    var sc = new SparkContext(conf)
    var sqlContext = new SQLContext(sc)
    //    var ac = new ADAMContext(sc)

    var RDD = sc.textFile(args(0))
    var filename = args(1).toString
    out = new PrintWriter(filename)

    RDD.filter(_.startsWith("@")).foreach { each =>
      write(each)
    }

    RDD.filter(!_.startsWith("@")).foreach { each =>
      write(each)
    }
    out.close()
    //    headerRDD.union(alignRDD).repartition(1).saveAsTextFile(args(1))
    sc.stop()
    val stopTime = System.currentTimeMillis()
    println("\ttime:\t" + (stopTime - startTime) / 1000.0 + "\t")
  }

  def write(each: String): Unit = {
    out.println(each)
  }
}
