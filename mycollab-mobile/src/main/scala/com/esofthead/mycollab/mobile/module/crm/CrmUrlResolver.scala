package com.esofthead.mycollab.mobile.module.crm

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.mobile.module.crm.events.CrmEvent
import com.esofthead.mycollab.mobile.module.crm.view.account.AccountUrlResolver
import com.esofthead.mycollab.mobile.module.crm.view.activity.ActivityUrlResolver
import com.esofthead.mycollab.mobile.module.crm.view.campaign.CampaignUrlResolver
import com.esofthead.mycollab.mobile.module.crm.view.cases.CaseUrlResolver
import com.esofthead.mycollab.mobile.module.crm.view.contact.ContactUrlResolver
import com.esofthead.mycollab.mobile.module.crm.view.lead.LeadUrlResolver
import com.esofthead.mycollab.mobile.module.crm.view.opportunity.OpportunityUrlResolver
import com.esofthead.mycollab.mobile.shell.ModuleHelper
import com.esofthead.mycollab.mobile.shell.events.ShellEvent
import com.esofthead.mycollab.module.crm.i18n.CrmCommonI18nEnum
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.UrlResolver

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class CrmUrlResolver extends UrlResolver {
    def build: UrlResolver = {
        this.addSubResolver("account", new AccountUrlResolver)
        this.addSubResolver("contact", new ContactUrlResolver)
        this.addSubResolver("campaign", new CampaignUrlResolver)
        this.addSubResolver("lead", new LeadUrlResolver)
        this.addSubResolver("opportunity", new OpportunityUrlResolver)
        this.addSubResolver("cases", new CaseUrlResolver)
        this.addSubResolver("activity", new ActivityUrlResolver)
        return this
    }

    override def handle(params: String*) {
        if (!ModuleHelper.isCurrentCrmModule) {
            EventBusFactory.getInstance.post(new ShellEvent.GotoCrmModule(this, params))
        }
        else {
            super.handle(params:_*)
        }
    }

    protected override def handlePage(params: String*) {
        super.handlePage(params:_*)
        EventBusFactory.getInstance.post(new CrmEvent.GotoContainer(this,
            new CrmModuleScreenData.GotoModule(AppContext.getMessage(CrmCommonI18nEnum.TOOLBAR_ACCOUNTS_HEADER))))
    }

    protected def defaultPageErrorHandler {
        EventBusFactory.getInstance.post(new ShellEvent.GotoCrmModule(this, null))
    }
}
