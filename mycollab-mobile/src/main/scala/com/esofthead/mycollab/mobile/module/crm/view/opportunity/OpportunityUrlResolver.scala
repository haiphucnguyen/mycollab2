package com.esofthead.mycollab.mobile.module.crm.view.opportunity

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.mobile.module.crm.{CrmModuleScreenData, CrmUrlResolver}
import com.esofthead.mycollab.mobile.module.crm.events.{CrmEvent, OpportunityEvent}
import com.esofthead.mycollab.module.crm.domain.Account
import com.esofthead.mycollab.module.crm.i18n.CrmCommonI18nEnum
import com.esofthead.mycollab.vaadin.AppContext

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class OpportunityUrlResolver extends CrmUrlResolver {
    this.addSubResolver("list", new OpportunityListUrlResolver)
    this.addSubResolver("add", new OpportunityAddUrlResolver)
    this.addSubResolver("edit", new OpportunityEditUrlResolver)
    this.addSubResolver("preview", new OpportunityPreviewUrlResolver)

    class OpportunityListUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new CrmEvent.GotoContainer(this,
                new CrmModuleScreenData.GotoModule(AppContext.getMessage(CrmCommonI18nEnum.TOOLBAR_OPPORTUNTIES_HEADER))))
        }
    }

    class OpportunityAddUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new OpportunityEvent.GotoAdd(this, new Account))
        }
    }

    class OpportunityEditUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val opportunintyId: Int = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new OpportunityEvent.GotoEdit(this, opportunintyId))
        }
    }

    class OpportunityPreviewUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val opportunintyId: Int = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new OpportunityEvent.GotoRead(this, opportunintyId))
        }
    }

}
