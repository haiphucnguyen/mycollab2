package com.mycollab.module.project.view.settings.component;

import com.mycollab.core.utils.StringUtils;
import com.mycollab.html.DivLessFormatter;
import com.mycollab.module.file.service.AbstractStorageService;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectLinkBuilder;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.TooltipHelper;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Img;
import com.mycollab.vaadin.ui.UIConstants;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

import static com.mycollab.vaadin.TooltipHelper.TOOLTIP_ID;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectUserLink extends Label {
    private static final long serialVersionUID = 1L;

    public ProjectUserLink(String username, String userAvatarId, String displayName) {
        if (StringUtils.isBlank(username)) {
            return;
        }
        this.setContentMode(ContentMode.HTML);
        DivLessFormatter div = new DivLessFormatter();
        Img avatarLink = new Img("", AppContextUtil.getSpringBean(AbstractStorageService.class)
                .getAvatarPath(userAvatarId, 16));
        avatarLink.setCSSClass(UIConstants.CIRCLE_BOX);
        A memberLink = new A().setId("tag" + TOOLTIP_ID).setHref(ProjectLinkBuilder.generateProjectMemberFullLink(
                CurrentProjectVariables.getProjectId(), username)).appendText(StringUtils.trim(displayName, 30, true));
        memberLink.setAttribute("onmouseover", TooltipHelper.userHoverJsFunction(username));
        memberLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
        div.appendChild(avatarLink, DivLessFormatter.EMPTY_SPACE(), memberLink);
        this.setValue(div.write());
    }
}
