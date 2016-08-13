/**
 * This file is part of mycollab-scheduler.
 *
 * mycollab-scheduler is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-scheduler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-scheduler.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.schedule.jobs

import com.mycollab.common.domain.MailRecipientField
import com.mycollab.core.utils.JsonDeSerializer
import com.mycollab.module.mail.service.{ExtMailService, MailRelayService}
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class SendingRelayEmailJob extends GenericQuartzJobBean {
  private val LOG = LoggerFactory.getLogger(classOf[SendingRelayEmailJob])
  
  @Autowired private val mailRelayService: MailRelayService = null
  @Autowired private val extMailService: ExtMailService = null
  
  @Override
  def executeJob(context: JobExecutionContext) {
    val relayEmails = mailRelayService.getRelayEmails
    mailRelayService.cleanEmails()
    
    import scala.collection.JavaConversions._
    for (relayEmail <- relayEmails) {
      try {
        val recipientVal = relayEmail.getRecipients
        import collection.JavaConverters._
        val recipientArr = JsonDeSerializer.fromJson(recipientVal, classOf[java.util.List[MailRecipientField]]).asScala.toList
        extMailService.sendHTMLMail(relayEmail.getFromemail, relayEmail.getFromname, recipientArr, relayEmail.getSubject, relayEmail.getBodycontent)
      } catch {
        case e: Exception => LOG.error("Error when send relay email", e)
      }
    }
  }
}
