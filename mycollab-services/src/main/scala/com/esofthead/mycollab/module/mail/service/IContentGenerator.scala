package com.esofthead.mycollab.module.mail.service

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
    * @param subject
    * @return
    */
  def parseString(subject: String): String

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

  /**
    *
    * @param templateFilePath
    * @param currentLocale
    * @param defaultLocale
    * @return
    */
  def parseFile(templateFilePath: String, currentLocale: Locale, defaultLocale: Locale): String
}
