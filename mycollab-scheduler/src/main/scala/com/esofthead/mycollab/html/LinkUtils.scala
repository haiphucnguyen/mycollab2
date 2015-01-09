package com.esofthead.mycollab.html

import com.esofthead.mycollab.configuration.StorageManager
import com.hp.gagawa.java.elements.Img

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
object LinkUtils {

  def newAvatar(avatarId:String): Img = new Img("", StorageManager.getAvatarLink(avatarId, 16)).setWidth("16")
    .setHeight("16").setStyle("display: inline-block; vertical-align: top;")
}
