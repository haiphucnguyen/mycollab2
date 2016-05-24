package com.esofthead.mycollab.html

import com.esofthead.mycollab.configuration.StorageFactory
import com.hp.gagawa.java.elements.Img

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
object LinkUtils {

  def newAvatar(avatarId: String) = new Img("", StorageFactory.getInstance.getAvatarPath(avatarId, 16)).setWidth("16").
    setHeight("16").setStyle("display: inline-block; vertical-align: top;")
}
