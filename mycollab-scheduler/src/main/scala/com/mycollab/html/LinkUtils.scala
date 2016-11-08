package com.mycollab.html

import com.hp.gagawa.java.elements.Img
import com.mycollab.configuration.StorageFactory

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
object LinkUtils {
  
  def newAvatar(avatarId: String) = new Img("", StorageFactory.getAvatarPath(avatarId, 16)).setWidth("16").
    setHeight("16").setStyle("display: inline-block; vertical-align: top;").setCSSClass("circle-box")
  
  def accountLogoPath(accountId: Integer, logoId: String) = if (logoId == null) StorageFactory.generateAssetRelativeLink("icons/logo.png")
  else StorageFactory.getLogoPath(accountId, logoId, 150)
}
