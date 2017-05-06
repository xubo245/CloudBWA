
package org.gcdss.alignment.utils

import java.io._
import java.lang.reflect.Field

import com.github.lindenb.jbwa.jni.BwaMem
import org.apache.spark.broadcast.Broadcast
import org.gcdss.backup.jBWA2

class ReferenceBroadcast(bd: Broadcast[BwaMem], isFromLocal: Boolean, index: String) extends Serializable {
  lazy val reference: BwaMem = init()

  private def init(): BwaMem = {
    this.synchronized {
      if (isFromLocal) {
        //        System.setProperty("-Djava.library.path", "/home/hadoop/disk2/xubo/lib/")
//        System.setProperty("java.library.path", System.getProperty("java.library.path") + ":/home/hadoop/disk2/xubo/lib/");
//        val fieldSysPath: Field = classOf[ClassLoader].getDeclaredField("sys_paths")
//        fieldSysPath.setAccessible(true)
//        //        fieldSysPath.set(null, null)

        var bwaIndex = jBWA2.buildIndex(index)
        val mem: BwaMem = new BwaMem(bwaIndex)
        mem.set_verbosity(1)

        //        val ref = new BWAIdxType
        println("Load reference genome")
        //        ref.load(path, 0)
        //        ref
        mem
      }
      else
        bd.value
    }
  }

  def value() = {
    reference
  }
}

