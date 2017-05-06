package org.gcdss.alignment

import org.gcdss.alignment.utils.GcdssAlignmentFunSuite

/**
  * Created by xubo on 2017/3/29.
  */
class MergeLocalSuite extends GcdssAlignmentFunSuite {

  test("MergeLocal test") {
    sc.stop()
    var in = "C:\\all\\idea\\CloudBWA\\file\\sam\\input\\cloudBWAnewg38L50c10000000Nhs20Paired12time10num16k1.sam"
    var out = "C:\\all\\idea\\CloudBWA\\file\\sam\\input\\cloudBWAnewg38L50c10000000Nhs20Paired12time10num16k1.merge.sam"
    val startTime = System.currentTimeMillis()
    MergeLocal.main(Array(in, out))
    val stopTime = System.currentTimeMillis()
    println("\ttime:\t" + (stopTime - startTime) / 1000.0 + "\t")
    //    time:	36.412
    //    - MergeLocal test
  }

}
