package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.configuration.StorageManager;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Img;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author MyCollab Ltd.
 * @version 5.0.4
 */
public class ProjectMemberLink extends Label {
    public ProjectMemberLink(String username, String userAvatarId,
                    String displayName) {
        if (StringUtils.isBlank(username)) {
            return;
        }
        this.setContentMode(ContentMode.HTML);
        String uid = UUID.randomUUID().toString();
        DivLessFormatter div = new DivLessFormatter();
        Img userAvatar = new Img("", StorageManager.getAvatarLink(userAvatarId, 16));
        A userLink = new A().setId("tag" + uid).setHref(ProjectLinkBuilder.generateProjectMemberFullLink(CurrentProjectVariables.getProjectId(), username))
                .appendText(displayName);
        userLink.setAttribute("onmouseover", TooltipHelper.userHoverJsDunction(uid, username));
        userLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction(uid));
        div.appendChild(userAvatar, DivLessFormatter.EMPTY_SPACE(), userLink, DivLessFormatter.EMPTY_SPACE(), TooltipHelper.buildDivTooltipEnable(uid));
        this.setValue(div.write());
    }
}
