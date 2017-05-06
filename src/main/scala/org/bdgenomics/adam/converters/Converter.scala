package org.bdgenomics.adam.converters

import htsjdk.samtools.{SAMLineParser, SAMRecord}
import org.apache.spark.rdd.RDD
import org.bdgenomics.adam.models.{RecordGroupDictionary, SequenceDictionary}
import org.bdgenomics.adam.rdd.read.AlignmentRecordRDD
import org.bdgenomics.formats.avro.AlignmentRecord
import org.gcdss.alignment.utils.{StringETL, SAMRecordSerializable}

/**
  * Created by xubo on 2017/3/7.
  */
//class Converter extends Serializable with Logging{
object Converter {
  def samToAlignmentRecordRDD(samRDD: RDD[String], sequenceDictionary: SequenceDictionary, recordGroupDictionary: RecordGroupDictionary): AlignmentRecordRDD = {
    val samRecordConverter = new SAMRecordConverter

    var rdd = samRDD.map { each =>
      var sAMRecordSerializable = new SAMRecordSerializable

      /**
        * avoid error like :
        * chr1_99683104_99683640_1:0:0_6:0:0_15655e	73	chr1	91199704	0	31M19S	=	91199704	0	AAGAAAGAAAGAAAGAAAGAAAGCAAGAAAGAAAGAAAGAAAGAAAGAAA	22222222222222222222222222222222222222222222222222	NM:i:0	MD:Z:31	AS:i:31	XS:i:31	SA:Z:chr1,12789437,-,30M20S,0,0;	chr1_99683104_99683640_1:0:0_6:0:0_15655e	2169	chr1	12789437	0	30M20H	=	12789437	0	TTTCTTTCTTTCTTTCTTTCTTTCTTGCTT	222222222222222222222222222222	NM:i:0	MD:Z:30	AS:i:30	XS:i:30	SA:Z:chr1,91199704,+,31M19S,0,0;
        * chr1_99683104_99683640_1:0:0_6:0:0_15655e	133	chr1	91199704	0	*	=	91199704	0	CAAAACTATATGAAGATGGTGAAATCCAGGTTGGTTTCCAGTATAAGGGT	22222222222222222222222222222222222222222222222222	AS:i:0	XS:i:0
        */
      var eachFirst: String = each
      //      var eachArr = each.split(";\t")
      //      if (eachArr.length > 1 && eachArr(0).split("\\s+").length > qualLength) {
      //        eachFirst = eachArr(0)
      //      }
      eachFirst = StringETL.splitSAM(each)
      sAMRecordSerializable.samRecord = parseLine(eachFirst, sequenceDictionary, recordGroupDictionary)

      samRecordConverter.convert(sAMRecordSerializable.samRecord)
    }

    AlignmentRecordRDD(rdd, sequenceDictionary, recordGroupDictionary)
  }

  def samStringToAlignmentRecord(sam: String, sequenceDictionary: SequenceDictionary, recordGroupDictionary: RecordGroupDictionary): AlignmentRecord = {
    val samRecordConverter = new SAMRecordConverter
    //    var samRecordSerializable = new SAMRecordSerializable
    /**
      * avoid error like :
      * chr1_99683104_99683640_1:0:0_6:0:0_15655e	73	chr1	91199704	0	31M19S	=	91199704	0	AAGAAAGAAAGAAAGAAAGAAAGCAAGAAAGAAAGAAAGAAAGAAAGAAA	22222222222222222222222222222222222222222222222222	NM:i:0	MD:Z:31	AS:i:31	XS:i:31	SA:Z:chr1,12789437,-,30M20S,0,0;	chr1_99683104_99683640_1:0:0_6:0:0_15655e	2169	chr1	12789437	0	30M20H	=	12789437	0	TTTCTTTCTTTCTTTCTTTCTTTCTTGCTT	222222222222222222222222222222	NM:i:0	MD:Z:30	AS:i:30	XS:i:30	SA:Z:chr1,91199704,+,31M19S,0,0;
      * chr1_99683104_99683640_1:0:0_6:0:0_15655e	133	chr1	91199704	0	*	=	91199704	0	CAAAACTATATGAAGATGGTGAAATCCAGGTTGGTTTCCAGTATAAGGGT	22222222222222222222222222222222222222222222222222	AS:i:0	XS:i:0
      */
    var eachFirst: String = sam
    //    var eachArr = sam.split(";\t")
    //    if (eachArr.length > 1 && eachArr(0).split("\\s+").length > qualLength) {
    //      eachFirst = eachArr(0)
    //    }
    eachFirst = StringETL.splitSAM(sam)
    var samRecord = parseLine(eachFirst, sequenceDictionary, recordGroupDictionary)
    if (samRecord == null) {
      //add by xubo 20170407
      //      println(eachFirst)
      null
    } else {
      samRecordConverter.convert(samRecord)
    }
  }

  var qualLength = 11

  def parseLine(each: String, sequenceDictionary: SequenceDictionary, recordGroupDictionary: RecordGroupDictionary): SAMRecord = {
    try {
      val arc = new AlignmentRecordConverter
      var SAMHeader = arc.createSAMHeader(sequenceDictionary, recordGroupDictionary)
      val samLineParser = new SAMLineParser(SAMHeader)
      var sAMRecordSerializable = new SAMRecordSerializable
      //      samLineParser.parseLine(new String(each.getBytes(), "UTF-8"))
      samLineParser.parseLine(each)
    } catch {
      case exc: Exception =>
        val msg = "Error \"%s\" parse sam String error (%s)".format(exc.getMessage, each)
        //add by xubo 20170407
        println(each)
        throw new IllegalArgumentException(msg, exc)
        null
    }
  }

}
