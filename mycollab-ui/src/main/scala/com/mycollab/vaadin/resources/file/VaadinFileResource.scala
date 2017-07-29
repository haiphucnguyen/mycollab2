package com.mycollab.vaadin.resources.file

import java.io.{File, FileInputStream, IOException}

import com.mycollab.core.utils.FileUtils
import com.mycollab.vaadin.resources.VaadinResource
import com.vaadin.server.{DownloadStream, FileResource, Resource}
import org.slf4j.LoggerFactory

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class VaadinFileResource extends VaadinResource {
  val LOG = LoggerFactory.getLogger(classOf[VaadinFileResource])
  
  override def getStreamResource(documentPath: String): Resource = new FileStreamDownloadResource(documentPath)
  
  @SerialVersionUID(1L)
  class FileStreamDownloadResource(val documentPath: String) extends FileResource(new File(FileUtils.getHomeFolder, documentPath)) {
    override def getStream: DownloadStream = {
      val fileName = getFilename.replace(" ", "_").replace("-", "_")
      try {
        val inStream = new FileInputStream(getSourceFile)
        val ds = new DownloadStream(inStream, getMIMEType, fileName)
        ds.setParameter("Content-Disposition", "attachment; filename=" + fileName)
        ds.setCacheTime(0)
        ds
      }
      catch {
        case e: IOException =>
          LOG.error("Error to create download stream", e)
          null
      }
    }
  }
  
}
