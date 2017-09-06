package com.mycollab.vaadin.resources.file

import com.mycollab.core.utils.MimeTypesUtil
import com.vaadin.server.FontAwesome

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
object FileAssetsUtil {
  val extraIconMap: Map[String, FontAwesome] = Map[String, FontAwesome]("application/pdf" -> FontAwesome.FILE_PDF_O,
    "application/vnd.ms-excel" -> FontAwesome.FILE_EXCEL_O,
    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" -> FontAwesome.FILE_EXCEL_O,
    "application/msword" -> FontAwesome.FILE_WORD_O,
    "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> FontAwesome.FILE_WORD_O,
    "application/vnd.ms-powerpoint" -> FontAwesome.FILE_POWERPOINT_O,
    "application/vnd.openxmlformats-officedocument.presentationml.presentation" -> FontAwesome.FILE_POWERPOINT_O,
    "application/zip" -> FontAwesome.FILE_ZIP_O,
    "application/x-rar-compressed" -> FontAwesome.FILE_ARCHIVE_O,
    "application/x-7z-compressed" -> FontAwesome.FILE_ARCHIVE_O,
    "application/java-archive" -> FontAwesome.FILE_ARCHIVE_O,
    "application/x-tar" -> FontAwesome.FILE_ARCHIVE_O)
  
  def getFileIconResource(fileName: String): FontAwesome = {
    val mimeType: String = MimeTypesUtil.detectMimeType(fileName)
    if (MimeTypesUtil.isImage(mimeType)) return FontAwesome.FILE_IMAGE_O
    else if (MimeTypesUtil.isAudio(mimeType)) return FontAwesome.FILE_AUDIO_O
    else if (MimeTypesUtil.isVideo(mimeType)) return FontAwesome.FILE_VIDEO_O
    else if (MimeTypesUtil.isText(mimeType)) return FontAwesome.FILE_TEXT_O
    val icon = extraIconMap.get(mimeType)
    icon match {
      case None => FontAwesome.FILE_O
      case _ => icon.get
    }
  }
}
