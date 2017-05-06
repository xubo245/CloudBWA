package org.gcdss.alignment.test

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.spark.{SparkConf, SparkContext}
import org.bdgenomics.adam.rdd.ADAMContext

/**
  * Created by xubo on 2017/2/22.
  * run success
  */
object LoadArrayFastq {
  def main(args: Array[String]) {
    //    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    //    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    //    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)
    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
    println("start:")
    //    val fastq1 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired1.fastq"
    //    val fastq2 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired2.fastq"
    val conf = new SparkConf().setMaster("local[16]").setAppName(this.getClass().getSimpleName().filter(!_.equals('$')))
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)
    val ac = new ADAMContext(sc)
    for (num<-Array(1,5,6,7,8,9,10,20,30,40,50,100)){


//    var num = 100
    val fastq1 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newsmall/newg38L50c" + num + "Nhs20Paired1.fastq"

    val fastq2 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newsmall/newg38L50c" + num + "Nhs20Paired2.fastq"


    val df3 = ac.loadAlignments(fastq1, filePath2Opt = Option(fastq2)).rdd
    //    val df3 = sqlContext.read.option("mergeSchema", "true").parquet(output + "/0")
    //    val df3 = sqlContext.read.option("mergeSchema", "true").parquet(output + "/0")
    //    df3.printSchema()
    //    df3.show()
    df3.take(10).foreach(println)
    //    df3.take(10).foreach { each =>
    //      println(each.start)
    //    }
    println("count:" + df3.count())

    val df4 = ac.loadAlignments(fastq1).rdd
    println(df4.count)
    val df5 = ac.loadAlignments(fastq2).rdd
    println(df5.count)
    println("end")

    }
    sc.stop()
  }
}
