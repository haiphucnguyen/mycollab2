package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.ShellI18nEnum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.esb.SendUserInvitationEvent;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.google.common.eventbus.AsyncEventBus;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class NewUserAddedWindow extends Window {
    public NewUserAddedWindow(final SimpleUser user) {
        super("Create a new user");
        this.setModal(true);
        this.setResizable(false);
        this.center();
        this.setWidth("600px");
        MVerticalLayout content = new MVerticalLayout();
        this.setContent(content);

        ELabel infoLbl = new ELabel(FontAwesome.CHECK_CIRCLE.getHtml() + " A new user named " + user.getDisplayName() +
                " has been created", ContentMode.HTML);
        content.with(infoLbl);

        String signinInstruction = String.format("Signin MyCollab website at <a href='%s'>%s</a>", AppContext.getSiteUrl(), AppContext.getSiteUrl());
        content.with(new MVerticalLayout(new Label(signinInstruction, ContentMode.HTML),
                new ELabel(AppContext.getMessage(ShellI18nEnum.FORM_EMAIL)).withStyleName(UIConstants.LABEL_META_INFO),
                new Label(user.getUsername()),
                new ELabel(AppContext.getMessage(ShellI18nEnum.FORM_PASSWORD)).withStyleName(UIConstants.LABEL_META_INFO),
                new Label(user.getPassword())));

        Button sendEmailBtn = new Button("Send Email", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AsyncEventBus asyncEventBus = AppContextUtil.getSpringBean(AsyncEventBus.class);
                SendUserInvitationEvent invitationEvent = new SendUserInvitationEvent(user.getUsername(), user.getInviteUser(),
                        user.getSubdomain(), AppContext.getAccountId());
                asyncEventBus.post(invitationEvent);
                NotificationUtil.showNotification(AppContext.getMessage(GenericI18Enum.HELP_SPAM_FILTER_PREVENT_TITLE),
                        AppContext.getMessage(GenericI18Enum.HELP_SPAM_FILTER_PREVENT_MESSAGE));
            }
        });
        sendEmailBtn.addStyleName(UIConstants.BUTTON_ACTION);
        content.with(sendEmailBtn);

        Button createMoreUserBtn = new Button("Create another user", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                EventBusFactory.getInstance().post(new UserEvent.GotoAdd(this, null));
                close();
            }
        });
        createMoreUserBtn.addStyleName(UIConstants.BUTTON_LINK);

        Button doneBtn = new Button("Done", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });
        doneBtn.addStyleName(UIConstants.BUTTON_ACTION);
        MHorizontalLayout buttonControls = new MHorizontalLayout(createMoreUserBtn, doneBtn).withFullWidth()
                .withAlign(createMoreUserBtn, Alignment.MIDDLE_LEFT).withAlign(doneBtn, Alignment.MIDDLE_RIGHT);
        content.with(buttonControls);
    }
}
