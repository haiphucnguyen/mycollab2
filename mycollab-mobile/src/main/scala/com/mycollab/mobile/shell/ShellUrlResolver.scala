package com.mycollab.mobile.shell

import com.mycollab.mobile.module.crm.CrmUrlResolver
import com.mycollab.mobile.module.project.httpmapping.ProjectUrlResolver
import com.mycollab.vaadin.mvp.UrlResolver

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
    } else {
      defaultPageErrorHandler()
    }
  }

  override protected def defaultPageErrorHandler(): Unit = {}
}
