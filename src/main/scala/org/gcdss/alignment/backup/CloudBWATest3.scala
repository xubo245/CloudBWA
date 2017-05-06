package org.gcdss.alignment

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.bdgenomics.adam.rdd.ADAMContext
import org.bdgenomics.adam.rdd.ADAMContext._
import org.bdgenomics.formats.avro.AlignmentRecord

/**
  * Created by xubo on 2017/3/4.
  */
object CloudBWATest3 {
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
    //    val str1 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL30N1pair1.fastq"
    //    val str2 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL30N1pair2.fastq"
    //    val str1 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL50N2pair1.fastq"
    //    val str2 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL50N2pair2.fastq"
    val str1 = "C:\\all\\idea\\jbwa\\test\\fastq\\pair1N10.fastq"
    val str2 = "C:\\all\\idea\\jbwa\\test\\fastq\\pair2N10.fastq"

    //    sc.loadPairedFastq(str1,str2,)
    //    val pairdRDD = sc.loadPairedFastq(str1, str2, None, ValidationStringency.STRICT)
    //    val pairdRDD = sc.loadPairedFastq(str1, str2, None, ValidationStringency.STRICT)
    val pairdRDD = sc.loadFastq(filePath1 = str1, filePath2Opt = Option(str2)).rdd
//    var rdd = pairdRDD.rdd

    println(pairdRDD.count())
    pairdRDD.foreach(println)
    var a3=pairdRDD.groupBy{each=>each.getReadName}
    println(a3.count())
    a3.foreach{each=>
    println(each._1+"============"+each._2.size)
      each._2.foreach(println)

    }



    //    pairdRDD.saveAsPairedFastq()
//    val build = AlignmentRecord.newBuilder().build()
//    build.rdd

    //    build.
    //    pairdRDD.foreach(println)
    //    //pairdRDD.
    //    println(pairdRDD.count())


    //    Ordering=NameOrdering
    //        implicit val ord = implicitly[NameOrdering]
    //    import Ordering._
    //    val a2 = PairingRDD.rddToPairingRDD(pairdRDD)
    //    ( sc.getClass,NameOrdering)
    //    println(a2.pair().count())
    //    val a1 = pairdRDD.pair()
    //    (NameOrdering)
    //    println(a1.count)

    //            var pairingRDD=PairingRDD.rddToPairingRDD(pairdRDD)
    //    var double = pairingRDD.pair()
    //    println(double.count())

    //    pairdRDD.mapPartitions { iter =>
    //    iter.
    //    }

    sc.stop()
  }
}

//object NameOrdering extends Ordering[AlignmentRecord] {
//  def compare(a: AlignmentRecord, b: AlignmentRecord): Int =
//    implicitly[Ordering[String]].compare(a.getReadName, b.getReadName)
//}