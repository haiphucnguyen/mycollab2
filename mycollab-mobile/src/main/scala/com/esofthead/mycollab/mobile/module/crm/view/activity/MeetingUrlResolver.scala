package com.esofthead.mycollab.mobile.module.crm.view.activity

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver
import com.esofthead.mycollab.mobile.module.crm.events.ActivityEvent
import com.esofthead.mycollab.module.crm.domain.MeetingWithBLOBs

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class MeetingUrlResolver extends CrmUrlResolver {
    this.addSubResolver("add", new MeetingAddUrlResolver)
    this.addSubResolver("edit", new MeetingEditUrlResolver)
    this.addSubResolver("preview", new MeetingPreviewUrlResolver)

    class MeetingAddUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new ActivityEvent.MeetingAdd(this, new MeetingWithBLOBs))
        }
    }

    class MeetingEditUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val meetingId: Int = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new ActivityEvent.MeetingEdit(this, meetingId))
        }
    }

    class MeetingPreviewUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val accountId: Int = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new ActivityEvent.MeetingRead(this, accountId))
        }
    }

}
