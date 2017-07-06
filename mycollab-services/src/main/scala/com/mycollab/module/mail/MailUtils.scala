package com.mycollab.module.mail

import com.mycollab.configuration.{IDeploymentMode, SiteConfiguration}
import com.mycollab.module.file.service.AbstractStorageService
import com.mycollab.module.user.service.BillingAccountService
import com.mycollab.spring.AppContextUtil

/**
  * @author MyCollab Ltd
  * @since 5.4.1
  */
object MailUtils {
  def getSiteUrl(sAccountId: Integer): String = {
    var siteUrl = ""
    val mode = AppContextUtil.getSpringBean(classOf[IDeploymentMode])
    if (mode.isDemandEdition) {
      val billingAccountService = AppContextUtil.getSpringBean(classOf[BillingAccountService])
      val account = billingAccountService.getAccountById(sAccountId)
      if (account != null) siteUrl = SiteConfiguration.getSiteUrl(account.getSubdomain)
    }
    else siteUrl = SiteConfiguration.getSiteUrl("")
    siteUrl
  }

  def getAvatarLink(userAvatarId: String, size: Int): String = AppContextUtil.getSpringBean(classOf[AbstractStorageService]).getAvatarPath(userAvatarId, size)
}
