package com.esofthead.mycollab.schedule

import java.io.{StringReader, StringWriter}

import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.{Template, VelocityContext}

/**
 * Created by baohan on 1/7/15.
 */
object Test {
  def main(args: Array[String]) {
    val reader:StringReader = new StringReader("aaa ${defaultUrls.cdn_url} $a")
    val ve: VelocityEngine = new VelocityEngine
    val context: VelocityContext = new VelocityContext()
    val defaultUrls = Map[String, String]("cdn_url" -> "xxx")
//val defaultUrls = new java.util.HashMap[String, String]
//    defaultUrls.put("cdn_url", "xxx")
    context.put("a", "b")
    context.put("defaultUrls", defaultUrls)
    val writer: StringWriter = new StringWriter
    ve.evaluate(context, writer, "log", reader)
    println(writer.toString)
  }
}
