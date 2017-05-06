package org.gcdss.alignment.transform

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.bdgenomics.adam.rdd.ADAMContext._
import org.bdgenomics.adam.rdd.read.AlignmentRecordRDD

/**
  * Created by xubo on 2017/3/5.
  */
object Sam2Adam {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)
    val fastq1 = args(0)
    val fastq2 = args(1)
    val output = args(2)
    val conf = new SparkConf()
      .setAppName("CloudBWA transform:Sam2Adam " + "\tfastq1:" + fastq1 + "\tfastq2:" + fastq2 + "\toutput:" + output)
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    if (System.getProperties.getProperty("os.name").contains("Windows")) {
      conf.setMaster("local[16]")
    }
    var sc = new SparkContext(conf)
    var sqlContext = new SQLContext(sc)
    //    var ac = new ADAMContext(sc)

    compute(sc, args(0), args(1), args(2))
    sc.stop()
  }

  def compute(sc: SparkContext, input1: String, input2: String, output: String): Unit = {
    var RDD = sc.loadAlignments(input1, filePath2Opt = Option(input2))
    var transformRDD = RDD.rdd.map { each =>
      each.setReadName((each.getReadName.split("\\s+"))(0))
      each
    }
    AlignmentRecordRDD(transformRDD, RDD.sequences, RDD.recordGroups).saveAsParquet(output)
  }
}
