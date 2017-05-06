package org.gcdss.alignment

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.bdgenomics.adam.rdd.ADAMContext._

import scala.io.Source

/**
  * Created by xubo on 2017/3/7.
  */

object saveAsAdamTest {
  def main(args: Array[String]) {


    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)
    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
    println("start:")
    //    val fastq1 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired1.fastq"
    //
    //    val fastq2 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired2.fastq"

    //    @transient
    val conf = new SparkConf().setMaster("local[16]").setAppName(this.getClass().getSimpleName().filter(!_.equals('$')))
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    //    @transient
    val sc = new SparkContext(conf)

    val sam = "file/sam/NA12878_snp_A2G_chr20_225058.sam"
    //    val sam = "file/sam/NA12878_snp_A2G_chr20_225058Test.sam"
    var lines = Source.fromFile(sam).getLines().filter(!_.startsWith("@")).toArray
    //    println(lines.length)
    //    for (i <- 0 until lines.length) {
    //      println(lines(i))
    //    }

    val samRDD = sc.parallelize(lines)

    val RDD = sc.loadAlignments(sam)

    //    CloudBWA.saveAsAdam(samRDD, "file/sam/NA12878_snp_A2G_chr20_225058.sam.adam" + iString, RDD.sequences, RDD.recordGroups)
    CloudBWA.saveAsAdamParquet(samRDD, "file/sam/NA12878_snp_A2G_chr20_225058.samTime" + iString + ".adam", RDD.sequences, RDD.recordGroups)

  }
}
