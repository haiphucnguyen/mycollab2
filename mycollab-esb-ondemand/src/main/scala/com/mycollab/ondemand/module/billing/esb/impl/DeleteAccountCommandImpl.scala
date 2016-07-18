package com.mycollab.ondemand.module.billing.esb.impl

import java.util.Arrays

import com.mycollab.common.domain.OptionValExample
import com.mycollab.module.page.service.PageService
import com.mycollab.ondemand.module.billing.esb.DeleteAccountEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.common.dao.OptionValMapper
import com.mycollab.common.domain.{MailRecipientField, OptionValExample}
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.core.utils.BeanUtility
import com.mycollab.module.ecm.service.ResourceService
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.mail.service.ExtMailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 1.0
  */
@Component class DeleteAccountCommandImpl extends GenericCommand {
  @Autowired private val resourceService: ResourceService = null
  @Autowired private val pageService: PageService = null
  @Autowired private val optionValMapper: OptionValMapper = null
  @Autowired private val mailService: ExtMailService = null

  @AllowConcurrentEvents
  @Subscribe
  def deleteAccount(event: DeleteAccountEvent): Unit = {
    val rootPath = event.accountId + ""
    resourceService.removeResource(rootPath, "", event.accountId)
    pageService.removeResource(rootPath)

    //delete all options of this account
    val optionEx = new OptionValExample
    optionEx.createCriteria().andSaccountidEqualTo(event.accountId)
    optionValMapper.deleteByExample(optionEx)

    val feedback = event.feedback
    if (feedback != null) {
      mailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName,
        Arrays.asList(new MailRecipientField("hainguyen@esofthead.com", "Hai Nguyen")), null, null,
        "User cancelled account", BeanUtility.printBeanObj(feedback), null)
    }
  }
}