package com.mycollab.html

import com.hp.gagawa.java.elements.Img
import com.mycollab.module.file.service.AbstractStorageService
import com.mycollab.spring.AppContextUtil

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
object LinkUtils {

  def storageService() = AppContextUtil.getSpringBean(classOf[AbstractStorageService])

  def newAvatar(avatarId: String) = new Img("", storageService.getAvatarPath(avatarId, 16)).setWidth("16").
    setHeight("16").setStyle("display: inline-block; vertical-align: top;").setCSSClass("circle-box")

  def accountLogoPath(accountId: Integer, logoId: String) = if (logoId == null) storageService.generateAssetRelativeLink("icons/logo.png")
  else storageService.getLogoPath(accountId, logoId, 150)
}
