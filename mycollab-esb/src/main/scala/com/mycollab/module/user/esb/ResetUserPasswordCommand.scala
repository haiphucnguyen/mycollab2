/**
 * This file is part of mycollab-esb.
 *
 * mycollab-esb is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-esb is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-esb.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.user.esb

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.common.UrlEncodeDecoder
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.common.i18n.MailI18nEnum
import com.mycollab.configuration.{ApplicationConfiguration, EmailConfiguration, IDeploymentMode}
import com.mycollab.core.utils.DateTimeUtils
import com.mycollab.i18n.LocalizationHelper
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum
import com.mycollab.module.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @version 5.2.5
  */
@Component class ResetUserPasswordCommand extends GenericCommand {
  @Autowired private val emailConfiguration: EmailConfiguration = null
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val userService: UserService = null
  @Autowired private val contentGenerator: IContentGenerator = null
  @Autowired private val applicationConfiguration: ApplicationConfiguration = null
  @Autowired private val deploymentMode: IDeploymentMode = null

  @AllowConcurrentEvents
  @Subscribe
  def execute(event: RequestToResetPasswordEvent): Unit = {
    val username = event.username
    if (username != null) {
      val user = userService.findUserByUserName(username)
      val subDomain = "api"
      val recoveryPasswordURL = deploymentMode.getSiteUrl(subDomain) + "user/recoverypassword/" +
        UrlEncodeDecoder.encode(username)
      val locale = LocalizationHelper.getLocaleInstance(user.getLanguage)
      contentGenerator.putVariable("username", user.getUsername)
      contentGenerator.putVariable("urlRecoveryPassword", recoveryPasswordURL)
      contentGenerator.putVariable("copyRight", LocalizationHelper.getMessage(locale, MailI18nEnum.Copyright,
        DateTimeUtils.getCurrentYear))
      val recipient = new MailRecipientField(user.getEmail, user.getUsername)
      val recipientFields = List[MailRecipientField](recipient)
      import collection.JavaConverters._
      extMailService.sendHTMLMail(emailConfiguration.getNotifyEmail, applicationConfiguration.getName,
        recipientFields.asJava,
        LocalizationHelper.getMessage(locale, UserI18nEnum.MAIL_RECOVERY_PASSWORD_SUBJECT,
          applicationConfiguration.getName),
        contentGenerator.parseFile("mailUserRecoveryPasswordNotifier.ftl", locale))
    }
  }
}
