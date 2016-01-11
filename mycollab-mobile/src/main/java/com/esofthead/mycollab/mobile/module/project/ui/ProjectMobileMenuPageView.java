package com.esofthead.mycollab.mobile.module.project.ui;

import com.esofthead.mycollab.mobile.ui.AbstractMobileMenuPageView;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import org.vaadin.thomas.slidemenu.SlideMenu;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public class ProjectMobileMenuPageView extends AbstractMobileMenuPageView {
    @Override
    protected void buildNavigateMenu() {

        // Section labels have a bolded style
        Label l = new Label("Views:");
        l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
        getMenu().addComponent(l);

        // Buttons with styling (slightly smaller with left-aligned text)
        Button activityBtn = new Button("Activities", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getMenu().close();
            }
        });
        activityBtn.setIcon(FontAwesome.INBOX);
        activityBtn.addStyleName(SlideMenu.STYLENAME_BUTTON);
        getMenu().addComponent(activityBtn);

        // add more buttons for a more realistic look.
        Button messageBtn = new Button("Messages");
        messageBtn.setIcon(ProjectAssetsManager.getAsset(ProjectTypeConstants.MESSAGE));
        messageBtn.addStyleName(SlideMenu.STYLENAME_BUTTON);
        getMenu().addComponent(messageBtn);

        Button phaseBtn = new Button("Phases");
        phaseBtn.addStyleName(SlideMenu.STYLENAME_BUTTON);
        phaseBtn.setIcon(ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE));
        getMenu().addComponent(phaseBtn);

        Button ticketBtn = new Button("Tickets");
        ticketBtn.addStyleName(SlideMenu.STYLENAME_BUTTON);
        ticketBtn.setIcon(FontAwesome.TICKET);
        getMenu().addComponent(ticketBtn);

        Button userBtn = new Button("Users");
        userBtn.setIcon(ProjectAssetsManager.getAsset(ProjectTypeConstants.MEMBER));
        userBtn.addStyleName(SlideMenu.STYLENAME_BUTTON);
        getMenu().addComponent(userBtn);

        l = new Label("Settings:");
        l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
        getMenu().addComponent(l);

        Button logoutBtn = new Button("Logout");
        logoutBtn.setIcon(FontAwesome.SIGN_OUT);
        logoutBtn.addStyleName(SlideMenu.STYLENAME_BUTTON);
        getMenu().addComponent(logoutBtn);
    }
}
