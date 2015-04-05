package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class ProjectMemberBlock extends MVerticalLayout{
    public ProjectMemberBlock(String username, String userAvatarId, String displayName) {
        withMargin(false).withWidth("80px");
        UserAvatarControlFactory.createAvatarResource(userAvatarId, 48);
    }
}
