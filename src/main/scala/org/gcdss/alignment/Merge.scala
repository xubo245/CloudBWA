package org.gcdss.alignment

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.bdgenomics.adam.rdd.ADAMContext

/**
  * Created by xubo on 2017/3/7.
  */
object Merge {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)

    val conf = new SparkConf()
      .setAppName("CloudBWA Merge")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    if (System.getProperties.getProperty("os.name").contains("Windows")) {
      conf.setMaster("local[1]")
    }
    var sc = new SparkContext(conf)
    var sqlContext = new SQLContext(sc)
//    var ac = new ADAMContext(sc)

    var RDD=sc.textFile(args(0))
    var alignRDD=RDD.filter(!_.startsWith("@")).repartition(1)
    var headerRDD=RDD.filter(_.startsWith("@"))

      headerRDD.union(alignRDD).repartition(1).saveAsTextFile(args(1))
    sc.stop()
  }
}
