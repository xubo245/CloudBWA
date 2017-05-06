package org.gcdss.alignment.run

import org.gcdss.alignment.transform.Sam2Adam

/**
  * Created by xubo on 2017/3/5.
  */
object Sam2AdamTime {
  def main(args: Array[String]) {
    //        compute()
//    compute2
//    var arr = arrNew
//    compute3
        var arr = arrNew8
    for (i <- 0 until arr.length) {
      val startTime = System.currentTimeMillis()
      Sam2Adam.main(arr(i))
      val stopTime = System.currentTimeMillis()
      for (j<-0 until arr(i).length){
        print(arr(i)(j)+"\t")
      }
      println( "\ttime:\t" + (stopTime - startTime) / 1000.0 + "\t")
    }
  }
  def arrNew8(): Array[Array[String]] = {
    Array(
      Array("/xubo/project/alignment/sparkBWA/input/ERR000589_1.filt.L10000.fastq", "/xubo/project/alignment/sparkBWA/input/ERR000589_2.filt.L10000.fastq", "/xubo/project/alignment/sparkBWA/input/ERR000589.L10000.adam")
    )
  }
  def arrNew7(): Array[Array[String]] = {
    Array(
      Array("/xubo/project/alignment/sparkBWA/input/SRR062634_1.filt.fastq", "/xubo/project/alignment/sparkBWA/input/SRR062634_2.filt.fastq", "/xubo/project/alignment/sparkBWA/input/SRR062634.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/ERR000589_1.filt.fastq", "/xubo/project/alignment/sparkBWA/input/ERR000589_2.filt.fastq", "/xubo/project/alignment/sparkBWA/input/ERR000589.adam")
    )
  }
  def arrNew6(): Array[Array[String]] = {
    Array(
      Array("/xubo/project/alignment/sparkBWA/input/SRR062634_1.filt.fastq", "/xubo/project/alignment/sparkBWA/input/SRR062634_2.filt.fastq", "/xubo/project/alignment/sparkBWA/input/SRR062634.adam")
    )
  }
  def arrNew5(): Array[Array[String]] = {
    Array(
      Array("/xubo/project/alignment/sparkBWA/input/ERR000589_1.filt.fastq", "/xubo/project/alignment/sparkBWA/input/ERR000589_2.filt.fastq", "/xubo/project/alignment/sparkBWA/input/ERR000589.adam")
    )
  }

  def arrNew4(): Array[Array[String]] = {
    Array(
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L100c5000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L100c5000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L100c5000000Nhs20Paired.adam")
    )
  }
  def arrNew(): Array[Array[String]] = {
    Array(
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c100000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c100000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c100000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L100c10000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L100c10000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L100c10000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L100c100000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L100c100000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L100c100000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L100c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L100c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L100c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L100c10000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L100c10000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L100c10000000Nhs20Paired.adam")
    )
    //    Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000Nhs20Paired.adam")
  }

  def arrNew2(): Array[Array[String]] = {
    Array(
//      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c2000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c2000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c2000000Nhs20Paired.adam"),
//      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c4000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c4000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c4000000Nhs20Paired.adam"),
//      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c6000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c6000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c6000000Nhs20Paired.adam"),
//      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c8000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c8000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c8000000Nhs20Paired.adam"),
//      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c12000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c12000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c12000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c14000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c14000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c14000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c16000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c16000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c16000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c18000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c18000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c18000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/newg38L50c20000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c20000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/newg38L50c20000000Nhs20Paired.adam")
    )
  }

  def arrNew3(): Array[Array[String]] = {
    Array(
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L50c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L50c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L50c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L100c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L100c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L100c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L150c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L150c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L150c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L200c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L200c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L200c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L250c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L250c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L250c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L300c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L300c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L300c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L350c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L350c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L350c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L400c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L400c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L400c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L450c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L450c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L450c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L500c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L500c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L500c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L550c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L550c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L550c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L600c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L600c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L600c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L650c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L650c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L650c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L700c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L700c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L700c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L750c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L750c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L750c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L800c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L800c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L800c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L850c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L850c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L850c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L900c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L900c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L900c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L950c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L950c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L950c1000000Nhs20Paired.adam"),
      Array("/xubo/project/alignment/sparkBWA/input/g38/longg38L1000c1000000Nhs20Paired1.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L1000c1000000Nhs20Paired2.fastq", "/xubo/project/alignment/sparkBWA/input/g38/longg38L1000c1000000Nhs20Paired.adam")
        )
  }

  def compute3(): Unit = {
    var i=0
    for (ii <- 0 until 20) {
      i=i+50
      //      val arr=Array(2000000 to 20000000 by 2000000)
      var j=0
      for (k <- 0 until 1) {
//        j=j+2000000
        j=1000000
        println("Array(\"/xubo/project/alignment/sparkBWA/input/g38/longg38L" + i + "c" + j + "Nhs20Paired1.fastq\", " +
          "\"/xubo/project/alignment/sparkBWA/input/g38/longg38L" + i + "c" + j + "Nhs20Paired2.fastq\", " +
          "\"/xubo/project/alignment/sparkBWA/input/g38/longg38L" + i + "c" + j + "Nhs20Paired.adam\"),")
      }
    }
  }

  def compute2(): Unit = {
    for (i <- Array(50)) {
//      val arr=Array(2000000 to 20000000 by 2000000)
      var j=0
      for (k <- 0 until 10) {
         j=j+2000000
        println("Array(\"/xubo/project/alignment/sparkBWA/input/g38/newg38L" + i + "c" + j + "Nhs20Paired1.fastq\", " +
          "\"/xubo/project/alignment/sparkBWA/input/g38/newg38L" + i + "c" + j + "Nhs20Paired2.fastq\", " +
          "\"/xubo/project/alignment/sparkBWA/input/g38/newg38L" + i + "c" + j + "Nhs20Paired.adam\"),")
      }
    }
  }

  def compute(): Unit = {
    for (i <- Array(50, 100)) {
      for (j <- Array(10000, 100000, 1000000, 10000000)) {
        println("Array(\"/xubo/project/alignment/sparkBWA/input/g38/newg38L" + i + "c" + j + "Nhs20Paired1.fastq\", " +
          "\"/xubo/project/alignment/sparkBWA/input/g38/newg38L" + i + "c" + j + "Nhs20Paired2.fastq\", " +
          "\"/xubo/project/alignment/sparkBWA/input/g38/newg38L" + i + "c" + j + "Nhs20Paired.adam\"),")
      }
    }
  }
}
