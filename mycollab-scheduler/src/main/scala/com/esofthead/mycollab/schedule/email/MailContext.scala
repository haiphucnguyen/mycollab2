package com.esofthead.mycollab.schedule.email

import java.util.{TimeZone, Locale}

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification
import com.esofthead.mycollab.configuration.LocaleHelper
import com.esofthead.mycollab.core.utils.TimezoneMapper
import com.esofthead.mycollab.i18n.LocalizationHelper
import com.esofthead.mycollab.module.user.domain.SimpleUser

import scala.beans.BeanProperty

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
class MailContext[B] (@BeanProperty val emailNotification: SimpleRelayEmailNotification, @BeanProperty val
user:SimpleUser, val siteUrl:String) {
  @BeanProperty val locale: Locale = LocaleHelper.toLocale(user.getLanguage)
  @BeanProperty val timeZone: TimeZone = TimezoneMapper.getTimezone(user.getTimezone)
  @BeanProperty var wrappedBean: B = _

  def getSaccountid: Int = emailNotification.getSaccountid

  def getChangeByUserFullName: String = emailNotification.getChangeByUserFullName

  def getTypeid: String = emailNotification.getTypeid

  def getType: String = emailNotification.getType

  def getMessage(key: Enum[_], params: AnyRef*): String = LocalizationHelper.getMessage(locale, key, params)
}
