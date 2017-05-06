package org.gcdss.alignment.utils

import htsjdk.samtools.SAMRecord
import org.apache.spark.Logging

/**
  * Created by xubo on 2017/3/7.
  */
class SAMRecordSerializable extends Serializable with Logging{
  @transient
  var samRecord:SAMRecord=null
}
