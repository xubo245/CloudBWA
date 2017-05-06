package org.gcdss.alignment.transform

import java.text.SimpleDateFormat
import java.util.Date

import org.gcdss.alignment.CloudBWA
import org.gcdss.alignment.utils.GcdssAlignmentFunSuite

import scala.io.Source

/**
  * Created by xubo on 2017/3/8.
  */
class ConverterSuite extends GcdssAlignmentFunSuite {

  test("null test2") {
    var a = null
    println(a==null)
  }

  //error
  test("null test") {
    var a = null
//    println(a.equals(null))
  }

  test("test saveAsAdamParquet with sam in adam") {
    val sam = "/C:/all/idea/CloudBWA/file/sam/input/NA12878_snp_A2G_chr20_225058.sam"
    var lines = Source.fromFile(sam).getLines().filter(!_.startsWith("@")).toArray
    val samRDD = sc.parallelize(lines)

    val RDD = ac.loadAlignments(sam)
    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
    //    CloudBWA.saveAsAdam(samRDD, "file/sam/NA12878_snp_A2G_chr20_225058.sam.adam" + iString, RDD.sequences, RDD.recordGroups)
    CloudBWA.saveAsAdamParquet(samRDD, "/C:/all/idea/CloudBWA/file/sam/output/NA12878_snp_A2G_chr20_225058.samTime" + iString + ".adam", RDD.sequences, RDD.recordGroups)
  }

  test("test saveAsAdam saveAsAdam with sam in adam") {
    val sam = "file/sam/NA12878_snp_A2G_chr20_225058.sam"
    var lines = Source.fromFile(sam).getLines().filter(!_.startsWith("@")).toArray
    val samRDD = sc.parallelize(lines)

    val RDD = ac.loadAlignments(sam)
    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
    CloudBWA.saveAsAdam(samRDD, "file/sam/NA12878_snp_A2G_chr20_225058.sam.adam" + iString, RDD.sequences, RDD.recordGroups)
    //    CloudBWA.saveAsAdamParquet(samRDD, "file/sam/NA12878_snp_A2G_chr20_225058.samTime" + iString + ".adam", RDD.sequences, RDD.recordGroups)
  }

  //  test("test saveAsAdam saveAsAdam with sam in adam") {
  //    val sam = "newg38L50c1Nhs20Paired1.fastq"
  //    var lines = Source.fromFile(sam).getLines().filter(!_.startsWith("@")).toArray
  //    val samRDD = sc.parallelize(lines)
  //
  //    val RDD = ac.loadAlignments(sam)
  //    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
  //    CloudBWA.saveAsAdam(samRDD, "file/sam/NA12878_snp_A2G_chr20_225058.sam.adam" + iString, RDD.sequences, RDD.recordGroups)
  //    //    CloudBWA.saveAsAdamParquet(samRDD, "file/sam/NA12878_snp_A2G_chr20_225058.samTime" + iString + ".adam", RDD.sequences, RDD.recordGroups)
  //  }

}
