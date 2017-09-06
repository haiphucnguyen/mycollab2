package com.mycollab.shell.view

import com.mycollab.module.crm.httpmapping.CrmUrlResolver
import com.mycollab.module.file.view.FileUrlResolver
import com.mycollab.module.project.httpmapping.ProjectUrlResolver
import com.mycollab.premium.module.user.accountsettings.view.AccountSettingUrlResolver
import com.mycollab.vaadin.mvp.UrlResolver
import org.apache.commons.lang3.StringUtils

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object ShellUrlResolver {
  val ROOT = new ShellUrlResolver
}
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

  protected def defaultPageErrorHandler() {
  }
}
