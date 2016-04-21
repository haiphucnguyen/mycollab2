package com.esofthead.mycollab.module.billing.esb.impl

import java.util.Arrays

import com.esofthead.mycollab.common.dao.OptionValMapper
import com.esofthead.mycollab.common.domain.{MailRecipientField, OptionValExample}
import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.core.utils.BeanUtility
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.ecm.service.ResourceService
import com.esofthead.mycollab.module.mail.service.ExtMailService
import com.esofthead.mycollab.module.page.service.PageService
import com.esofthead.mycollab.ondemand.module.billing.esb.DeleteAccountEvent
import com.google.common.eventbus.Subscribe
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
      mailService.sendHTMLMail(SiteConfiguration.getNoReplyEmail, SiteConfiguration.getDefaultSiteName,
        Arrays.asList(new MailRecipientField("hainguyen@esofthead.com", "Hai Nguyen")), null, null,
        "User cancelled account", BeanUtility.printBeanObj(feedback), null)
    }
  }
}