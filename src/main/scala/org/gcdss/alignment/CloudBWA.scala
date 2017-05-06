package org.gcdss.alignment

import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

import com.github.lindenb.jbwa.jni.BwaMem
import htsjdk.samtools.ValidationStringency
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
import org.bdgenomics.adam.converters.Converter
import org.bdgenomics.adam.models.{RecordGroupDictionary, SequenceDictionary, SequenceRecord}
import org.bdgenomics.adam.rdd.ADAMContext._
import org.bdgenomics.adam.rdd.read.AlignmentRecordRDD
import org.bdgenomics.formats.avro.AlignmentRecord
import org.gcdss.alignment.utils.{StringETL, Constants, TestSaveArgs}
import org.gcdss.backup.jBWA2

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by xubo on 2017/3/4.
  */

class CloudBWA extends ReadMapping with Serializable {
  override def compute(sc: SparkContext, preRDD: RDD[(String, Iterable[AlignmentRecord])], index: String, batch: Int): RDD[String] = {
    var sam = preRDD.mapPartitions { iter =>
      System.loadLibrary("bwajni")

      var results = new ArrayBuffer[String]()
      var bwaIndex = jBWA2.buildIndex(index)
      val mem: BwaMem = new BwaMem(bwaIndex)
      mem.set_verbosity(1)

      var alignmentRecord1 = new Array[AlignmentRecord](batch);
      var alignmentRecord2 = new Array[AlignmentRecord](batch);
      var sub = 0;
      var flag = true
      iter.foreach { each =>
        if (sub < batch) {
          var arr = each._2.toArray
          alignmentRecord1(sub) = arr(0)
          alignmentRecord2(sub) = arr(1)
          sub = sub + 1
          flag = false
        } else {
          flag = true
          sub = 1
          //add by xubo 20170407
          //          println("*******************")
          //          for(ixubo<-0 until alignmentRecord1.length){
          //            println("########")
          //            println(alignmentRecord1(ixubo))
          //            println(alignmentRecord2(ixubo))
          //          }

          var result = jBWA2.align(mem, alignmentRecord1, alignmentRecord2)
          for (i <- 0 until result.length) {

            if (!(result(i) == null || result(i).equals('\n'))) {
              var tmpResult = result(i).replace("\n", "")
              //              var eachFirst: String = tmpResult
              //              var eachArr = tmpResult.split(";\t")
              //              if (eachArr.length > 1 && eachArr(0).split("\\s+").length > Constants.qualLength) {
              //                eachFirst = eachArr(0)
              //              }
              results += StringETL.splitSAM(tmpResult)
              //              results += eachFirst
              //            results += result(i).replace("\n", "")
              //            results += result(i)
            }

            //            results += result(i).replace("\n", "")
            //            results += result(i)
          }
          alignmentRecord1 = new Array[AlignmentRecord](batch);
          alignmentRecord2 = new Array[AlignmentRecord](batch);
          var arr = each._2.toArray
          alignmentRecord1(0) = arr(0)
          alignmentRecord2(0) = arr(1)
        }
      }

      if (!flag) {
        var alignmentRecord1sub = new Array[AlignmentRecord](sub);
        var alignmentRecord2sub = new Array[AlignmentRecord](sub);
        for (s <- 0 until sub) {
          alignmentRecord1sub(s) = alignmentRecord1(s)
          alignmentRecord2sub(s) = alignmentRecord2(s)
        }
        //add by xubo 20170407
        //        println("*******************")
        //        for(ixubo<-0 until alignmentRecord1.length){
        //          println("########")
        //          println(alignmentRecord1(ixubo))
        //          println(alignmentRecord2(ixubo))
        //        }

        var result = jBWA2.align(mem, alignmentRecord1sub, alignmentRecord2sub)
        for (i <- 0 until result.length) {

          if (!(result(i) == null || result(i).equals('\n'))) {
            var tmpResult = result(i).replace("\n", "")
//            var eachFirst: String = tmpResult
//            var eachArr = tmpResult.split(";\t")
//            if (eachArr.length > 1 && eachArr(0).split("\\s+").length > Constants.qualLength) {
//              eachFirst = eachArr(0)
//            }
//            results += eachFirst
            results += StringETL.splitSAM(tmpResult)
            //            results += result(i).replace("\n", "")
            //            results += result(i)
          }
        }
      }

      bwaIndex.close
      mem.dispose
      results.toIterator
    }
    sam
  }

  var pairdRDD: AlignmentRecordRDD = null

  override def preprocess(sc: SparkContext, fastq1: String, fastq2: String, splitNum: Int): RDD[(String, Iterable[AlignmentRecord])] = {
    if (fastq2 == "null") {
      pairdRDD = sc.loadAlignments(fastq1, stringency = ValidationStringency.LENIENT)
    } else {
      //      pairdRDD = sc.loadFastq(filePath1 = fastq1, filePath2Opt = Option(fastq2))//读出有问题
      pairdRDD = sc.loadAlignments(fastq1, filePath2Opt = Option(fastq2), stringency = ValidationStringency.LENIENT)
    }
    //    println(pairdRDD.recordGroups.recordGroups.length)
    //    println("pairdRDD.count():"+pairdRDD.rdd.count())
    //    pairdRDD.rdd.take(10).foreach(println)
    var a3 = pairdRDD.rdd.groupBy((each: AlignmentRecord) => each.getReadName, new HashPartitioner(splitNum))
    a3
  }
}

