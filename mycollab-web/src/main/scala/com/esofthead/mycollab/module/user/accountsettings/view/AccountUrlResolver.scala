package com.esofthead.mycollab.module.user.accountsettings.view

import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.core.DeploymentMode
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.user.accountsettings.view.events.{AccountBillingEvent, AccountCustomizeEvent, ProfileEvent, SetupEvent}
import com.esofthead.mycollab.shell.events.ShellEvent
import com.esofthead.mycollab.vaadin.desktop.ui.ModuleHelper
import com.esofthead.mycollab.vaadin.mvp.UrlResolver

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class AccountUrlResolver extends UrlResolver {
    def build: UrlResolver = {
        this.addSubResolver("preview", new ReadUrlResolver)
        this.addSubResolver("billing", new BillingUrlResolver)
        this.addSubResolver("user", new UserUrlResolver)
        this.addSubResolver("role", new RoleUrlResolver)
        this.addSubResolver("customization", new CustomizeUrlResolver)
        if (SiteConfiguration.getDeploymentMode eq DeploymentMode.standalone) {
            this.addSubResolver("setup", new SetupUrlResolver)
        }
        return this
    }

    override def handle(params: String*) {
        if (!ModuleHelper.isCurrentAccountModule) {
            EventBusFactory.getInstance.post(new ShellEvent.GotoUserAccountModule(this, params))
        }
        else {
            super.handle(params: _*)
        }
    }

    protected def defaultPageErrorHandler {
        EventBusFactory.getInstance.post(new ProfileEvent.GotoProfileView(this, null))
    }

    private class ReadUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new ProfileEvent.GotoProfileView(this, null))
        }
    }

    private class BillingUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new AccountBillingEvent.GotoSummary(this, null))
        }
    }

    private class CustomizeUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new AccountCustomizeEvent.GotoCustomize(this, null))
        }
    }

    private class SetupUrlResolver extends AccountUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new SetupEvent.GotoSetupPage(this, null))
        }
    }

}
