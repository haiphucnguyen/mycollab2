package com.esofthead.mycollab.common.ui.components.notification;

import com.esofthead.mycollab.common.ui.components.AbstractNotification;
import com.esofthead.mycollab.shell.view.SmtpConfigurationWindow;
import com.vaadin.ui.*;
import org.vaadin.maddon.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.0.4
 */
public class SmtpSetupNotification  extends AbstractNotification {

    public SmtpSetupNotification() {
        super(AbstractNotification.WARNING);
    }

    @Override
    public Component renderContent() {
        MHorizontalLayout layout = new MHorizontalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        Button smtpBtn = new Button("Setup", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new SmtpConfigurationWindow());
            }
        });
        smtpBtn.setStyleName("link");
        layout.with(new Label("You did not set up a SMTP account yet."), smtpBtn);
        return layout;
    }
}
