package org.gcdss.alignment

import java.io.File

import htsjdk.variant.utils.SAMSequenceDictionaryExtractor
import org.bdgenomics.adam.models.{SequenceDictionary, SequenceRecord}
import org.gcdss.alignment.utils.GcdssAlignmentFunSuite

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by xubo on 2017/3/6.
  */
class CloudBWASuite extends GcdssAlignmentFunSuite {


  test("test ;\\s++") {
    var arr = Array("7,-,30M20S,0,0;\tchr1_99683", "0574,50M,1;\t")
    arr.foreach(println)
    arr.foreach { each =>
      var a1 = each.split(";\t")
      if (a1.length > 1) {
        println(a1(0))
      }

    }
  }

  test("test ;\\s++ from file") {
    var source = Source.fromFile("file/sam/part-00017").getLines().toArray
    source.foreach { each =>
      var a1 = each.split(";\t")
      if (a1.length > 1) {
        println(a1(0))
      }
    }
  }
  test("sam string to parse") {

    var str = "chr1_99683104_99683640_1:0:0_6:0:0_15655e 73      chr1    91199704        0       31M19S  =       91199704        0       AAGAAAGAAAGAAAGAAAGAAAGCAAGAAAGAAAGAAAGAAAGAAAGAAA      22222222222222222222222222222222222222222222222222      NM:i:0  MD:Z:31 AS:i:31 XS:i:31 SA:Z:chr1,12789437,-,30M20S,0,0;        chr1_99683104_99683640_1:0:0_6:0:0_15655e       2169    chr1    12789437        0       30M20H  =       12789437        0       TTTCTTTCTTTCTTTCTTTCTTTCTTGCTT  222222222222222222222222222222  NM:i:0  MD:Z:30 AS:i:30 XS:i:30 SA:Z:chr1,91199704,+,31M19S,0,0;"

  }

  test("test saveAsAdam") {
    //    val sam = "file/sam/NA12878_snp_A2G_chr20_225058.sam"
    //    var lines = Source.fromFile(sam).getLines().filter(!_.startsWith("@")).toArray
    //    println(lines.length)
    //    for (i <- 0 until lines.length) {
    //      println(lines(i))
    //    }
    //
    //    val samRDD = sc.parallelize(lines)
    //
    //    val RDD = ac.loadAlignments(sam)
    //
    //    CloudBWA.saveAsAdam(samRDD, "file/sam/NA12878_snp_A2G_chr20_225058.sam.adam", RDD.sequences, RDD.recordGroups)

  }


  test("union") {
    var rdd = sc.parallelize(Array("a", "b"), 1)
    println(rdd.count())
    rdd.foreach(println)

    var rdd2 = sc.parallelize(Array("x1", "y1", "z1"), 1)
    println(rdd2.count())
    rdd2.foreach(println)

    var rdd3 = rdd.union(rdd2)
    println(rdd3.count())
    rdd3.foreach(println)

  }

  test("Record group test") {
    var fasta = ac.loadFasta("file/bwaindex/GRCH38chr1L3556522.fasta", 1000000)
    //    fasta.take(1).foreach(println)
    fasta.rdd.take(1).foreach(println)

    fasta.rdd.take(1).foreach { each =>
      println(each.getContig.getContigName)
      println(each.getContig)
    }
  }
  test("head") {
    var fasta = ac.loadFasta("file/bwaindex/GRCH38chr1L3556522.fasta", 1000000)
    fasta.rdd.take(1).foreach(println)
    //    fasta.sequences.records.take(1).foreach(println)
    //    var a1 = fasta.sequences
    //
    //    a1.records.foreach(println)
    //    var test= fasta.sequences.records.take(1).startIndex
  }

  test("dic") {
    var path = "file/bwaindex/dict_with_accession.dict"
    val ssd = SAMSequenceDictionaryExtractor.extractDictionary(new File(path))
    var str = ssd.getSequence("1")
    println(str)
  }

  test("computeSeqDic") {
    var seqDic = CloudBWA.computeSeqDic(sc, "file/bwaindex/GRCH38chr1L3556522.fasta")
    var SQ = new ArrayBuffer[String]()

    for (elem <- seqDic.records) {
      var str = new String("@SQ")
      println("elem" + elem)
      println("elem name" + elem.name)
      println("elem length" + elem.length)
      str += ("\tSN:" + elem.name)
      //      if (!elem.length.equals(null)) {
      str += ("\tLN:" + elem.length.toString)
      //      }
      SQ += str
      str = null
    }
    var sqArr = SQ.toArray
    for (i <- 0 until sqArr.length) {
      println(sqArr(i))
    }
  }

  test("seq index") {
    var input = "file/bwaindex/GCA_000001405.15_GRCh38_full_analysis_set.seqIndex"
    val source = Source.fromFile(input).getLines().toArray
    //    var sequenceRecords = new ArrayBuffer[SequenceRecord]
    var sequenceDictionary = new SequenceDictionary()
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
      sequenceDictionary = sequenceDictionary + sequenceRecord
    }
    //        if (line(0).equalsIgnoreCase("AC")) {
    //          name = line(1)
    //        }
    //        if (line(0).equalsIgnoreCase(" gi")) {
    //          name = line(1)
    //        }
    //        if (line(0).equalsIgnoreCase("rl")) {
    //          name = line(1)
    //        }
    //
    //        if (line(0).equalsIgnoreCase("AS")) {
    //          name = line(1)
    //        }
    sequenceDictionary.records.foreach(println)
    //    var a1=sequenceDictionary.records
    //    for (i<-a1){
    //      println(i.md5)
    //    }

  }

}
