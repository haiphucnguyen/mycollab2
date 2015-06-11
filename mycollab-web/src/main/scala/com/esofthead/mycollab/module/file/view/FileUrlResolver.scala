package com.esofthead.mycollab.module.file.view

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver
import com.esofthead.mycollab.shell.events.ShellEvent
import com.esofthead.mycollab.vaadin.desktop.ui.ModuleHelper
import com.esofthead.mycollab.vaadin.mvp.UrlResolver

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class FileUrlResolver extends UrlResolver {
    def build: UrlResolver = {
        this.addSubResolver("list", new FileListUrlResolver)
        this
    }

    override def handle(params: String*) {
        if (!ModuleHelper.isCurrentFileModule) {
            EventBusFactory.getInstance.post(new ShellEvent.GotoFileModule(this, params))
        }
        else {
            super.handle(params: _*)
        }
    }

    protected def defaultPageErrorHandler {
    }

    class FileListUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
        }
    }

}
