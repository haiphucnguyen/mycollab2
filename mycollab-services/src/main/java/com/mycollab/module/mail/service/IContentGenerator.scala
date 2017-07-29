package com.mycollab.module.mail.service

import java.util.Locale

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
trait IContentGenerator {
  /**
    *
    * @param key
    * @param value
    */
  def putVariable(key: String, value: Any)

  /**
    *
    * @param templateFilePath
    * @return
    */
  def parseFile(templateFilePath: String): String

  /**
    *
    * @param templateFilePath
    * @param currentLocale
    * @return
    */
  def parseFile(templateFilePath: String, currentLocale: Locale): String
}
