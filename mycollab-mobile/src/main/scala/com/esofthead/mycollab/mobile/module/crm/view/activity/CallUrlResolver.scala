package com.esofthead.mycollab.mobile.module.crm.view.activity

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver
import com.esofthead.mycollab.mobile.module.crm.events.ActivityEvent
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class CallUrlResolver extends CrmUrlResolver {
    this.addSubResolver("add", new CallAddUrlResolver)
    this.addSubResolver("edit", new CallEditUrlResolver)
    this.addSubResolver("preview", new CallPreviewUrlResolver)

    class CallAddUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new ActivityEvent.CallAdd(this, new CallWithBLOBs))
        }
    }

    class CallEditUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val meetingId: Int = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new ActivityEvent.CallEdit(this, meetingId))
        }
    }

    class CallPreviewUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val accountId: Int = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new ActivityEvent.CallRead(this, accountId))
        }
    }

}
