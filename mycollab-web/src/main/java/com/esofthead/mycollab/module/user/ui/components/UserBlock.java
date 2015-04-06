package com.esofthead.mycollab.module.user.ui.components;

import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.user.AccountLinkBuilder;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.hp.gagawa.java.elements.A;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import org.vaadin.maddon.button.MButton;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.UUID;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class UserBlock extends MVerticalLayout {
    public UserBlock(String username, String userAvatarId, String displayName) {
        withMargin(false).withWidth("80px");
        MButton button = new MButton(UserAvatarControlFactory.createAvatarResource(userAvatarId, 48)).withStyleName("link");

        String uid = UUID.randomUUID().toString();
        DivLessFormatter div = new DivLessFormatter();
        A userLink = new A().setId("tag" + uid).setHref(AccountLinkBuilder.generatePreviewFullUserLink(username))
                .appendText(displayName);
        userLink.setAttribute("onmouseover", TooltipHelper.buildUserHtmlTooltip(uid, username));
        div.appendChild(userLink, DivLessFormatter.EMPTY_SPACE(), TooltipHelper.buildDivTooltipEnable(uid));
        Label userLbl = new Label(div.write(), ContentMode.HTML);
        with(button, userLbl);
    }
}
