package com.esofthead.mycollab.ondemand.module.billing.esb.impl

import java.util.GregorianCalendar

import com.esofthead.mycollab.common.domain.OptionVal
import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum
import com.esofthead.mycollab.common.service.OptionValService
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.project.ProjectTypeConstants
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum
import com.esofthead.mycollab.ondemand.module.billing.esb.AccountCreatedEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@Component class AccountCreatedCommand extends GenericCommand {

    @Autowired private val optionValService: OptionValService = null

    @AllowConcurrentEvents
    @Subscribe
    def execute(event: AccountCreatedEvent): Unit = {
        val option: OptionVal = new OptionVal;
        option.setCreatedtime(new GregorianCalendar().getTime)
        option.setIsdefault(true)
        option.setSaccountid(event.accountId)
        option.setType(ProjectTypeConstants.TASK)
        option.setTypeval(StatusI18nEnum.Open.name())
        optionValService.saveWithSession(option, null)

        option.setTypeval(StatusI18nEnum.Closed.name())
        option.setId(null)
        optionValService.saveWithSession(option, null)

        option.setTypeval(StatusI18nEnum.Pending.name())
        option.setId(null)
        optionValService.saveWithSession(option, null)

        option.setType(ProjectTypeConstants.MILESTONE)
        option.setTypeval(OptionI18nEnum.MilestoneStatus.Closed.name())
        option.setId(null)
        optionValService.saveWithSession(option, null)

        option.setTypeval(OptionI18nEnum.MilestoneStatus.InProgress.name())
        option.setId(null)
        optionValService.saveWithSession(option, null)

        option.setTypeval(OptionI18nEnum.MilestoneStatus.Future.name())
        option.setId(null)
        optionValService.saveWithSession(option, null)

    }
}