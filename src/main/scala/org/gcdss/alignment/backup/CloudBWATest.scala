//package org.gcdss.alignment
//
//import org.gcdss.alignment.backup.CloudBWA2
//
///**
//  * Created by xubo on 2017/3/4.
//  */
//object CloudBWATest {
//  def main(args: Array[String]) {
//    //-Djava.library.path=src/main/native
//    val input=compute("pairN1")
//    CloudBWA2.main(input)
//  }
//
//  def compute(str:String): Array[String] ={
//    var strings: Array[String] = null
//    if (str == "pairN1") {
//      strings = Array[String]("test/bwaindex/GRCH38chr1L3556522.fasta", "test/fastq/pair1N1.fastq", "test/fastq/pair2N1.fastq")
//    }
//    else if (str == "testL30N1pair") {
//      strings = Array[String]("test/bwaindex/GRCH38chr1L3556522.fasta", "test/fastq/testL30N1pair1.fastq", "test/fastq/testL30N1pair2.fastq")
//    }
//    else if (str == "testL50N1pair") {
//      strings = Array[String]("test/bwaindex/GRCH38chr1L3556522.fasta", "test/fastq/testL50N1pair1.fastq", "test/fastq/testL50N1pair2.fastq")
//    }
//    else if (str == "testL50N2pair") {
//      strings = Array[String]("test/bwaindex/GRCH38chr1L3556522.fasta", "test/fastq/testL50N2pair1.fastq", "test/fastq/testL50N2pair2.fastq")
//    }
//    return strings
//  }
//}
