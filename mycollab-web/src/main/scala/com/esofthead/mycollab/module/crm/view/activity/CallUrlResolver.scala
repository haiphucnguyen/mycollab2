package com.esofthead.mycollab.module.crm.view.activity

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs
import com.esofthead.mycollab.module.crm.events.ActivityEvent
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class CallUrlResolver extends CrmUrlResolver {
    this.addSubResolver("add", new AddUrlResolver)
    this.addSubResolver("edit", new EditUrlResolver)
    this.addSubResolver("preview", new PreviewUrlResolver)

    class AddUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new ActivityEvent.CallAdd(this, new CallWithBLOBs))
        }
    }

    class EditUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val callId: Integer = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new ActivityEvent.CallEdit(this, callId))
        }
    }

    class PreviewUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val callId: Integer = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new ActivityEvent.CallRead(this, callId))
        }
    }
}
