package com.esofthead.mycollab.shell.view

import com.esofthead.mycollab.module.crm.view.CrmUrlResolver
import com.esofthead.mycollab.module.file.view.FileUrlResolver
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.user.accountsettings.view.AccountUrlResolver
import com.esofthead.mycollab.vaadin.mvp.UrlResolver
import org.apache.commons.lang3.StringUtils

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class ShellUrlResolver extends UrlResolver {
    this.addSubResolver("crm", new CrmUrlResolver().build)
    this.addSubResolver("project", new ProjectUrlResolver().build)
    this.addSubResolver("account", new AccountUrlResolver().build)
    this.addSubResolver("document", new FileUrlResolver().build)

    def navigateByFragement(fragement: String) {
        if (!StringUtils.isBlank(fragement)) {
            val tokens: Array[String] = fragement.split("/")
            this.handle(tokens:_*)
        }
    }

    protected def defaultPageErrorHandler {
    }
}
