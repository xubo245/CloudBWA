package org.gcdss.alignment.utils

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.bdgenomics.adam.rdd.ADAMContext._
import org.bdgenomics.adam.rdd.read.AlignmentRecordRDD

/**
  * Created by xubo on 2017/3/8.
  */
object LoadParquet {
  def main(args: Array[String]) {

    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
    println("start:")
    val fastq1 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired1.fastq"

    val fastq2 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired2.fastq"

    val conf = new SparkConf().setMaster("local[16]").setAppName(this.getClass().getSimpleName().filter(!_.equals('$')))
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.OFF)
    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.OFF)
    val sc = new SparkContext(conf)
    //    val ac = new ADAMContext(sc)
//    val par = "file/sam/NA12878_snp_A2G_chr20_225058.samTime20170308170928186.adam"
    val par = "file/sam/NA12878_snp_A2G_chr20_225058.samTime20170309151832488.adam"
    val adam2 = "hdfs://219.219.220.149:9000/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time6k1.adam"


    //    val df3:AlignmentRecordRDD = sc.loadParquet(par)
    val df3: AlignmentRecordRDD = sc.loadAlignments(adam2)
    df3.recordGroups.recordGroups.foreach(println)
    println("Count="+df3.rdd.count())
    df3.rdd.take(10).foreach(println)
  }
}
