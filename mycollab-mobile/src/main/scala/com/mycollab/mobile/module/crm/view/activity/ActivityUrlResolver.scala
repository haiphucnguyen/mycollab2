package com.mycollab.mobile.module.crm.view.activity

import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.crm.CrmUrlResolver
import com.mycollab.mobile.module.crm.CrmModuleScreenData
import com.mycollab.mobile.module.crm.events.CrmEvent
import com.mycollab.module.crm.i18n.CrmCommonI18nEnum
import com.mycollab.vaadin.UserUIContext

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
            EventBusFactory.getInstance().post(new CrmEvent.GotoActivitiesView(this,
                new CrmModuleScreenData.GotoModule(Array())))
        }
    }

}