object CloudBWA {
  var appArgs: String = null
  var appName = "CloudBWA"
  var appVersion = "0.1.0"
  //  var seqindexLocal = "/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GRCH38chr1L3556522.seqIndex"
  var seqindexLocal = "/home/hadoop/disk2/xubo/ref/GRCH38Index/GCA_000001405.15_GRCh38_full_analysis_set.seqIndex"
  //  lazy val sequenceDictionary: SequenceDictionary = computeSeqDicFromLocal(seqindexLocal)
  var defaultRecordGroupName = "machine foo"
  var defaultRecordGroupSample = "sample"

  def main(args: Array[String]) {
    //    val str1 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL30N1pair1.fastq"
    //    val str2 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL30N1pair2.fastq"
    //    val str1 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL50N2pair1.fastq"
    //    val str2 = "C:\\all\\idea\\jbwa\\test\\fastq\\testL50N2pair2.fastq"
    //    val str1 = "C:\\all\\idea\\jbwa\\test\\fastq\\pair1N10.fastq"
    //    val str2 = "C:\\all\\idea\\jbwa\\test\\fastq\\pair2N10.fastq"
    val startTime = System.currentTimeMillis()
    val index = args(0)
    val fastq1 = args(1)
    val fastq2 = args(2)
    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
    //    val output = args(3) + "time" + iString
    //    val output = args(3) + "time" + iString
    val output = args(3)
    val batch = args(4).toInt
    val numPartition = args(5).toInt
    val seqindex = args(6)
    appArgs = "index:\t" + index + "\tfastq1:\t" + fastq1 + "\tfastq2:\t" + fastq2 + "\toutput:\t" + output + "\tbacth:\t" + batch + "\tnumPatition:\t" + numPartition + "\tseqindex:\t" + seqindex

    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)
    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.OFF)

    val conf = new SparkConf()
      .setAppName("CloudBWA " + appArgs)
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .set("spark.driver.allowMultipleContexts", "true")
      .set("spark.kryo.registrator", "org.bdgenomics.adam.serialization.ADAMKryoRegistrator")
      .set("spark.kryo.referenceTracking", "true")
      .set("spark.kryo.registrationRequired", "true")
    if (System.getProperties.getProperty("os.name").contains("Windows")) {
      conf.setMaster("local[1]")
    }
    var sc = new SparkContext(conf)
    var sqlContext = new SQLContext(sc)
    //    var ac = new ADAMContext(sc)

    val sequenceDictionary: SequenceDictionary = computeSeqDicFromLocal(seqindex)

    var clouBWA = new CloudBWA

    val samRDD = clouBWA.run(sc, index, fastq1, fastq2, output, batch, numPartition)
    //    saveAsSam(sc, samRDD, output, computeSeqDic(sc, seqindex))
    //    seqindexLocal = seqindex
    //    saveAsSam(sc, samRDD, output, computeSeqDicFromLocal(seqindex))
    //    samRDD.cache()// by xubo245 201704011453

    //    verifySamRDD(samRDD)

    saveAsSam(sc, samRDD, output, sequenceDictionary)
    //    saveAsSamPar2(sc, samRDD, output, sequenceDictionary)
    //    saveAsAdam(samRDD, output, sequenceDictionary, clouBWA.pairdRDD.recordGroups)
    //    saveAsAdamParquet(samRDD, output, sequenceDictionary, clouBWA.pairdRDD.recordGroups)
    //    samRDD.unpersist(true)// by xubo245 201704011453
    sc.stop()
    val stopTime = System.currentTimeMillis()
    println(appArgs + "\ttime:\t" + (stopTime - startTime) / 1000.0 + "\t")
  }


  def verifySamRDD(samRDD: RDD[String]): Unit = {
    var filterRDD = samRDD.filter { each =>
      var arr = each.split("\\s+")
      var flag = true
      for (i <- 0 until arr.length) {
        if (flag == false && arr.equals("chr1")) {
          flag = true
        }
        if (flag == true && arr.equals("chr1")) {
          true
        }
      }
      false
    }.take(10)
    for (i <- 0 until filterRDD.length) {
      println(filterRDD(i))
    }
  }

  //  var
  def saveAsSam(sc: SparkContext, samRDD: RDD[String], output: String, seqDic: SequenceDictionary): Unit = {
    var SQ = new ArrayBuffer[String]()
    for (elem <- seqDic.records) {
      var str = new String("@SQ")
      str += ("\tSN:" + elem.name)
      str += ("\tLN:" + elem.length.toString)
      SQ += str
    }
    var RG = new String("@RG")
    RG = RG + "\tID:" + defaultRecordGroupName + "\tSM:" + defaultRecordGroupSample
    SQ += RG

    var PG = new String("@PG")
    PG = PG + "\tID:" + appName + "\tPN:" + appName + "\tVN:" + appName + "-" + appVersion + "\tCL:" + appArgs
    SQ += PG


    var sqArr = SQ.toArray

    //    var sqRDD = sc.parallelize(sqArr, 1)
    var sqRDD = samRDD.context.parallelize(sqArr, 1)
    sqRDD.union(samRDD).saveAsTextFile(output)
  }

  /**
    * 将sam RDD存储为hear和匹配结果两格partition的文件，匹配结果repatition比较耗时，但可以合并所有匹配数据
    *
    * @param sc     SparkContext
    * @param samRDD 匹配数据
    * @param output 输出路径
    * @param seqDic header 参考序列信息
    */
  def saveAsSamPar2(sc: SparkContext, samRDD: RDD[String], output: String, seqDic: SequenceDictionary): Unit = {
    var SQ = new ArrayBuffer[String]()
    for (elem <- seqDic.records) {
      var str = new String("@SQ")
      str += ("\tSN:" + elem.name)
      str += ("\tLN:" + elem.length.toString)
      SQ += str
    }
    var PG = new String("@PG")
    PG = PG + "\tID:" + appName + "\tPN:" + appName + "\tVN:" + appName + "-" + appVersion + "\tCL:" + appArgs
    SQ += PG
    var sqArr = SQ.toArray
    var sqRDD = sc.parallelize(sqArr, 1)
    sqRDD.union(samRDD.repartition(1)).saveAsTextFile(output)
  }

  def saveAsAdam(samRDD: RDD[String], output: String, sequenceDictionary: SequenceDictionary, recordGroupDictionary: RecordGroupDictionary): Unit = {
    //    var converter=new Converter
    val alignmentRecordRDD = Converter.samToAlignmentRecordRDD(samRDD, sequenceDictionary, recordGroupDictionary)
    //    println(alignmentRecordRDD.count())
    //    alignmentRecordRDD.take(10).foreach(println)

    //    alignmentRecordRDD.saveAsTextFile(output)

    alignmentRecordRDD.rdd.saveAsTextFile(output)

    //    alignmentRecordRDD.rdd.saveAsParquet()
    //        alignmentRecordRDD.rdd.saveAsParquet(output,alignmentRecordRDD.sequences,alignmentRecordRDD.recordGroups)
  }

  def tempLocation(suffix: String = ".adam"): String = {
    val tempFile = File.createTempFile("ADAMContextSuite", "")
    val tempDir = tempFile.getParentFile
    new File(tempDir, tempFile.getName + suffix).getAbsolutePath
  }

  def saveAsAdamParquet(samRDD: RDD[String], output: String, sequenceDictionary: SequenceDictionary, recordGroupDictionary: RecordGroupDictionary): Unit = {
    //    var converter=new Converter
    val alignmentRecordRDD = Converter.samToAlignmentRecordRDD(samRDD, sequenceDictionary, recordGroupDictionary)


    //    println(alignmentRecordRDD.count())
    //    val loc = tempLocation(output)
    //    println(loc)
    val args = TestSaveArgs(output)
    //    alignmentRecordRDD.rdd.saveAsParquet(args, alignmentRecordRDD.sequences, alignmentRecordRDD.recordGroups)
    //    alignmentRecordRDD.rdd.saveAsParquet(args)
    alignmentRecordRDD.saveAsParquet(args)
  }


  def computeSeqDic(sc: SparkContext, hdfsIndex: String): SequenceDictionary = {
    var fasta = sc.loadFasta(hdfsIndex, 1000000)
    fasta
    null
  }

  def computeSeqDicFromLocal(localSeqIndex: String): SequenceDictionary = {
    val source = Source.fromFile(localSeqIndex).getLines().toArray
    //    var sequenceRecords = new ArrayBuffer[SequenceRecord]
    var seqDic = new SequenceDictionary()
    source.foreach { str =>
      var arr = str.replace(">", "").split("\\s+")

      var name: String = null
      var length: Long = 0
      var md5: String = null
      var url: String = null
      var refseq: String = null
      var genbank: String = null
      var assembly: String = null
      var species: String = null
      var referenceIndex: Option[Int] = None

      arr.foreach { each =>
        var line = each.split(":")
        if (!each.contains(":")) {
          name = each
        }
        if (line(0).equalsIgnoreCase("LN")) {
          length = line(1).toLong
        }
        if (line(0).equalsIgnoreCase("M5")) {
          md5 = line(1)
        }
      }
      var sequenceRecord = SequenceRecord(name, length, md5, url, refseq, genbank, assembly, species, referenceIndex)
      seqDic = seqDic + sequenceRecord
    }
    seqDic
  }

  //  def run(sc: SparkContext, index: String, fastq1: String, fastq2: String, output: String, batch: Int, splitNum: Int): Unit = {
  //
  //    val pairdRDD = sc.loadFastq(filePath1 = fastq1, filePath2Opt = Option(fastq2))
  //    var a3 = pairdRDD.groupBy((each: AlignmentRecord) => each.getReadName, new HashPartitioner(splitNum))
  //    a3.cache()
  //    a3.unpersist(true)
  //  }
}

