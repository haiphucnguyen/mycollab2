package com.mycollab.module.project.view.settings.component

import com.hp.gagawa.java.elements.A
import com.hp.gagawa.java.elements.Img
import com.mycollab.core.utils.StringUtils
import com.mycollab.html.DivLessFormatter
import com.mycollab.module.file.service.AbstractStorageService
import com.mycollab.module.project.CurrentProjectVariables
import com.mycollab.module.project.ProjectLinkBuilder
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.TooltipHelper
import com.mycollab.vaadin.TooltipHelper.TOOLTIP_ID
import com.mycollab.vaadin.ui.UIConstants
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.ui.Label

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class ProjectUserLink(username: String, userAvatarId: String, displayName: String) : Label() {

    init {
        if (StringUtils.isNotBlank(username)) {
            this.contentMode = ContentMode.HTML
            val div = DivLessFormatter()
            val avatarLink = Img("", AppContextUtil.getSpringBean(AbstractStorageService::class.java)
                    .getAvatarPath(userAvatarId, 16))
            avatarLink.cssClass = UIConstants.CIRCLE_BOX
            val memberLink = A().setId("tag" + TOOLTIP_ID).setHref(ProjectLinkBuilder.generateProjectMemberFullLink(
                    CurrentProjectVariables.projectId, username)).appendText(StringUtils.trim(displayName, 30, true))
            memberLink.setAttribute("onmouseover", TooltipHelper.userHoverJsFunction(username))
            memberLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction())
            div.appendChild(avatarLink, DivLessFormatter.EMPTY_SPACE, memberLink)
            this.value = div.write()
        }
    }

    companion object {
        private val serialVersionUID = 1L
    }
}
