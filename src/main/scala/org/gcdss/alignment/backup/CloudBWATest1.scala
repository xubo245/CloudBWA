package org.gcdss.alignment

import htsjdk.samtools.ValidationStringency
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.bdgenomics.adam.rdd.ADAMContext
import org.bdgenomics.adam.rdd.ADAMContext._

/**
  * Created by xubo on 2017/3/4.
  */
object CloudBWATest1 {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)

    //   val conf = new SparkConf().setMaster("local[2]").setAppName("MLlibUnitTest")
    //   sc = new SparkContext(conf)
    val conf = new SparkConf()
      .setAppName("CS_bwamem test")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    if (System.getProperties.getProperty("os.name").contains("Windows")) {
      conf.setMaster("local[16]")
    }
    var sc = new SparkContext(conf)
    var sqlContext = new SQLContext(sc)
//    var ac = new ADAMContext(sc)
    var RDD = sc.loadAlignments("C:\\all\\idea\\jbwa\\test\\fastq\\testL30N1pair1.fastq")
//    RDD.foreach(println)

    println()
    val str1="C:\\all\\idea\\jbwa\\test\\fastq\\testL30N1pair1.fastq"
    val str2="C:\\all\\idea\\jbwa\\test\\fastq\\testL30N1pair2.fastq"
    val pairdRDD=sc.loadPairedFastq(str1,str2,None,ValidationStringency.STRICT)
//    pairdRDD.foreach(println)

    sc.stop()
  }
}
