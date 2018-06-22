package com.mycollab.ondemand.module.billing.esb

import com.google.common.eventbus.Subscribe
import com.mycollab.common.dao.OptionValMapper
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.common.domain.OptionValExample
import com.mycollab.configuration.ApplicationConfiguration
import com.mycollab.core.utils.BeanUtility
import com.mycollab.module.ecm.service.ResourceService
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.page.service.PageService
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
class DeleteAccountCommand(private val resourceService: ResourceService,
                           private val pageService: PageService,
                           private val optionValMapper: OptionValMapper,
                           private val mailService: ExtMailService,
                           private val applicationConfiguration: ApplicationConfiguration) : GenericCommand() {

    @Subscribe
    fun deleteAccount(event: DeleteAccountEvent) {
        val rootPath = event.accountId.toString()
        resourceService.removeResource(rootPath, "", false, event.accountId)
        pageService.removeResource(rootPath)

        //delete all options of this account
        val optionEx = OptionValExample()
        optionEx.createCriteria().andSaccountidEqualTo(event.accountId)
        optionValMapper.deleteByExample(optionEx)

        val feedback = event.feedback
        val feedbackValue = if (feedback == null) "None" else BeanUtility.printBeanObj(feedback)
        mailService.sendHTMLMail(applicationConfiguration.notifyEmail, applicationConfiguration.siteName,
                listOf(MailRecipientField("haiphucnguyen@gmail.com", "Hai Nguyen")),
                "User cancelled account", feedbackValue)
    }
}