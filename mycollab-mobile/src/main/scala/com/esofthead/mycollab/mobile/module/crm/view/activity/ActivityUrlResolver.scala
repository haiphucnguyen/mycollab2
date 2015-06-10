package com.esofthead.mycollab.mobile.module.crm.view.activity

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.mobile.module.crm.{CrmModuleScreenData, CrmUrlResolver}
import com.esofthead.mycollab.mobile.module.crm.events.CrmEvent
import com.esofthead.mycollab.module.crm.i18n.CrmCommonI18nEnum
import com.esofthead.mycollab.vaadin.AppContext

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class ActivityUrlResolver extends CrmUrlResolver {
    this.addSubResolver("list", new ActivityListUrlResolver)
    this.addSubResolver("task", new ActivityTaskUrlResolver)
    this.addSubResolver("meeting", new MeetingUrlResolver)
    this.addSubResolver("call", new CallUrlResolver)

    class ActivityListUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new CrmEvent.GotoContainer(this,
                new CrmModuleScreenData.GotoModule(AppContext.getMessage(CrmCommonI18nEnum.TOOLBAR_ACTIVITIES_HEADER))))
        }
    }

}
