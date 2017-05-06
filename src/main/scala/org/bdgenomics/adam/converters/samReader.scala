package org.bdgenomics.adam.converters

import java.text.SimpleDateFormat
import java.util.Date

import htsjdk.samtools.SAMLineParser
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.bdgenomics.adam.rdd.ADAMContext._

import scala.io.Source

/**
  * Created by xubo on 2017/3/7.
  */
object samReader {
  def main(args: Array[String]) {
    val file = Source.fromFile("file/sam/testL50N1pair.sam").getLines()

    var fil = file.filter(!_.startsWith("@"))

    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    Logger.getLogger("org.apache.parquet.hadoop").setLevel(Level.ERROR)
    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
    println("start:")
    //    val fastq1 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired1.fastq"
    //
    //    val fastq2 = "hdfs://219.219.220.149:9000/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired2.fastq"

    val conf = new SparkConf().setMaster("local[16]").setAppName(this.getClass().getSimpleName().filter(!_.equals('$')))
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)
    //    val ac = new ADAMContext(sc)
    val sam = "file/sam/NA12878_snp_A2G_chr20_225058.sam"
    val RDD = sc.loadAlignments(sam)
    //    val RDD = sc.loadAlignments(sam, stringency = ValidationStringency.LENIENT)


    //    fil.foreach { each =>
    //      println(each)
    val samRecordConverter = new SAMRecordConverter
    //      samRecordConverter.convert(p._2.get, seqDict, readGroups)

    val arc = new AlignmentRecordConverter
    var SAMHeader = arc.createSAMHeader(RDD.sequences, RDD.recordGroups)
    val sAMLineParser = new SAMLineParser(SAMHeader)
    //    var samRecord = sAMLineParser.parseLine("20GAVAAXX100126:6:42:3292:192725\t163\t20\t224977\t60\t101M\t=\t225216\t339\tCAAGGATAGAATTTCTAGAAAGTTCCTTCCCCTAAAGCTTTCACACTTGCCTCAGTGTATATATGTGGCTATACCACTGACAGGCCGCCAGTCATTAAATT\t?BDCBCDDEEFFDDEGEEFFFEDEEEFEEDEEFEEFDEDDCDECDAECE?EEEDECDCCFECEEEEEEEEBDEAACAEDEADEDCC=CCECDCEDDCDB@A\tMD:Z:101\tPG:Z:BWA\tRG:Z:20GAV.6\tAM:i:37\tNM:i:0\tSM:i:37\tMQ:i:60\tOQ:Z:BBBBBBBCBBBBBBBBBBBBBBABBBBBBABBBBBBBBBBBBBBBABAB@BABAB@B@BBBABBB@BBBAAABA@@@@B@@@A@A@A@@@@?@@@@@@;=@\tUQ:i:0")
    var str = "20GAVAAXX100126:6:42:3292:192725\t163\t20\t224977\t60\t101M\t=\t225216\t339\tCAAGGATAGAATTTCTAGAAAGTTCCTTCCCCTAAAGCTTTCACACTTGCCTCAGTGTATATATGTGGCTATACCACTGACAGGCCGCCAGTCATTAAATT\t?BDCBCDDEEFFDDEGEEFFFEDEEEFEEDEEFEEFDEDDCDECDAECE?EEEDECDCCFECEEEEEEEEBDEAACAEDEADEDCC=CCECDCEDDCDB@A\tMD:Z:101\tPG:Z:BWA\tRG:Z:20GAV.6\tAM:i:37\tNM:i:0\tSM:i:37\tMQ:i:60\tOQ:Z:BBBBBBBCBBBBBBBBBBBBBBABBBBBBABBBBBBBBBBBBBBBABAB@BABAB@B@BBBABBB@BBBAAABA@@@@B@@@A@A@A@@@@?@@@@@@;=@\tUQ:i:0"
    var str2 = "chr1_99683104_99683640_1:0:0_6:0:0_15655e   73  chr1    91199704    0   31M19S  =   91199704    0   AAGAAAGAAAGAAAGAAAGAAAGCAAGAAAGAAAGAAAGAAAGAAAGAAA  22222222222222222222222222222222222222222222222222  NM:i:0  MD:Z:31 AS:i:31 XS:i:31 SA:Z:chr1,13203351,+,20S30M     ,0,0;    chr1_99683104_99683640_1:0:0_6:0:0_15655e   2121    chr1    13203351    0   20H30M  =   13203351    0   AAGCAAGAAAGAAAGAAAGAAAGAAAGAAA  222222222222222222222222222222  NM:i:0  MD:Z:30 AS:i:30 XS:i:30 SA:Z:chr1,91199704,+,31M19S,0,0;"
    var str21 = "20GAVAAXX100126:6:42:3292:192725\t73\t20\t91199704\t0\t31M19S\t=\t91199704\t0\tAAGAAAGAAAGAAAGAAAGAAAGCAAGAAAGAAAGAAAGAAAGAAAGAAA\t22222222222222222222222222222222222222222222222222\tNM:i:0\tMD:Z:31\tAS:i:31\tXS:i:31\tSA:Z:chr1,13203351,+,20S30M,0,0;"
    var str22 = "chr1_99683104_99683640_1:0:0_6:0:0_15655e   73  chr1    91199704    0   31M19S  =   91199704    0   AAGAAAGAAAGAAAGAAAGAAAGCAAGAAAGAAAGAAAGAAAGAAAGAAA  22222222222222222222222222222222222222222222222222  NM:i:0  MD:Z:31 AS:i:31 XS:i:31 SA:Z:chr1,13203351,+,20S30M     ,0,0;"

    var samRecord = sAMLineParser.parseLine(str)


    println(samRecord)
    println(samRecord.getReadName)
    println(samRecord.getStart)
    println(samRecord.getEnd)
    println(samRecord.getCigar)
    println(samRecord.getReadName)
    println(samRecord.getReadName)
    println(samRecord.getReadName)
    println(samRecord.getReadName)
    println(samRecord.getReadName)

    val alignmentRecord = samRecordConverter.convert(samRecord)
    println(alignmentRecord)




    //    }
  }
}
