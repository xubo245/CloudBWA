package org.gcdss.alignment.test

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xubo on 2017/3/8.
  */
class MyTest1(conf: String) extends Serializable {
  //  @transient
  val list = List("a.com", "www.b.com", "a.cn", "a.com.cn", "a.org");
  @transient
  val sparkConf = new SparkConf().setAppName("AppName").setMaster("local");
  @transient
  val sc = new SparkContext(sparkConf);
  val rdd = sc.parallelize(list);

  private val rootDomain = conf

  def getResult(): Array[(String)] = {
    val result = rdd.filter(item => item.contains(rootDomain))
    result.take(result.count().toInt)
  }
}

/**
  * It will be :Exception in thread "main" org.apache.spark.SparkException: Task not serializable
  */
object MyTest1 {
  def main(args: Array[String]) {
    val test = new MyTest1("www")
    var result = test.getResult()
    for (i <- 0 until result.length) {
      println(result(i))
    }
  }
}
