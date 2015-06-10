package com.esofthead.mycollab.mobile.shell

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver
import com.esofthead.mycollab.mobile.module.project.ProjectUrlResolver
import com.esofthead.mycollab.mobile.shell.events.ShellEvent
import com.esofthead.mycollab.vaadin.mvp.UrlResolver
import com.vaadin.ui.UI

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class ShellUrlResolver extends UrlResolver {
    this.addSubResolver("crm", new CrmUrlResolver().build)
    this.addSubResolver("project", new ProjectUrlResolver().build)

    def navigateByFragement(fragement: String) {
        if (fragement != null && fragement.length > 0) {
            val tokens: Array[String] = fragement.split("/")
            this.handle(tokens: _*)
        }
        else {
            EventBusFactory.getInstance.post(new ShellEvent.GotoMainPage(UI.getCurrent, null))
        }
    }

    override protected def defaultPageErrorHandler(): Unit = {
        EventBusFactory.getInstance.post(new ShellEvent.GotoMainPage(UI.getCurrent, null))
    }
}
