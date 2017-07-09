package com.mycollab.ondemand.module.billing.esb

import java.util.Arrays

import com.google.common.eventbus.Subscribe
import com.mycollab.common.dao.OptionValMapper
import com.mycollab.common.domain.{MailRecipientField, OptionValExample}
import com.mycollab.configuration.{ApplicationConfiguration, EmailConfiguration, SiteConfiguration}
import com.mycollab.core.utils.BeanUtility
import com.mycollab.module.ecm.service.ResourceService
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.page.service.PageService
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
  @Autowired private val emailConfiguration: EmailConfiguration = null
  @Autowired private val applicationConfiguration: ApplicationConfiguration = null
  
  @Subscribe
  def deleteAccount(event: DeleteAccountEvent): Unit = {
    val rootPath = event.accountId + ""
    resourceService.removeResource(rootPath, "", false, event.accountId)
    pageService.removeResource(rootPath)
    
    //delete all options of this account
    val optionEx = new OptionValExample
    optionEx.createCriteria().andSaccountidEqualTo(event.accountId)
    optionValMapper.deleteByExample(optionEx)
    
    val feedback = event.feedback
    val feedbackValue = if (feedback == null) "None" else BeanUtility.printBeanObj(feedback)
    mailService.sendHTMLMail(emailConfiguration.getNotifyEmail, applicationConfiguration.getName,
      Arrays.asList(new MailRecipientField("hainguyen@esofthead.com", "Hai Nguyen")),
      "User cancelled account", feedbackValue)
  }
}