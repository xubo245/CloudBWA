package org.gcdss.alignment.utils

import org.apache.parquet.hadoop.metadata.CompressionCodecName
import org.bdgenomics.adam.rdd.ADAMSaveAnyArgs

/**
  * Created by xubo on 2017/3/8.
  */
case class TestSaveArgs(var outputPath: String) extends ADAMSaveAnyArgs {
  var sortFastqOutput = false
  var asSingleFile = false
  var blockSize = 128 * 1024 * 1024
  var pageSize = 1 * 1024 * 1024
  var compressionCodec = CompressionCodecName.GZIP
  var logLevel = "SEVERE"
  var disableDictionaryEncoding = false
  override var deferMerging: Boolean = false
}