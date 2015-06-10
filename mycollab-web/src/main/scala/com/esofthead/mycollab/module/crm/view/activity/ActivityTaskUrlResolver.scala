package com.esofthead.mycollab.module.crm.view.activity

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.crm.domain.Task
import com.esofthead.mycollab.module.crm.events.ActivityEvent
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class ActivityTaskUrlResolver extends CrmUrlResolver {
    this.addSubResolver("add", new TaskAddUrlResolver)
    this.addSubResolver("edit", new TaskEditUrlResolver)
    this.addSubResolver("preview", new TaskPreviewUrlResolver)

    class TaskAddUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new ActivityEvent.TaskAdd(this, new Task))
        }
    }

    class TaskEditUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val meetingId: Integer = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new ActivityEvent.TaskEdit(this, meetingId))
        }
    }

    class TaskPreviewUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val accountId: Integer = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new ActivityEvent.TaskRead(this, accountId))
        }
    }

}
