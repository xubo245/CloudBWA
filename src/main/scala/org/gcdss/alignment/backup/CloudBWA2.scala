//package org.gcdss.alignment.backup
//
//import java.text.SimpleDateFormat
//import java.util.Date
//
//import com.github.lindenb.jbwa.jni.BwaMem
//import org.apache.log4j.{Level, Logger}
//import org.apache.spark.sql.SQLContext
//import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
//import org.bdgenomics.adam.rdd.ADAMContext
//import org.bdgenomics.adam.rdd.ADAMContext._
//import org.bdgenomics.formats.avro.AlignmentRecord
//import org.gcdss.backup.jBWA2
//
//import scala.collection.mutable.ArrayBuffer
//
///**
//  * Created by xubo on 2017/3/4.
//  */
//object CloudBWA2 {
//  var appName: String = null
//
//  def main(args: Array[String]) {
//    //    val str1 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL30N1pair1.fastq"
//    //    val str2 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL30N1pair2.fastq"
//    //    val str1 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL50N2pair1.fastq"
//    //    val str2 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL50N2pair2.fastq"
//    //    val str1 = "C:\\all\\idea\\jbwa\\test\\fastq\\pair1N10.fastq"
//    //    val str2 = "C:\\all\\idea\\jbwa\\test\\fastq\\pair2N10.fastq"
//    val startTime = System.currentTimeMillis()
//    val index = args(0)
//    val fastq1 = args(1)
//    val fastq2 = args(2)
//    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
//    val output = args(3) + iString
//    val batch = 5000
//    val numPartition = 46
//
//    appName = "index:\t" + index + "\tfastq1:\t" + fastq1 + "\tfastq2:\t" + fastq2 + "\toutput\t" + output + "\tbacth\t" + batch + "\tnumPatition\t" + numPartition
//    compute(index, fastq1, fastq2, output, batch, numPartition)
//    val stopTime = System.currentTimeMillis()
//    println(appName + "\ttime:\t" + (stopTime - startTime) / 1000.0 + "\t")
//  }
//
//  def compute(index: String, fastq1: String, fastq2: String, output: String, batch: Int, splitNum: Int): Unit = {
//    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
//    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR)
//    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)
//
//    //   val conf = new SparkConf().setMaster("local[2]").setAppName("MLlibUnitTest")
//    //   sc = new SparkContext(conf)
//    val conf = new SparkConf()
//      .setAppName("CloudBWA " + appName)
//      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
//    if (System.getProperties.getProperty("os.name").contains("Windows")) {
//      conf.setMaster("local[1]")
//    }
//    //    conf.setMaster("local[1]")
//    var sc = new SparkContext(conf)
//    var sqlContext = new SQLContext(sc)
//    var ac = new ADAMContext(sc)
//
//    println()
//
//
//    //    sc.loadPairedFastq(str1,str2,)
//    //    val pairdRDD = sc.loadPairedFastq(str1, str2, None, ValidationStringency.STRICT)
//    //    val pairdRDD = sc.loadPairedFastq(str1, str2, None, ValidationStringency.STRICT)
//    val pairdRDD = sc.loadFastq(filePath1 = fastq1, filePath2Opt = Option(fastq2)).rdd
//
//    var a3 = pairdRDD.groupBy((each: AlignmentRecord) => each.getReadName, new HashPartitioner(splitNum))
//    //    println(a3.count())
//    //    a3.foreach { each =>
//    //      println(each._1 + "============" + each._2.size)
//    //      each._2.foreach(println)
//    //    }
//    //    val fasta = "";
//
//
//    //    val bwaIdxGlobal = sc.broadcast(new ReferenceBroadcast(sc.broadcast(null), true, index))
//
//    //    var test = bwaIdxGlobal.value.value()
//
//    var sam = a3.mapPartitions { iter =>
//
//      System.loadLibrary("bwajni")
//
//      var results = new ArrayBuffer[String]()
//
//
//      var bwaIndex = jBWA2.buildIndex(index)
//      val mem: BwaMem = new BwaMem(bwaIndex)
//      mem.set_verbosity(1)
//
//
//      //      val mem: BwaMem = new BwaMem(bwaIndex)
//      //      //        if (args.length == 4) {
//      //      mem.set_verbosity(4)
//
//      //      iter.next()
//      var alignmentRecord1 = new Array[AlignmentRecord](batch);
//      var alignmentRecord2 = new Array[AlignmentRecord](batch);
//      var sub = 0;
//      var flag = true
//      iter.foreach { each =>
//        if (sub < batch) {
//          var arr = each._2.toArray
//          alignmentRecord1(sub) = arr(0)
//          alignmentRecord2(sub) = arr(1)
//          sub = sub + 1
//          flag = false
//        } else {
//          flag = true
//          sub = 0
//          var result = jBWA2.align(mem, alignmentRecord1, alignmentRecord2)
//          //          var result = jBWA.align(bwaIdxGlobal.value.value(), alignmentRecord1, alignmentRecord2)
//          println("1********************************************************************" + result.length)
//          for (i <- 0 until result.length) {
//            results += result(i).replace("\n", "")
//          }
//          alignmentRecord1 = new Array[AlignmentRecord](batch);
//          alignmentRecord2 = new Array[AlignmentRecord](batch);
//        }
//      }
//
//      if (!flag) {
//        var alignmentRecord1sub = new Array[AlignmentRecord](sub);
//        var alignmentRecord2sub = new Array[AlignmentRecord](sub);
//        for (s <- 0 until sub) {
//          alignmentRecord1sub(s) = alignmentRecord1(s)
//          alignmentRecord2sub(s) = alignmentRecord2(s)
//        }
//        var result = jBWA2.align(mem, alignmentRecord1, alignmentRecord2)
//        //        var result = jBWA.align(bwaIdxGlobal.value.value(), alignmentRecord1sub, alignmentRecord2sub)
//        println("2********************************************************************" + result.length)
//        for (i <- 0 until result.length) {
//          //          println("result:" + i + ":" + result(i))
//          if (!(result(i).equals(null) || result(i).equals('\n'))) {
//            results += result(i).replace("\n", "")
//          }
//        }
//
//      }
//      bwaIndex.close
//      mem.dispose
//
//      //      results.filter { each =>
//      //        !each.equals('\n')
//      //      }.toIterator
//      results.toIterator
//    }
//    //    bwaIdxGlobal.value.reference.dispose()
//    sam.cache()
//    //    sam.map { each =>
//    //      each.replace("\n", "")
//    //    }.take(10).foreach { each =>
//    //      println("*****************start***************")
//    //      println(each)
//    //      println("*****************end***************")
//    //    }
//    //    println(sam.count())
//    //    println(
//    //      sam.filter { each =>
//    //        !each.contains("\n\n")
//    //      }.count()
//    //    )
//    //    sam.map { each =>
//    //      each.replace("\n", "")
//    //    }.saveAsTextFile(output)
//    sam.saveAsTextFile(output)
//    sam.unpersist(true)
//    sc.stop()
//  }
//}
//
