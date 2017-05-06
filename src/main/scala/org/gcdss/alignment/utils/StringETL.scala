package org.gcdss.alignment.utils

import scala.io.Source

/**
  * Created by xubo on 2017/4/8.
  */
object StringETL {
  def main(args: Array[String]) {

    val file = "file\\sam\\input\\ERR000589cloudBWAbatch11k1error129919.sam"
    val str = Source.fromFile(file).getLines()
    //    str.foreach(println)
    for (s <- str) {
      println(s)
      println("==>" + splitSAM(s))
    }
  }

  def splitSAM(str: String): String = {
    var eachFirst: String = str
    var eachArr = str.split(";\t")
    var flag = false
    var tmp: String = new String
    var tmp2: String = new String
    //    println(tmp)
    if (eachArr.length > 1) {
      for (i <- 0 until eachArr.length) {

        if (tmp.length == 0) {
          tmp = eachArr(i)
        } else {
          tmp = tmp + ";\t" + eachArr(i)
        }
        //        println(i + ";" + tmp)
        //        println(tmp.split("\\s+").length)
        if (flag == false && (tmp.split("\\s+").length > Constants.qualLength)) {
          //        eachFirst = eachArr(0)
          //        for(j<-0 until i){
          //          eachFirst=eachFirst+eachArr(j)
          eachFirst = tmp
          //        }
          flag = true
        }
        tmp2 = tmp

      }
    }
    eachFirst
  }
}
