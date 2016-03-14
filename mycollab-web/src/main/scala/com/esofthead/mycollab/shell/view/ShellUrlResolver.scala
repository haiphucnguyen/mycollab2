package com.esofthead.mycollab.shell.view

import com.esofthead.mycollab.module.crm.view.CrmUrlResolver
import com.esofthead.mycollab.module.file.view.FileUrlResolver
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.premium.module.user.accountsettings.view.AccountSettingUrlResolver
import com.esofthead.mycollab.vaadin.mvp.UrlResolver
import org.apache.commons.lang3.StringUtils

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class ShellUrlResolver extends UrlResolver {
  this.addSubResolver("crm", new CrmUrlResolver().build)
  this.addSubResolver("project", new ProjectUrlResolver().build)
  this.addSubResolver("account", new AccountSettingUrlResolver().build)
  this.addSubResolver("document", new FileUrlResolver().build)

  def resolveFragment(newFragmentUrl: String) {
    if (!StringUtils.isBlank(newFragmentUrl)) {
      val tokens = newFragmentUrl.split("/")
      this.handle(tokens: _*)
    }
  }

  protected def defaultPageErrorHandler {
  }
}
