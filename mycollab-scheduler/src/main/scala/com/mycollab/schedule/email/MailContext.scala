package com.mycollab.schedule.email

import java.util.{Locale, TimeZone}

import com.mycollab.module.user.domain.SimpleUser
import com.mycollab.common.domain.SimpleRelayEmailNotification
import com.mycollab.core.utils.TimezoneVal
import com.mycollab.i18n.LocalizationHelper

import scala.annotation.varargs
import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
class MailContext[B](@BeanProperty val emailNotification: SimpleRelayEmailNotification,
                     @BeanProperty val user: SimpleUser, val siteUrl: String) {
  @BeanProperty val locale: Locale = LocalizationHelper.getLocaleInstance(user.getLanguage)
  @BeanProperty val timeZone: TimeZone = TimezoneVal.valueOf(user.getTimezone)
  @BeanProperty var wrappedBean: B = _

  def getSaccountid: Int = emailNotification.getSaccountid

  def getChangeByUserFullName: String = emailNotification.getChangeByUserFullName

  def getTypeid: String = emailNotification.getTypeid

  def getType: String = emailNotification.getType

  @varargs def getMessage(key: Enum[_], params: AnyRef*): String = LocalizationHelper.getMessage(locale, key, params: _*)

  def getFieldName(fieldMapper: ItemFieldMapper, fieldName: String): String = {
    val fieldFormat = fieldMapper.getField(fieldName)
    if (fieldFormat != null) getMessage(fieldFormat.displayName) else ""
  }
}
