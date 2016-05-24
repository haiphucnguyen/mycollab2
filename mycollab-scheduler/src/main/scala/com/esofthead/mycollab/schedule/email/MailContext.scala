package com.esofthead.mycollab.schedule.email

import java.util.{Locale, TimeZone}

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification
import com.esofthead.mycollab.core.utils.TimezoneVal
import com.esofthead.mycollab.i18n.LocalizationHelper
import com.esofthead.mycollab.module.user.domain.SimpleUser

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
}
